package com.mhjy.service;

import com.mhjy.entity.Suggest;
import com.mhjy.entity.User;
import com.mhjy.mapper.SuggestMapper;
import com.mhjy.pojo.Bo.SuggestBO;
import com.mhjy.pojo.Dto.SuggestDto;
import com.mhjy.pojo.Dto.SysMessageListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SuggestService {
    @Autowired
    SuggestMapper suggestMapper;

    @Autowired
    UserService userService;

    public Boolean publishSuggest(SuggestDto suggestDto) {
        Suggest suggest = new Suggest();
        suggest.setSuggest_uid(suggestDto.getSuggest_uid());
        suggest.setContent(suggestDto.getContent());
        suggest.setCreated_at(new Date());
        suggest.setUpdated_at(new Date());
        return suggestMapper.publishSuggest(suggest) == 1;
    }

    public List<SuggestBO> sysMessageList(SysMessageListDto sysMessageListDto) {
        Date startDate = sysMessageListDto.getStartDate();
        Date endDate = sysMessageListDto.getEndDate();
        List<Suggest> list = suggestMapper.sysMessageList(startDate, endDate);

        List<SuggestBO> result = new ArrayList<>();
        for (Suggest suggest:list) {
            SuggestBO suggestBO = new SuggestBO();
            BeanUtils.copyProperties(suggest, suggestBO);
            long uid = suggest.getSuggest_uid();

            User user = userService.getUserInfoWithUid(uid);
            suggestBO.setSuggest_nickname(user.getNickname());
            result.add(suggestBO);
        }
        return result;
    }
}
