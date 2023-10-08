package com.jpeony.file.common.util;

import static com.jpeony.file.common.constant.FileConstant.WINDOW_SIZE;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.jpeony.file.common.annotation.DataBody;
import com.jpeony.file.common.annotation.Document;
import com.jpeony.file.common.annotation.Documents;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import com.jpeony.file.common.enums.SuffixEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件导出处理
 *
 * @author yihonglei
 */
public class FileExporterUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(FileExporterUtil.class);

    /**
     * 导出文件
     *
     * @param ts       实体类 必须要注解
     *                 {@link Document}
     *                 {@link Documents}
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param <T>
     * @return 文件路径 多个用#分割
     */
    public static <T> void export(List<T> ts, String filePath, String fileName) {
        Assert.notEmpty(ts, "Collection must have elements");
        Assert.hasLength(filePath, "file's path must be has text");
        Assert.hasLength(fileName, "file's name must be has text");

        Class<?> tClass = ts.get(0).getClass();
        Documents documents = tClass.getAnnotation(Documents.class);
        Document doc = tClass.getAnnotation(Document.class);
        if (documents == null && doc == null) {
            LOGGER.warn("Documents or Document Annotation is null");
            return;
        }
        List<Field> fields = ClassUtil.getAnnotationField(tClass, DataBody.class);
        Assert.notEmpty(fields, "fields must have DataBody Annotation");

        String path = StringUtils.getFileName(filePath, fileName);

        //排序
        Collections.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                DataBody body1 = o1.getAnnotation(DataBody.class);
                DataBody body2 = o2.getAnnotation(DataBody.class);
                return body1.order() - body2.order();
            }
        });

        if (doc != null) {
            export(ts, fields, path, doc);
            return;
        }

        if (documents != null) {
            export(ts, fields, path, documents);
            return;
        }
    }

    /**
     * 导出
     *
     * @param ts
     * @param file
     * @param doc
     * @param <T>
     */
    private static <T> void export(List<T> ts, List<Field> fields, String file, Document doc) {
        String fullName = null;
        try {
            SuffixEnum suffixEnum = doc.suffix();
            fullName = file + suffixEnum.getSuffix();
            switch (suffixEnum) {
                case DEFAULT:
                case TXT:
                case CSV:
                    exportDefault(ts, fields, fullName, doc);
                    break;
                case EXCEL:
                    exportExcel(ts, fields, fullName, doc);
                default:
                    break;
            }
            LOGGER.info("导出文件成功 file:{}", fullName);
        } catch (Throwable e) {
            LOGGER.error("导出文件异常 file:{} ", fullName, e);
        }
    }

    /**
     * excel 写入
     *
     * @param <T>
     * @param ts
     * @param fields
     * @param file
     * @param doc
     */
    private static <T> void exportExcel(List<T> ts, List<Field> fields, String file, Document doc)
            throws IOException, IllegalAccessException {
        int size = doc.rowAccessWindowSize();
        if (size <= 0) {
            size = WINDOW_SIZE;
        }
        SXSSFWorkbook wb = new SXSSFWorkbook(size);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            int rowNumber = 0;
            //创建表头
            Sheet sheet = wb.createSheet("sheet1");
            //创建行
            Row row = sheet.createRow(rowNumber++);
            List<String> headers = getHeader(fields);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headers.get(i));
            }

            for (T t : ts) {
                Row r = sheet.createRow(rowNumber++);
                List<String> contents = getContent(t, fields);
                for (int i = 0; i < contents.size(); i++) {
                    Cell cell = r.createCell(i);
                    cell.setCellValue(contents.get(i));
                }
            }
            wb.write(outputStream);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            wb.close();
            wb.dispose();
        }
    }

    /**
     * 导出默认的文件类型
     *
     * @param ts
     * @param fields
     * @param file
     * @param doc
     * @param <T>
     */
    private static <T> void exportDefault(List<T> ts, List<Field> fields, String file, Document doc)
            throws IOException, IllegalAccessException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), doc.charSet())));
        try {
            List<String> headers = getHeader(fields);
            String header = Joiner.join(headers, doc.column().getSeparator());
            pw.println(header);
            for (T t : ts) {
                List<String> contents = getContent(t, fields);
                String content = Joiner.join(contents, doc.column().getSeparator());
                pw.println(content);
            }
        } finally {
            pw.flush();
            pw.close();
        }
    }

    /**
     * 获取文件里面的内容
     *
     * @param t
     * @param fields
     * @param <T>
     * @returnø
     */
    private static <T> List<String> getContent(T t, List<Field> fields) throws IllegalAccessException {
        List<String> contents = new ArrayList<String>(fields.size());
        for (Field f : fields) {
            DataBody body = f.getAnnotation(DataBody.class);
            if (body == null) {
                continue;
            }
            //设置访问权限
            f.setAccessible(true);
            Object value = f.get(t);
            if (value == null) {
                contents.add("");
                continue;
            }
            Class<?> aClass = f.getType();
            String format = body.format();
            if (!StringUtils.hasLength(format)) {
                contents.add(value.toString());
                continue;
            }
            //date类型
            if (aClass == Date.class) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                String val = dateFormat.format(value);
                contents.add(val);
                continue;
            }
            MessageFormat messageFormat = new MessageFormat(format);
            String val = messageFormat.format(new Object[]{value});
            contents.add(val);
        }
        return contents;
    }

    /**
     * 获取header信息
     *
     * @param fields
     * @return
     */
    private static List<String> getHeader(List<Field> fields) {
        List<String> headers = new ArrayList<String>(fields.size());
        for (Field f : fields) {
            DataBody body = f.getAnnotation(DataBody.class);
            if (body == null || !StringUtils.hasLength(body.header())) {
                continue;
            }
            headers.add(body.header());
        }
        return headers;
    }

    /**
     * 导出
     *
     * @param ts
     * @param file
     * @param docs
     * @param <T>
     */
    private static <T> void export(List<T> ts, List<Field> fields, String file, Documents docs) {
        Document[] documents = docs.docs();
        for (Document doc : documents) {
            export(ts, fields, file, doc);
        }
    }
}
