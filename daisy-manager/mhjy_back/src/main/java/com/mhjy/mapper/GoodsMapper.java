package com.mhjy.mapper;

import com.mhjy.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GoodsMapper {

    List<Goods> goodsList(int type);

    int sysAddGoods(Goods goods);

    int sysUpdateGoodsEnable(int id, int enable, Date updated_at);
    int sysUpdateGoodsSort(int id, int sort, Date updated_at);
    int sysUpdateGoods(Goods goods);

    int sysDelGoods(int id);

    Goods getGoodsWithGoodId(long id);

}
