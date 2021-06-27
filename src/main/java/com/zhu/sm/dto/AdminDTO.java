package com.zhu.sm.dto;

import com.zhu.sm.dto.base.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/24 13:00
 * @className: BrandDTO
 * @description:
 */


@Data
public class AdminDTO extends BaseDTO {

    private String adminAccount;

    private String adminName;

    private Integer gender;

    private String adminPhone;

    private String adminCode;

    private String adminEmail;

    private String adminAvatar;

    private Boolean isActive;

    private Boolean isAdmin;

    private Double adminSalary;

    private String adminAddress;

    private List<Long> roleIds;

}
