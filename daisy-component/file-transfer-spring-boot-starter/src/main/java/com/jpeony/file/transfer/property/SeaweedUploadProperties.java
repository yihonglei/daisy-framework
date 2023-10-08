package com.jpeony.file.transfer.property;

/**
 * 文件上传seaweed工具配置属性
 *
 * @author : yihonglei
 */
public class SeaweedUploadProperties {
    private String host;
    private Integer port;
    private Integer connectTimeout;
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

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
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
