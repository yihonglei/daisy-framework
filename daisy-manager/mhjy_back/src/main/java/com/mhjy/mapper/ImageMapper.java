package com.mhjy.mapper;

import com.mhjy.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ImageMapper {

    List<Image> imageList(int place);

    int sysAddImage(Image image);

    List<Image> sysImageList();

    int sysUpdateEnable(int enable, long id, Date updated_at);

    int sysDeleteImage(long id);

    int sysUpdateImage(Image image);

}
