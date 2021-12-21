package com.hxs.service.impl;

import com.hxs.dao.PromotionSpaceMapper;
import com.hxs.domain.PromotionSpace;
import com.hxs.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper spaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return spaceMapper.findAll();
    }

    @Override
    public void addPromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setIsDel(0);
        spaceMapper.addPromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        spaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace space = spaceMapper.findPromotionSpaceById(id);
        return space;
    }
}
