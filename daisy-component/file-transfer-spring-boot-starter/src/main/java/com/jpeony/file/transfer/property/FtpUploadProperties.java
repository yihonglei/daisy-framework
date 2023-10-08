package com.jpeony.file.transfer.property;

/**
 * 文件上传ftp工具配置属性
 *
 * @author : yihonglei
 */
public class FtpUploadProperties {
    private String host;
    private Integer port;
    private String rootDir;
    private String userName;
    private String password;
    private String viewUrl;
    private String insideViewUrl;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getInsideViewUrl() {
        return insideViewUrl;
    }

    public void setInsideViewUrl(String insideViewUrl) {
        this.insideViewUrl = insideViewUrl;
    }
}
