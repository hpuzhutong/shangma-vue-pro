package com.zhu.sm.dto;

import com.zhu.sm.dto.base.BaseDTO;
import lombok.Data;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/24 13:00
 * @className: BrandDTO
 * @description:
 */


@Data
public class BrandDTO extends BaseDTO {

    private String brandName;
    private String brandSite;
    private String brandDesc;
    private String brandLogo;
}
