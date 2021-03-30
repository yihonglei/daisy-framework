package com.jpeony.shopping.dal.persistence;

import com.jpeony.commons.tool.tkmapper.TkMapper;
import com.jpeony.shopping.dal.entitys.Panel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PanelMapper extends TkMapper<Panel> {

    List<Panel> selectPanelContentById(@Param("panelId")Integer panelId);
}