package com.jpeony.file.common.annotation;

import static com.jpeony.file.common.constant.FileConstant.WINDOW_SIZE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jpeony.file.common.enums.SeparatorEnum;
import com.jpeony.file.common.enums.SuffixEnum;

/**
 * 文档标识
 *
 * @author yihonglei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Document {

    /**
     * 文件后缀
     *
     * @return
     */
    SuffixEnum suffix() default SuffixEnum.TXT;

    /**
     * 列之间的分隔符
     * excel不起作用
     *
     * @return
     */
    SeparatorEnum column() default SeparatorEnum.COMMA;

    /**
     * 字符集
     *
     * @return
     */
    String charSet() default "UTF-8";

    /**
     * excel xlsx 格式起作用 内存中保存多少行 大于这个数之后写入临时文件
     * 避免内存溢出
     *
     * @return
     */
    int rowAccessWindowSize() default WINDOW_SIZE;
}
