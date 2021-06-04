package com.cl.gzshop.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author l
 * @Date 2020/9/29 09:44
 */
public class FtpUtil {

    static Logger logger= LoggerFactory.getLogger(FtpUtil.class);

    /**
     *
     * @param ftpUrl
     * @param ftpPort
     * @param ftpUserName
     * @param ftpPassword
     * @param ftpPath
     * @param inputStream
     * @param fileName
     * @param la_lp
     * @return
     */
    public static boolean fileUploadByFtp(String ftpUrl, int ftpPort, String ftpUserName, String ftpPassword, String ftpPath, InputStream inputStream, String fileName, boolean la_lp ){
        FTPClient ftpClient = null;
        boolean storeFile=false;
        try {
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding("UTF-8");
            int reply;
            ftpClient.connect(ftpUrl, ftpPort);// 连接FTP服务器
            boolean login = ftpClient.login(ftpUserName, ftpPassword);    //登录
            if(login){
                logger.info("ftp登录成功");
                logger.info("ftp当前路径："+ftpClient.printWorkingDirectory());
                reply = ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftpClient.disconnect();
                }
                boolean setFileType = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                boolean changeWorkingDirectory2 = ftpClient.changeWorkingDirectory("/");
                logger.info("切换到根目录");
                logger.info("ftp当前路径："+ftpClient.printWorkingDirectory());
                boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(ftpPath);
                if(changeWorkingDirectory){
                    logger.info("changeWorkingDirectory");
                    logger.info("ftp当前路径："+ftpClient.printWorkingDirectory());
                    ftpClient.setFileTransferMode(ftpClient.BINARY_FILE_TYPE);
                    if(la_lp){
                        logger.info("设置为本地主动模式");
                        ftpClient.enterLocalActiveMode();
                    }else{
                        logger.info("设置本地为被动模式");
                        ftpClient.enterLocalPassiveMode();  //设置本地为被动模式
                    }
                    storeFile = ftpClient.storeFile(ftpPath+fileName, inputStream);
                    reply=ftpClient.getReplyCode();     //得到返回参数
                    logger.info("上传"+fileName+"是否成功：" + storeFile+","+reply);
                    return storeFile;
                }else{
                    logger.info("切换目录失败");
                }
            }else {
                logger.info("ftp登录失败");
            }
        } catch (Exception e) {
            logger.error("上传至ftp有误：",e);
        } finally {

        }
        return storeFile;
    }

    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            ftp.enterLocalPassiveMode();  //设置本地为被动模式
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }


}
