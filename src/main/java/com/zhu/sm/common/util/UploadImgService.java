package com.zhu.sm.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zhu.sm.common.exception.ApiException;
import com.zhu.sm.common.http.AxiosStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/26 22:03
 * @className: uploadImgService
 * @description:
 */

@Component
public class UploadImgService {

    @Autowired
    private UploadProperties uploadProperties;

    public String uploadImg(Part part) throws ApiException {
        try{
            //判断上传的是一个图片
            BufferedImage read = ImageIO.read(part.getInputStream());
            if (read == null) {
                throw new ApiException(AxiosStatus.NOT_IMG);
            }
            if (!uploadProperties.getUploadImgExt().contains(StringUtils.getFilenameExtension(part.getSubmittedFileName()))) {
                throw new ApiException(AxiosStatus.IMG_TYPE_ERROR);
            }
            String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(part.getSubmittedFileName());
            OSS ossClient = new OSSClientBuilder().build(uploadProperties.getEndPoint(), uploadProperties.getAccessKeyId(), uploadProperties.getAccessKeySecret());
            //第一参数： 表示bucket（桶）名称
            //第二个参数： 文件名称 携带后缀
            ossClient.putObject(uploadProperties.getBucket(), fileName, part.getInputStream());
            ossClient.shutdown();
            // !niyade是水印url后缀连接
            String url = uploadProperties.getUrl() + fileName;
            return url;
        }catch (IOException e){
            throw new ApiException(AxiosStatus.IMG_UPLOAD_ERROR);
        }


    }
}
