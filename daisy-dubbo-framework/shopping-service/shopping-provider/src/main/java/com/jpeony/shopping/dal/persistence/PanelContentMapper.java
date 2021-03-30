package com.jpeony.shopping.dal.persistence;

import com.jpeony.commons.tool.tkmapper.TkMapper;
import com.jpeony.shopping.dal.entitys.PanelContent;

import java.util.List;

import com.jpeony.shopping.dal.entitys.PanelContentItem;
import org.apache.ibatis.annotations.Param;

public interface PanelContentMapper extends TkMapper<PanelContent> {

    List<PanelContentItem> selectPanelContentAndProductWithPanelId(@Param("panelId")Integer panelId);
}