package com.jpeony.file.transfer.property;

/**
 * 下载属性
 *
 * @author : yihonglei
 */
public class DownloadProperties {
    private int readTimeout;
    private int connectTimeout;

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
