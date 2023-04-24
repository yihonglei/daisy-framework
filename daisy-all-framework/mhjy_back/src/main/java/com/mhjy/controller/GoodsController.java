package com.mhjy.controller;

import com.mhjy.entity.Goods;
import com.mhjy.pojo.Dto.*;
import com.mhjy.service.GoodsService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 商品列表
     * @param goodsListDto
     * @return
     */
    @RequestMapping("goodsList")
    public ApiResponse<List<Goods>> goodsList(@RequestBody GoodsListDto goodsListDto){
        List<Goods> list = goodsService.goodsList(goodsListDto.getType());
        return ApiResponse.success(list);
    }

    /**
     * 新增商品
     * @param goodsDto
     * @return
     */
    @RequestMapping("sysAddGoods")
    public ApiResponse<Boolean> sysAddGoods(@RequestBody GoodsDto goodsDto){
        Goods goods = new Goods();
        BeanUtils.copyProperties(goods, goodsDto);
        Boolean flag = goodsService.sysAddGoods(goods);
        return ApiResponse.success(flag);
    }

    /**
     * 更新商品可用性
     * @param sysUpdateGoodsEnableDto
     * @return
     */
    @RequestMapping("sysUpdateGoodsEnable")
    public ApiResponse<Boolean> sysUpdateGoodsEnable(@RequestBody SysUpdateGoodsEnableDto sysUpdateGoodsEnableDto){
        Boolean flag = goodsService.sysUpdateGoodsEnable(sysUpdateGoodsEnableDto.getEnable(), sysUpdateGoodsEnableDto.getId());
        return ApiResponse.success(flag);
    }

    /**
     * 更新商品排序
     * @param sysUpdateGoodsSortDto
     * @return
     */
    @RequestMapping("sysUpdateGoodsSort")
    public ApiResponse<Boolean> sysUpdateGoodsSort(@RequestBody SysUpdateGoodsSortDto sysUpdateGoodsSortDto){
        Boolean flag = goodsService.sysUpdateGoodsSort(sysUpdateGoodsSortDto.getSort(), sysUpdateGoodsSortDto.getId());
        return ApiResponse.success(flag);
    }

    /**
     * 更新商品信息
     * @param goodsDto
     * @return
     */
    @RequestMapping("sysUpdateGoods")
    public ApiResponse<Boolean> sysUpdateGoods(@RequestBody GoodsDto goodsDto){
        Goods goods = new Goods();
        BeanUtils.copyProperties(goods, goodsDto);
        Boolean flag = goodsService.sysUpdateGoods(goods);
        return ApiResponse.success(flag);
    }

    /**
     * 删除商品
     * @param sysDelGoodsDto
     * @return
     */
    @RequestMapping("sysDelGoods")
    public ApiResponse<Boolean> sysDelGoods(@RequestBody SysDelGoodsDto sysDelGoodsDto){
        Boolean flag = goodsService.sysDelGoods(sysDelGoodsDto.getId());
        return ApiResponse.success(flag);
    }

}
