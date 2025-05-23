package com.nanting.point.nantingdianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanting.point.nantingdianping.dto.Result;
import com.nanting.point.nantingdianping.entity.Shop;

/**
 * @Description:
 * @Author: zbj
 * @Date: 2025/4/28
 */
public interface IShopService extends IService<Shop> {

    /**
     * 根据id查询商户信息
     *
     * @param id id
     * @return {@link Result}
     */
    Result queryById(Long id);

    /**
     * 更新店铺信息
     *
     * @param shop 商店
     * @return {@link Result}
     */
    Result update(Shop shop);

    /**
     * 按类型查询商店
     *
     * @param typeId  id类型
     * @param current 当前
     * @param x       x
     * @param y       y
     * @return {@link Result}
     */
    Result queryShopByType(Integer typeId, Integer current, Double x, Double y);
}
