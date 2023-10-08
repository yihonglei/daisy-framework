package com.jpeony.boot.test.slow;

import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yihonglei
 */
public class SlowSqlAn {
    public static void main(String[] args) {
        try {
            // 慢sql属性提起
            List<ContentBO> slowSqlList = new ArrayList<>();

            // 读取文件
            File file = new File("/Users/hongqi/Documents/perf/mysql-slow-last-01.log");
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            ContentBO contentBO = new ContentBO();
            String str;
            while ((str = bf.readLine()) != null) {
                // appIp
                if (str.contains("User@Host:")) {
                    String[] tmpA = str.split("@");
                    String ip = tmpA[2].split("Id")[0].replace("[", "").replace("]", "").trim();
                    contentBO.setAppIp(ip);
                }
                // schema
                if (str.contains("Schema")) {
                    String[] tmpA = str.split(":");
                    String schemaVal = tmpA[1].split("Last_errno")[0].trim();
                    contentBO.setSchema(schemaVal);
                }
                // opeTime
                if (str.contains("Query_time")) {
                    String[] time = str.split(":");
                    contentBO.setOpeTime(new BigDecimal(time[1].replace("Lock_time", "").trim()));
                }
                // executeTime
                if (str.contains("timestamp")) {
                    String[] time = str.split("=");
                    long timestamp = Long.valueOf(time[1].split(";")[0].trim()) * 1000L;
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
                    contentBO.setExecuteTime(date);
                }
                // opeType
                if (str.contains("select ") || str.contains("SELECT ")) {
                    contentBO.setOpeType("select");
                }
                if (str.contains("insert ") || str.contains("INSERT ")) {
                    contentBO.setOpeType("insert");
                }
                if (str.contains("update ") || str.contains("UPDATE ")) {
                    contentBO.setOpeType("update");
                }
                if (str.contains("delete ") || str.contains("DELETE ")) {
                    contentBO.setOpeType("delete");
                }

                // sqlContent
                if (str.contains("select ") || str.contains("SELECT ")
                        || str.contains("insert ") || str.contains("INSERT ")
                        || str.contains("update ") || str.contains("UPDATE ")
                        || str.contains("delete ") || str.contains("DELETE ")) {
                    contentBO.setSqlContent(str);
                }

                if (str.contains("select ") || str.contains("SELECT ")
                        || str.contains("insert ") || str.contains("INSERT ")
                        || str.contains("update ") || str.contains("UPDATE ")
                        || str.contains("delete ") || str.contains("DELETE ")) {
                    slowSqlList.add(contentBO);
                    contentBO = new ContentBO();
                }
            }
            // 业务处理
            Map<String, List<ContentBO>> groupMap = slowSqlList.stream().collect(Collectors.groupingBy(ContentBO::getOpeType));
            List<ContentBO> insertList = groupMap.get("insert");
            if (CollectionUtils.isNotEmpty(insertList)) {
                System.out.println(
                        "操作次数: " + insertList.size() +
                                " 操作类型: insert" +
                                ", 总时间: " + insertList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).toString() +
                                ", 最大值: " + insertList.stream().map(ContentBO::getOpeTime).max(BigDecimal::compareTo).get().toString() +
                                ", 最小值: " + insertList.stream().map(ContentBO::getOpeTime).min(BigDecimal::compareTo).get().toString() +
                                ", 平均值: " + insertList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(insertList.size()), 6, BigDecimal.ROUND_HALF_UP));
            }

            List<ContentBO> selectList = groupMap.get("select");
            if (CollectionUtils.isNotEmpty(selectList)) {
                System.out.println(
                        "操作次数: " + selectList.size() +
                                " 操作类型: select" +
                                ", 总时间: " + selectList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).toString() +
                                ", 最大值: " + selectList.stream().map(ContentBO::getOpeTime).max(BigDecimal::compareTo).get().toString() +
                                ", 最小值: " + selectList.stream().map(ContentBO::getOpeTime).min(BigDecimal::compareTo).get().toString() +
                                ", 平均值: " + selectList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(selectList.size()), 6, BigDecimal.ROUND_HALF_UP));
            }

            List<ContentBO> updateList = groupMap.get("update");
            if (CollectionUtils.isNotEmpty(updateList)) {
                System.out.println(
                        "操作次数: " + updateList.size() +
                                " 操作类型: update" +
                                ", 总时间: " + updateList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).toString() +
                                ", 最大值: " + updateList.stream().map(ContentBO::getOpeTime).max(BigDecimal::compareTo).get().toString() +
                                ", 最小值: " + updateList.stream().map(ContentBO::getOpeTime).min(BigDecimal::compareTo).get().toString() +
                                ", 平均值: " + updateList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(updateList.size()), 6, BigDecimal.ROUND_HALF_UP));
            }

            List<ContentBO> deleteList = groupMap.get("delete");
            if (CollectionUtils.isNotEmpty(deleteList)) {
                System.out.println(
                        "操作次数: " + deleteList.size() +
                                " 操作类型: delete" +
                                ", 总时间: " + deleteList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).toString() +
                                ", 最大值: " + deleteList.stream().map(ContentBO::getOpeTime).max(BigDecimal::compareTo).get().toString() +
                                ", 最小值: " + deleteList.stream().map(ContentBO::getOpeTime).min(BigDecimal::compareTo).get().toString() +
                                ", 平均值: " + deleteList.stream().map(ContentBO::getOpeTime).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(deleteList.size()), 6, BigDecimal.ROUND_HALF_UP));
            }

            // insert
            for (ContentBO bo : insertList) {
                System.out.println("insert操作, Schema: " + bo.getSchema() +
                        ", 操作时间: " + bo.getExecuteTime() +
                        ", 耗时: " + bo.getOpeTime() +
                        ", SQL: " + bo.getSqlContent());
            }

            // update
            for (ContentBO bo : updateList) {
                System.out.println("update操作, Schema: " + bo.getSchema() +
                        ", 操作时间: " + bo.getExecuteTime() +
                        ", 耗时: " + bo.getOpeTime() +
                        ", SQL: " + bo.getSqlContent());
            }

            // delete
            for (ContentBO bo : deleteList) {
                System.out.println("delete操作, Schema: " + bo.getSchema() +
                        ", 操作时间: " + bo.getExecuteTime() +
                        ", 耗时: " + bo.getOpeTime() +
                        ", SQL: " + bo.getSqlContent());
            }

            // select
            for (ContentBO bo : selectList) {
                System.out.println("select操作, Schema: " + bo.getSchema() +
                        ", 操作时间: " + bo.getExecuteTime() +
                        ", 耗时: " + bo.getOpeTime() +
                        ", SQL: " + bo.getSqlContent());
            }
            // select ip 分组
            Map<String, List<ContentBO>> ipGroupMap = selectList.stream().collect(Collectors.groupingBy(ContentBO::getAppIp));
            for (Map.Entry<String, List<ContentBO>> entry : ipGroupMap.entrySet()) {
                System.out.println("===========ip:" + entry.getKey());
                List<ContentBO> list = entry.getValue();
                for (ContentBO bo : list) {
                    System.out.println("select操作, Schema: " + bo.getSchema() +
                            ", 耗时: " + bo.getOpeTime() +
                            ", SQL: " + bo.getSqlContent());
                }
            }

            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static class ContentBO {
        /**
         * 应用ip
         */
        private String appIp;
        /**
         * 数据库实例
         */
        private String schema;
        /**
         * s
         */
        private BigDecimal opeTime;
        /**
         * 执行日期
         */
        private String executeTime;
        /**
         * select insert update delete
         */
        private String opeType;
        /**
         * sql内容
         */
        private String sqlContent;

        public String getAppIp() {
            return appIp;
        }

        public void setAppIp(String appIp) {
            this.appIp = appIp;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public BigDecimal getOpeTime() {
            return opeTime;
        }

        public void setOpeTime(BigDecimal opeTime) {
            this.opeTime = opeTime;
        }

        public String getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(String executeTime) {
            this.executeTime = executeTime;
        }

        public String getOpeType() {
            return opeType;
        }

        public void setOpeType(String opeType) {
            this.opeType = opeType;
        }

        public String getSqlContent() {
            return sqlContent;
        }

        public void setSqlContent(String sqlContent) {
            this.sqlContent = sqlContent;
        }
    }
}
