package com.zhu.sm.controller;

import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.transfer.BrandTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:18
 * @className: BrandController
 * @description:
 */
@RestController
@RequestMapping("brand")

public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandTransfer brandTransfer;

    @GetMapping("searchPage")
    public AxiosResult<PageBean<BrandDTO>> searchPage(BrandQuery brandQuery){
        PageHelper.startPage(brandQuery.getCurrentPage(),brandQuery.getPageSize());
        PageBean<BrandDTO> pageBean = brandService.searchPage(brandQuery);
        return AxiosResult.success(pageBean);
    }

//    @GetMapping
//    public AxiosResult<List<BrandDTO>> findAll() {
//        return AxiosResult.success(brandService.findAll());
//    }
//
    @GetMapping("{id}")
    public AxiosResult<BrandDTO> findById(@PathVariable long id) {
        Brand brand = brandService.findById(id);
        return AxiosResult.success(brandTransfer.toDTO(brand));
    }

    @PostMapping
    public AxiosResult<Void> addBrand(@RequestBody Brand brand) {
//        brand.setCreateBy(1);
//        brand.setCreateTime(LocalDateTime.now());
        return toAxios(brandService.add(brand));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id) {
        return toAxios(brandService.deleteById(id));
    }

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
        return toAxios(brandService.batchDelByIds(ids));
    }

    @PutMapping
    public AxiosResult<Void> updateById(@RequestBody Brand brand){
        return toAxios(brandService.update(brand));
    }


}
