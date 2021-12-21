package com.hxs.dao;

import com.hxs.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    //分页 查询广告列表
    public List<PromotionAd> findAll();
    //修改广告上下线状态
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
