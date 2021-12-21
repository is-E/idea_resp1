package com.hxs.service;

import com.hxs.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    public List<PromotionSpace> findAllPromotionSpace();

    public void addPromotionSpace(PromotionSpace promotionSpace);

    public void updatePromotionSpace(PromotionSpace promotionSpace);

    public PromotionSpace findPromotionSpaceById(Integer id);
}
