package com.cn.Util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class HttpUtil {
    /**
     * 上传文件到指定服务器
     * @param ip 连接ip
     * @param user 用户名
     * @param psw 密码
     * @param port 端口 <=0 为默认端口
     * @param fielPath 上传的服务器路径
     * @param serverFileName 服务器保存的文件名
     * @param instream 要上传的文件流
     * @throws Exception
     */
    public static void sftpFileUpload(String ip,int port, String user, String psw, String fielPath,String serverFileName,InputStream instream) throws Exception {
        Session session = getSession( ip,  user,  psw,  port);
        Channel channel = null;
        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;
            //进入服务器指定的文件夹
            sftp.cd(fielPath);
            OutputStream outstream = sftp.put(serverFileName);
            byte b[] = new byte[1024*200];//每次传输200k
            int n;
            while ((n = instream.read(b)) != -1) {
                outstream.write(b, 0, n);
            }
            outstream.flush();
            outstream.close();
            instream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            if (channel!=null)channel.disconnect();
        }
    }
    /**
     * 从指定服务器下载文件到本地
     * @param ip 连接ip
     * @param user 用户名
     * @param psw 密码
     * @param port 端口 <=0 为默认端口
     * @param fielPath 服务器文件路径
     * @param serverFileName 要下载的文件名
     * @param outputStream 输出到本地的文件流
     * @throws Exception
     */
    public static void sftpFileDownload(String ip,int port, String user, String psw, String fielPath,String serverFileName,OutputStream outputStream) throws Exception {
        Session session =getSession( ip,  user,  psw,  port);
        Channel channel = null;
        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;
            //进入服务器指定的文件夹
            sftp.cd(fielPath);
            sftp.get(serverFileName,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            if (channel!=null)channel.disconnect();
        }
    }
    /**
     * 删除服务器指定文件
     * @param ip 连接ip
     * @param user 用户名
     * @param psw 密码
     * @param port 端口 <=0 为默认端口
     * @param fielPath 服务器文件路径
     * @param serverFileName 要删除的文件名
     * @throws Exception
     */
    public static void sftpFileDelete(String ip,int port, String user, String psw, String fielPath,String serverFileName) throws Exception {
        Session session =getSession( ip,  user,  psw,  port);
        Channel channel = null;
        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;
            //进入服务器指定的文件夹
            sftp.cd(fielPath);
            sftp.rm(serverFileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            if (channel!=null)channel.disconnect();
        }
    }
    /**
     * 连接服务器
     * @param ip 服务器地址
     * @param user 用户名
     * @param psw  密码
     * @param port  连接端口
     * @return
     * @throws Exception
     */
    public static Session getSession(String ip, String user, String psw, int port)throws Exception{
        Session session = null;
        JSch jsch = new JSch();
        if (port <= 0) {
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        } else {
            session = jsch.getSession(user, ip, port);
        }
        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("sftp session is null");
        }
        session.setPassword(psw);//设置密码
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("UseDNS", "no");

        session.setConfig(config);
        //设置登陆超时时间
        session.connect(30000);//30s
        return  session;
    }
}
