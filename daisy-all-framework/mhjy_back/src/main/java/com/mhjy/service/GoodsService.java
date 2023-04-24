package com.mhjy.service;

import com.mhjy.entity.Goods;
import com.mhjy.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<Goods> goodsList(int type) {
        return goodsMapper.goodsList(type);
    }

    public Boolean sysAddGoods(Goods goods) {
        return goodsMapper.sysAddGoods(goods) == 1;
    }

    public Boolean sysUpdateGoodsEnable(int enable, int id) {
        Date now = new Date();
        return goodsMapper.sysUpdateGoodsEnable(id, enable, now) == 1;
    }

    public Boolean sysUpdateGoodsSort(int sort, int id) {
        Date now = new Date();
        return goodsMapper.sysUpdateGoodsSort(id, sort, now) == 1;
    }

    public Boolean sysUpdateGoods(Goods goods) {
        Date now = new Date();
        goods.setUpdated_at(now);
        return goodsMapper.sysUpdateGoods(goods) == 1;
    }

    public Boolean sysDelGoods(int id) {
        return goodsMapper.sysDelGoods(id) == 1;
    }

    public Goods getGoodsWithGoodId(long id) {
        return goodsMapper.getGoodsWithGoodId(id);
    }
}
