package com.jpeony.shopping.converter;

import com.jpeony.shopping.dal.entitys.Panel;
import com.jpeony.shopping.dal.entitys.PanelContent;
import com.jpeony.shopping.dal.entitys.PanelContentItem;
import com.jpeony.shopping.dto.PanelContentDto;
import com.jpeony.shopping.dto.PanelContentItemDto;
import com.jpeony.shopping.dto.PanelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentConverter {

    @Mappings({})
    PanelContentDto panelContent2Dto(PanelContent panelContent);

    @Mappings({})
    PanelContentDto panelContentItem2Dto(PanelContentItem panelContentItem);

    @Mappings({})
    PanelDto panen2Dto(Panel panel);

    List<PanelContentDto> panelContents2Dto(List<PanelContent> panelContents);

    List<PanelContentItemDto> panelContentItem2Dto(List<PanelContentItem> panelContentItems);

}
