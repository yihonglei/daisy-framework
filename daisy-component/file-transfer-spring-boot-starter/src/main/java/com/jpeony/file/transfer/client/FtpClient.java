package com.jpeony.file.transfer.client;

import com.jpeony.file.transfer.property.FtpUploadProperties;
import com.jpeony.file.transfer.result.FileUploadResult;
import com.jpeony.file.transfer.util.FtpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * ftp 客户端
 *
 * @author : yihonglei
 */
public class FtpClient {

    private static Logger logger = LoggerFactory.getLogger(FtpClient.class);

    private final static int DEFAULT_PORT = 21;

    private final static String DEFAULT_PATH = "/upload";

    private final static String SLASH = "/";

    private FtpUploadProperties ftpProperties;

    public FtpClient(FtpUploadProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    /**
     * 文件上传
     *
     * @param fileName
     * @param inputStream
     * @return
     */
    public FileUploadResult fileUpload(String fileName, InputStream inputStream) {
        return this.fileUpload(fileName, null, inputStream);
    }

    /**
     * 文件上传
     *
     * @param fileName
     * @param path        必须已斜杠开头，否则上传至根目录
     * @param inputStream
     * @return
     */
    public FileUploadResult fileUpload(String fileName, String path, InputStream inputStream) {
        int port = ftpProperties.getPort() == null ? DEFAULT_PORT : ftpProperties.getPort();
        String dirPath = DEFAULT_PATH;
        //配置中rootDir不为空且以斜杠开头
        if (ftpProperties.getRootDir() != null && ftpProperties.getRootDir().startsWith(SLASH)) {
            dirPath = ftpProperties.getRootDir();
        }
        //指定路径不为空，并且以斜杠开头
        if (path != null && path.startsWith(SLASH)) {
            dirPath += path;
        }
        FtpUtil ftpUtil = new FtpUtil(ftpProperties.getHost(), port, ftpProperties.getUserName(), ftpProperties.getPassword());
        if (ftpUtil.upload(fileName, dirPath, inputStream)) {
            return new FileUploadResult(dirPath + SLASH + fileName, ftpProperties);
        }
        return null;
    }
}
