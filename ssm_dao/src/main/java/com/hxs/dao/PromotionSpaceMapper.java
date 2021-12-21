package com.hxs.dao;

import com.hxs.domain.PromotionSpace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PromotionSpaceMapper {
    //获取广告位列表数据
    public List<PromotionSpace> findAll();

    //添加广告位
    public void addPromotionSpace(PromotionSpace promotionSpace);

    //修改广告位信息
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //通过id查询广告位信息
    public PromotionSpace findPromotionSpaceById(@Param("id") Integer id);
}
