package com.jpeony.file.common.test;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import com.jpeony.file.common.annotation.DataBody;
import com.jpeony.file.common.annotation.Document;
import com.jpeony.file.common.annotation.Documents;
import com.jpeony.file.common.enums.SeparatorEnum;
import com.jpeony.file.common.enums.SuffixEnum;

@Data
@Documents(docs = {
        @Document(column = SeparatorEnum.TAB),
        @Document(column = SeparatorEnum.COMMA, suffix = SuffixEnum.CSV),
        @Document(column = SeparatorEnum.COMMA, suffix = SuffixEnum.EXCEL, rowAccessWindowSize = 1),
})
public class Person {
    @DataBody(header = "姓名")
    private String name;
    @DataBody(header = "手机号")
    private String phone;
    @DataBody(header = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @DataBody(header = "支付金额", format = "{0, number, #.##}")
    private Double pay;
    @DataBody(header = "剩余金额", format = "{0, number, #.##}")
    private BigDecimal payAmount;
}