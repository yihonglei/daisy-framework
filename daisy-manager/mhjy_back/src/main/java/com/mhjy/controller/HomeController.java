package com.mhjy.controller;

import com.mhjy.entity.Paper;
import com.mhjy.pojo.Bo.ImageBO;
import com.mhjy.pojo.Dto.ImagePlaceDto;
import com.mhjy.service.HomeService;
import com.mhjy.service.ImageService;
import com.mhjy.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    /**
     * test
     */
    @RequestMapping("test")
    public String test(){
        return "success";
    }

    @Autowired
    private HomeService homeService;

    @Autowired
    private ImageService imageService;
    /**
     * 首页轮播图列表
     * @param imagePlaceDto
     * @return
     */
    @RequestMapping("imageList")
    public ApiResponse<List<ImageBO>> imageList(@RequestBody ImagePlaceDto imagePlaceDto){
        List<ImageBO> list = imageService.imageList(imagePlaceDto.getPlace());
        return ApiResponse.success(list);
    }

    /**
     * 宣言轮播图列表
     * @return
     */
    @RequestMapping("loadIntro")
    public ApiResponse<List<Paper>> loadIntro(){
        List<Paper> list = homeService.loadIntro();
        return ApiResponse.success(list);
    }
}
