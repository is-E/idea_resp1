package com.hxs.comtroller;

import com.hxs.domain.PromotionSpace;
import com.hxs.domain.ResponseResult;
import com.hxs.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService spaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> list = spaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        System.out.println(promotionSpace);
        if (promotionSpace.getId() == null) {
            spaceService.addPromotionSpace(promotionSpace);
        } else {
            spaceService.updatePromotionSpace(promotionSpace);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id) {
        PromotionSpace space = spaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", space);
        return result;
    }
}
