package com.hxs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxs.dao.PromotionAdMapper;
import com.hxs.domain.PromotionAd;
import com.hxs.domain.PromotionAdvo;
import com.hxs.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper adMapper;

    @Override
    public PageInfo<PromotionAd> findAll(PromotionAdvo promotionAdvo) {
        PageHelper.startPage(promotionAdvo.getCurrentPage(), promotionAdvo.getPageSize());
        List<PromotionAd> list = adMapper.findAll();
        PageInfo<PromotionAd> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public void updatePromotionAdStatus(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());
        adMapper.updatePromotionAdStatus(promotionAd);
    }
}
