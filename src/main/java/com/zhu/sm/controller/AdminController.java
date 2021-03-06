package com.zhu.sm.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.http.AxiosStatus;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.util.UploadUtils;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.AdminDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.query.AdminQuery;
import com.zhu.sm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
@RequestMapping("admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("searchPage")
    public AxiosResult<PageBean<AdminDTO>> searchPage(AdminQuery adminQuery) {
        PageHelper.startPage(adminQuery.getCurrentPage(), adminQuery.getPageSize());
        PageBean<AdminDTO> pageBean = adminService.searchPage(adminQuery);
        return AxiosResult.success(pageBean);
    }

    //TODO
    @GetMapping
    public AxiosResult<List<Admin>> findAll() {
        return AxiosResult.success(adminService.findAll());
    }

    @GetMapping("{id}")
    public AxiosResult<AdminDTO> findById(@PathVariable long id) {
        AdminDTO adminDTO = adminService.getAdminAndRoleByAdminId(id);
        return AxiosResult.success(adminDTO);
    }

    /**
     * MD5??????
     *
     * @param admin
     * @return
     */
    @PostMapping
    public AxiosResult addAdmin(@Validated(AddGroup.class) @RequestBody Admin admin) {
        //??????????????? ??????  ????????? ????????????????????????    ?????????????????????
        boolean row = adminService.hasAccountEmailPhone(admin);
        if (row) {
            return AxiosResult.error(AxiosStatus.USED_HAS_EXIST);
        } else {
            if (admin.getIsActive() == null) {
                admin.setIsActive(false);
            }
            if (admin.getIsAdmin() == null) {
                admin.setIsAdmin(false);
            }
            String encode = bCryptPasswordEncoder.encode("123");
            admin.setAdminPassword(encode);

//            return toAxios(adminService.add(admin));

            return toAxios(adminService.addAdminAndAdminRole(admin));
        }

//        int row = adminService.findByAccount(admin.getAdminAccount());
//        if (row == 1) {
//            return AxiosResult.error(AxiosStatus.ACCOUNT_USED_ERROR);
//        } else {
//            if (admin.getIsActive() == null) {
//                admin.setIsActive(false);
//            }
//            if (admin.getIsAdmin() == null) {
//                admin.setIsAdmin(false);
//            }
//            String encode = bCryptPasswordEncoder.encode("123");
//            admin.setAdminPassword(encode);
//            return toAxios(adminService.add(admin));
//        }
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id) {

//        return toAxios(adminService.deleteById(id));

        return toAxios(adminService.deleteAdminAndRoleByAdminId(id));

    }

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
//        return toAxios(adminService.batchDelByIds(ids));

        return toAxios(adminService.batchDeleteAdminAndRolesByAdminIds(ids));
    }

    @PutMapping
    public AxiosResult<Void> updateById(@Validated(UpdateGroup.class) @RequestBody Admin admin) {
//        return toAxios(adminService.update(admin));

        return toAxios(adminService.updateAdminAndRole(admin));
    }

    /**
     * ????????????
     *
     * @param avatar
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part avatar) throws IOException {
        //?????????
        //  ??????????????????????????????????????????
        BufferedImage read = ImageIO.read(avatar.getInputStream());
        if (read == null) {
            return AxiosResult.error(AxiosStatus.NOT_IMG);
        } else {
            //?????????
            String filename = System.nanoTime() + "." + StringUtils.getFilenameExtension(avatar.getSubmittedFileName());
            return AxiosResult.success(UploadUtils.uploadImg(filename, avatar.getInputStream()));
        }

        //?????????   ????????????  ????????????null
//        return AxiosResult.success(uploadImgService.uploadImg(avatar));
    }

    /**
     * ????????????
     */
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Admin> list = adminService.findAll();
        //???????????? 0 1 ????????? ??? ???
        list.forEach(admin -> {
            admin.setSex(admin.getGender() == 0 ? "???" : "???");
            try {
                admin.setUrl(new URL(admin.getAdminAvatar()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        // ???????????? ?????????????????????swagger ??????????????????????????????????????????????????????postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // ??????URLEncoder.encode???????????????????????? ?????????easyexcel????????????
        String fileName = URLEncoder.encode("??????????????????", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Admin.class).sheet("????????????").doWrite(list);
    }


}
