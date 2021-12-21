package com.hxs.comtroller;

import com.github.pagehelper.PageInfo;
import com.hxs.domain.PromotionAd;
import com.hxs.domain.PromotionAdvo;
import com.hxs.domain.ResponseResult;
import com.hxs.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService adService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdvo promotionAdvo) {
        PageInfo<PromotionAd> info = adService.findAll(promotionAdvo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", info);
        return result;
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1、判断file是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2、获取项目路径
        String realPath = request.getServletContext().getRealPath("/");
        String newPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3、获取文件名
        String name = file.getOriginalFilename();
        String newName = System.currentTimeMillis() + "_" + name;

        //4、文件上传
        String uploadPath = newPath + "\\upload";
        File filePath = new File(uploadPath, newName);
//        System.out.println("要上传的地址" + uploadPath);
//        System.out.println("上传后的文件路径" + filePath);
//
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }

        //文件上传
        file.transferTo(filePath);

        Map<String, String> map = new HashMap<>();
        map.put("fileName", newName);
        map.put("filePath", "http://localhost:8080/upload/" + newName);

        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;
    }

    //修改广告上下线状态
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd) {
        adService.updatePromotionAdStatus(promotionAd);
        Map<String, Object> map = new HashMap<>();
        map.put("status", promotionAd.getStatus());
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
}
