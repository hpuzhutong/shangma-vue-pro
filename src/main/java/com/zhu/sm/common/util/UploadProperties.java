package com.zhu.sm.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/26 22:04
 * @className: UploadPropertise
 * @description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "aliyun")
public class UploadProperties {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;
    private String url;
    private String uploadImgExt;
}
