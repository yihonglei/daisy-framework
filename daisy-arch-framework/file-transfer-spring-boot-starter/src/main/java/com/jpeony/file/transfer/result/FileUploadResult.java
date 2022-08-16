package com.jpeony.file.transfer.result;

import com.jpeony.file.transfer.exception.UploadException;
import com.jpeony.file.transfer.property.FtpUploadProperties;
import com.jpeony.file.transfer.property.SeaweedUploadProperties;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;

/**
 * seaweedFs上传文件结果
 *
 * @author : yihonglei
 */
public class FileUploadResult {
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 文件内部地址
     */
    private String fileInsideUrl;
    /**
     * 文件地址
     */
    private String fileUrl;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileInsideUrl() {
        return fileInsideUrl;
    }

    public void setFileInsideUrl(String fileInsideUrl) {
        this.fileInsideUrl = fileInsideUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public FileUploadResult(FileHandleStatus fileHandleStatus, SeaweedUploadProperties seaweedFsProperties) {
        if (fileHandleStatus == null || fileHandleStatus.getFileId() == null) {
            throw new UploadException("seaweedFs upload fileHandleStatus is null or fileId is null");
        }
        if (seaweedFsProperties.getViewUrl() != null) {
            this.fileUrl = seaweedFsProperties.getViewUrl() + "/" + fileHandleStatus.getFileId();
        }
        if (seaweedFsProperties.getInsideViewUrl() == null) {
            this.fileInsideUrl = fileHandleStatus.getFileUrl();
        } else {
            this.fileInsideUrl = seaweedFsProperties.getInsideViewUrl() + "/" + fileHandleStatus.getFileId();
        }
        this.fileId = fileHandleStatus.getFileId();
    }

    public FileUploadResult(String filePath, FtpUploadProperties ftpProperties) {
        if (ftpProperties.getViewUrl() != null) {
            this.fileUrl = ftpProperties.getViewUrl() + filePath;
        }
        if (ftpProperties.getInsideViewUrl() == null) {
            this.fileInsideUrl = filePath;
        } else {
            this.fileInsideUrl = ftpProperties.getInsideViewUrl() + filePath;
        }
    }
}
