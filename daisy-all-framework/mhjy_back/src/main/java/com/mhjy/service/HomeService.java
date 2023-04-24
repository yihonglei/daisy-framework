package com.mhjy.service;

import com.mhjy.entity.Image;
import com.mhjy.entity.Paper;
import com.mhjy.mapper.ImageMapper;
import com.mhjy.mapper.PaperMapper;
import com.mhjy.pojo.Bo.ImageBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ImageMapper imageMapper;

    @Autowired
    PaperMapper paperMapper;



    /**
     * 优秀表白墙
     * @return
     */
    public List<Paper> loadIntro() {
        List<Paper> resultList = paperMapper.chosenList();

        return resultList;
    }
}
