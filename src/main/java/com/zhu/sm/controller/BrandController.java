package com.zhu.sm.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.http.AxiosStatus;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.transfer.BrandTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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

    @GetMapping("{id}")
    public AxiosResult<BrandDTO> findById(@PathVariable long id) {
        Brand brand = brandService.findById(id);
        return AxiosResult.success(brandTransfer.toDTO(brand));
    }

    @PostMapping
    public AxiosResult addBrand(@Validated(AddGroup.class) @RequestBody Brand brand) {
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
    public AxiosResult<Void> updateById(@Validated(UpdateGroup.class) @RequestBody Brand brand){
        return toAxios(brandService.update(brand));
    }

    /**
     * 导出表格
     */
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Brand> list = brandService.findAll();
        list.forEach(brand -> {
            try {
                brand.setUrl(new URL("https:"+brand.getBrandLogo()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("品牌Excel", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Brand.class).sheet("品牌").doWrite(list);
    }
}
