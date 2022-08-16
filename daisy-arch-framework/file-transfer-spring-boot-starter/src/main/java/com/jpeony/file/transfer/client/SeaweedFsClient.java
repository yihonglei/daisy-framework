package com.jpeony.file.transfer.client;

import com.jpeony.file.transfer.result.FileUploadResult;
import com.jpeony.file.transfer.property.SeaweedUploadProperties;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : yihonglei
 */
public class SeaweedFsClient {

    private static Logger logger = LoggerFactory.getLogger(SeaweedFsClient.class);

    private final static int DEFAULT_CONNECTION_TIMEOUT = 3000;

    private final static int DEFAULT_PORT = 9333;

    private static FileSource fileSource;

    private SeaweedUploadProperties seaweedFsProperties;

    public SeaweedFsClient(SeaweedUploadProperties properties) {
        this(properties.getHost(), properties.getPort(), properties.getConnectTimeout());
        this.seaweedFsProperties = properties;
    }

    /**
     * 初始化seaweedFs连接
     *
     * @param host
     * @param port
     * @param connectionTimeout
     */
    private SeaweedFsClient(String host, Integer port, Integer connectionTimeout) {
        fileSource = new FileSource();
        fileSource.setHost(host);
        fileSource.setPort(port == null ? DEFAULT_PORT : port);
        fileSource.setMasterCluster(true);
        fileSource.setConnectionTimeout(connectionTimeout == null ? DEFAULT_CONNECTION_TIMEOUT : connectionTimeout);
        try {
            fileSource.startup();
        } catch (IOException e) {
            logger.error("seaweedFS connect error.", e);
        }
    }

    /**
     * 通过seaweed上传文件
     *
     * @param inputStream
     * @return
     */
    public FileUploadResult fileUpload(InputStream inputStream) {
        try {
            FileTemplate fileTemplate = new FileTemplate(fileSource.getConnection());
            fileTemplate.setUsingPublicUrl(false);
            FileHandleStatus fileHandleStatus = fileTemplate.saveFileByStream(String.valueOf(System.currentTimeMillis()), inputStream);
            return new FileUploadResult(fileHandleStatus, this.seaweedFsProperties);
        } catch (Exception e) {
            logger.error("SeaweedFS upload file error.", e);
        }
        return null;
    }
}
