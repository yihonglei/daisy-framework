package com.jpeony.shopping.services;

import com.alibaba.fastjson.JSON;
import com.jpeony.shopping.IHomeService;
import com.jpeony.shopping.constant.GlobalConstants;
import com.jpeony.shopping.constants.ShoppingRetCode;
import com.jpeony.shopping.converter.ContentConverter;
import com.jpeony.shopping.dal.entitys.*;
import com.jpeony.shopping.dal.entitys.Panel;
import com.jpeony.shopping.dal.entitys.PanelContentItem;
import com.jpeony.shopping.dal.persistence.PanelContentMapper;
import com.jpeony.shopping.dal.persistence.PanelMapper;
import com.jpeony.shopping.dto.HomePageResponse;
import com.jpeony.shopping.dto.PanelDto;
import com.jpeony.shopping.services.cache.CacheManager;
import com.jpeony.shopping.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    PanelMapper panelMapper;
    @Autowired
    PanelContentMapper panelContentMapper;
    @Autowired
    ContentConverter contentConverter;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    CacheManager cacheManager;

    @Override
    public HomePageResponse homepage() {
        log.info("Begin HomeServiceImpl.homepage");
        HomePageResponse response=new HomePageResponse();
        response.setCode(ShoppingRetCode.SUCCESS.getCode());
        response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        try {
            String json= cacheManager.checkCache(GlobalConstants.HOMEPAGE_CACHE_KEY);
            if(StringUtils.isNoneEmpty(json)){
                List<PanelDto> panelDtoList=JSON.parseArray(json,PanelDto.class);
                Set set=new HashSet(panelDtoList);
                response.setPanelContentItemDtos(set);
                return response;
            }
            Example panelExample = new Example(Panel.class);
            Example.Criteria criteria = panelExample.createCriteria();
            criteria.andEqualTo("position", 0);
            criteria.andEqualTo("status", 1);
            panelExample.setOrderByClause("sort_order");
            List<Panel> panels = panelMapper.selectByExample(panelExample);
            Set<PanelDto> panelContentItemDtos = new HashSet<PanelDto>();
            panels.parallelStream().forEach(panel -> {
                List<PanelContentItem> panelContentItems = panelContentMapper.selectPanelContentAndProductWithPanelId(panel.getId());
                PanelDto panelDto = contentConverter.panen2Dto(panel);
                panelDto.setPanelContentItems(contentConverter.panelContentItem2Dto(panelContentItems));
                panelContentItemDtos.add(panelDto);
            });
            cacheManager.setCache(GlobalConstants.HOMEPAGE_CACHE_KEY,JSON.toJSONString(panelContentItemDtos),GlobalConstants.HOMEPAGE_EXPIRE_TIME);
            response.setPanelContentItemDtos(panelContentItemDtos);
        }catch (Exception e){
            log.error("HomeServiceImpl.homepage Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }




}
