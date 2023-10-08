package com.mhjy.service;

import com.mhjy.entity.Image;
import com.mhjy.mapper.ImageMapper;
import com.mhjy.pojo.Bo.ImageBO;
import com.mhjy.pojo.Dto.SysAddImageDto;
import com.mhjy.pojo.Dto.SysUpdateEnableDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageMapper imageMapper;

    /**
     * 图片列表
     * @param place
     * @return
     */
    public List<ImageBO> imageList(int place) {
        List<ImageBO> resultList = new ArrayList<>();
        List<Image> list = imageMapper.imageList(place);

        for (Image image:list) {
            ImageBO imageBO = new ImageBO();
            BeanUtils.copyProperties(image, imageBO);
            resultList.add(imageBO);
        }

        return resultList;
    }

    public Boolean sysAddImage(SysAddImageDto sysAddImageDto) {
        Image image = new Image();
        BeanUtils.copyProperties(sysAddImageDto, image);
        Date now = new Date();
        image.setCreated_at(now);
        image.setUpdated_at(now);

        return imageMapper.sysAddImage(image) == 1;
    }

    public List<ImageBO> sysImageList() {
        List<ImageBO> resultList = new ArrayList<>();
        List<Image> list = imageMapper.sysImageList();

        for (Image image:list) {
            ImageBO imageBO = new ImageBO();
            BeanUtils.copyProperties(image, imageBO);
            resultList.add(imageBO);
        }

        return resultList;
    }

    public Boolean sysUpdateEnable(SysUpdateEnableDto sysUpdateEnableDto) {
        Date now = new Date();
        int enable = sysUpdateEnableDto.getEnable();
        long id = sysUpdateEnableDto.getId();
        return imageMapper.sysUpdateEnable(enable, id, now) == 1;
    }

    public Boolean sysDeleteImage(long id) {
        Date now = new Date();
        return imageMapper.sysDeleteImage(id) == 1;
    }


    public Boolean sysUpdateImage(SysAddImageDto sysAddImageDto) {
        Image image = new Image();
        BeanUtils.copyProperties(sysAddImageDto, image);
        Date now = new Date();
        image.setUpdated_at(now);

        return imageMapper.sysUpdateImage(image) == 1;
    }
}
