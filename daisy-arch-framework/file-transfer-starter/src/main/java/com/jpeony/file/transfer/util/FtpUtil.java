package com.jpeony.file.transfer.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * ftp工具类
 *
 * @author : yihonglei
 */
public class FtpUtil {

    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private FTPClient ftpClient = null;

    public FtpUtil(String url, String userName, String password) {
        connectFTP(url, userName, password);
    }

    public FtpUtil(String url, int port, String userName, String password) {
        connectFTP(url, port, userName, password);
    }

    /**
     * 连接ftp服务器
     *
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public boolean connectFTP(String url, String userName, String password) {
        ftpClient = new FTPClient();
        try {
            // 连接
            ftpClient.connect(url);
            return login(userName, password);
        } catch (Exception e) {
            logger.error("ftp connect error", e);
        }
        return false;
    }

    /**
     * 连接ftp服务器
     *
     * @param url
     * @param port
     * @param userName
     * @param password
     * @return
     */
    public boolean connectFTP(String url, int port, String userName, String password) {
        ftpClient = new FTPClient();
        try {
            // 连接
            ftpClient.connect(url, port);
            return login(userName, password);
        } catch (Exception e) {
            logger.error("ftp connect error", e);
        }
        return false;
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean login(String userName, String password) {
        if (ftpClient == null || !ftpClient.isConnected()) {
            return false;
        }
        try {
            // 登陆
            return ftpClient.login(userName, password);
        } catch (IOException e) {
            logger.error("ftp login error", e);
        }
        return false;
    }

    /**
     * 切换目录
     *
     * @param path
     */
    public boolean changeDirectory(String path) {
        if (ftpClient == null || !ftpClient.isConnected()) {
            logger.warn("ftp not connect");
            return false;
        }
        try {
            if (!ftpClient.changeWorkingDirectory(path)) {
                return makeDirectory(path);
            }
            return true;
        } catch (IOException e) {
            logger.error("ftp change directory error", e);
            return makeDirectory(path);
        }
    }

    /**
     * 新建并切换路径
     *
     * @param path
     * @return
     */
    public boolean makeDirectory(String path) {
        if (ftpClient == null || !ftpClient.isConnected()) {
            logger.warn("ftp not connect");
            return false;
        }
        try {
            ftpClient.makeDirectory(path);
            return ftpClient.changeWorkingDirectory(path);
        } catch (IOException e) {
            logger.error("ftp make or change directory error Exception ", e);
            return false;
        }
    }

    /**
     * 关闭ftp
     */
    public void disconnectFTP() {
        if (ftpClient == null || !ftpClient.isConnected()) {
            return;
        }
        try {
            ftpClient.disconnect();
            logger.info("disconnect success!!");
        } catch (IOException e) {
            logger.error("ftp error IOException ", e);
        }
    }

    /**
     * 上传文件
     *
     * @param fileName
     * @param inputStream
     * @return
     */
    public boolean upload(String fileName, InputStream inputStream) {
        if (ftpClient == null || !ftpClient.isConnected()) {
            logger.warn("ftp not connect");
            return false;
        }
        boolean result = false;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 将ftp文件加入输出流中。输出到ftp上
            result = ftpClient.storeFile(fileName, inputStream);
            logger.info("ftp upload {}", result);
        } catch (IOException e) {
            logger.error("ftp upload error IOException ", e);
        } finally {
            disconnectFTP();
        }
        return result;
    }

    /**
     * 上传文件，指定路径。上传前必须已连接
     *
     * @param fileName
     * @param path
     * @param inputStream
     * @return
     */
    public boolean upload(String fileName, String path, InputStream inputStream) {
        if (path == null || "".equals(path)) {
            logger.error("ftp upload path is null, root is not allowed upload");
            return false;
        }
        if (!changeDirectory(path)) {
            logger.error("ftp file path make fail, upload not allowed");
            return false;
        }
        return upload(fileName, inputStream);
    }

    /**
     * 上传文件，参数包含ftp地址及用户名密码
     *
     * @param host
     * @param userName
     * @param password
     * @param fileName
     * @param path
     * @param inputStream
     * @return
     */
    public boolean upload(String host, String userName, String password, String fileName, String path, InputStream inputStream) {
        if (!connectFTP(host, userName, password)) {
            logger.warn("ftp connect fail");
            return false;
        }
        return upload(fileName, path, inputStream);
    }

    /**
     * 上传文件，参数包含ftp地址、端口号及用户名密码
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param fileName
     * @param path
     * @param inputStream
     * @return
     */
    public boolean upload(String host, int port, String userName, String password, String fileName, String path, InputStream inputStream) {
        if (!connectFTP(host, port, userName, password)) {
            logger.warn("ftp connect fail");
            return false;
        }
        return upload(fileName, path, inputStream);
    }
}
