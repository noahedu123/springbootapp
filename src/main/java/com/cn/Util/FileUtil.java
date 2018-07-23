package com.cn.Util;

import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * 创建存放头像路径文件classpath:static/images/
 */
public class FileUtil {
    public static String createImageUrl(String url) throws  Exception{
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(),url);
        if(!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}
