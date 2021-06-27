package com.zhu.sm.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/26 10:07
 * @className: Upload
 * @description:
 */

public class UploadUtils {

    public static String uploadImg(String fileName, InputStream in) {
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tKVVu8yPJbDBuNdk5bu";
        String accessKeySecret = "c1A1vBMmtdUBdcSDXMSO0dNOcvww6F";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //第一参数： 表示bucket（桶）名称
        //第二个参数： 文件名称 携带后缀
        ossClient.putObject("shangmasanshiqi", fileName, in);
        ossClient.shutdown();
        // !niyade是水印url后缀连接
        String url = "https://shangmasanshiqi.oss-cn-beijing.aliyuncs.com/" + fileName /*+ "!niyade"*/;
        return url;
    }
}
