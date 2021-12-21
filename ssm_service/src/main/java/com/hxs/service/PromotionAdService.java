package com.hxs.service;


import com.github.pagehelper.PageInfo;
import com.hxs.domain.PromotionAd;
import com.hxs.domain.PromotionAdvo;

public interface PromotionAdService {
    public PageInfo<PromotionAd> findAll(PromotionAdvo promotionAdvo);

    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
