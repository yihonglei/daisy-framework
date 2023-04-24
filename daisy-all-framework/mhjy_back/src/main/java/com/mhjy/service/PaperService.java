package com.mhjy.service;

import com.mhjy.entity.*;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.PaperMapper;
import com.mhjy.pojo.Bo.PaperHistoryBO;
import com.mhjy.pojo.Bo.SysPaperBO;
import com.mhjy.pojo.Bo.SysUserBO;
import com.mhjy.pojo.Dto.*;
import com.mhjy.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    PaperHistoryService paperHistoryService;

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    UserService userService;

    public Paper paperWithId(long id) {
        return paperMapper.paperWithId(id);
    }

    public Paper pushPaper(PaperDto paperDto) throws BizException {

        Paper paper0 = paperMapper.paperWithVxnum(paperDto.getVxnum());
        long uid = paperDto.getUid();
        int sex = paperDto.getSex();
        int money = paperDto.getMoney();
        String vxnum = paperDto.getVxnum();
        Date now = new Date();

        SysCompanyUser sysCompanyUser = sysCompanyUserService.userInfoWithUid(uid);
        long cid = sysCompanyUser.getCid();
        if (paper0 == null) {
            Paper paper = new Paper();
            paper.setPortraits(paperDto.getPortraits());
            paper.setUid(uid);
            paper.setCid(cid);
            paper.setVxnum(vxnum);
            paper.setMoney(money);
            paper.setIntro(paperDto.getIntro());
            paper.setSex(sex);
            paper.setStatus(0);
            paper.setCreated_at(now);
            paper.setUpdated_at(now);
            Boolean flag = paperMapper.pushPaper(paper) == 1;
            Paper paper1 = paperMapper.paperWithUidAndVxnum(uid, vxnum, 0);
//            long paperid = paper1.getId();
//
//            PaperHistory paperHistory = new PaperHistory();
//            paperHistory.setUid(uid);
//            paperHistory.setCid(cid);
//            paperHistory.setPaperid(paperid);
//            paperHistory.setType(1);
//            paperHistory.setCreated_at(now);
//            paperHistory.setUpdated_at(now);
//            paperHistoryService.addPaperHistory(paperHistory);

            return paper1;
        } else {
            throw new BizException(ErrorCodeEnum.PAPER_PUSH_ERROR.getCode(), ErrorCodeEnum.PAPER_PUSH_ERROR.getMsg());
        }
    }

    public Paper pullPaper(PullPaperDto pullPaperDto) {
        long uid = pullPaperDto.getUid();
        int sex = pullPaperDto.getSex();

        List<Paper> list = paperMapper.pullPaper(sex);
        int num = list.size();
        if (num == 0) {
            return null;
        } else {
            int index = (int) (Math.random()%num);
            Paper paper = list.get(index);

//        Date now = new Date();
//        PaperHistory paperHistory = new PaperHistory();
//        paperHistory.setUid(uid);
//        paperHistory.setPaperid(paper.getId());
//        paperHistory.setType(2);
//        paperHistory.setCreated_at(now);
//        paperHistory.setUpdated_at(now);
//        paperHistoryService.addPaperHistory(paperHistory);

            return paper;
        }
    }

    public List<PaperHistoryBO> paperHistory(PaperHistoryDto paperHistoryDto) {
        long uid = paperHistoryDto.getUid();
        int type = paperHistoryDto.getType();
        List result = new ArrayList();
        List<PaperHistory> list = paperHistoryService.paperHistory(uid, type);
        for (int i = 0; i < list.size(); i++) {
            PaperHistory paperHistory = list.get(i);
            long paperid = paperHistory.getPaperid();
            Paper paper = paperMapper.paperWithid(paperid);
            PaperHistoryBO paperHistoryBO = new PaperHistoryBO();
            paperHistoryBO.setUid(uid);
            paperHistoryBO.setPaperid(paperid);
            paperHistoryBO.setType(type);
            paperHistoryBO.setSex(paper.getSex());
            paperHistoryBO.setPortraits(paper.getPortraits());
            paperHistoryBO.setIntro(paper.getIntro());
            paperHistoryBO.setStatus(paper.getStatus());
            paperHistoryBO.setChosen(paper.getChosen());
            paperHistoryBO.setVxnum(paper.getVxnum());
            paperHistoryBO.setCreated_time(DateUtils.formatString(paper.getCreated_at(), DateUtils.TIME_DATE));
            paperHistoryBO.setUpdated_time(DateUtils.formatString(paper.getUpdated_at(), DateUtils.TIME_DATE));

            result.add(paperHistoryBO);
        }

        return result;
    }

    public Boolean updatePaperStatus(int status, long id) {
        Date now = new Date();
        return paperMapper.updatePaperEnable(status, id, now) == 1;
    }

    public List<SysPaperBO> sysPushPaperList(String token, SysPaperListDto sysPaperListDto) throws Exception {
        List<SysPaperBO> result = new ArrayList<>();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Boolean containAdmin = false;
        for (Object object:sysUserBO.getRoles()) {
            if (object instanceof String) {
                String rolename = (String) object;
                if (rolename.equals("admin")) {
                    containAdmin = true;
                }
            }
        }

        int sex = sysPaperListDto.getSex();
        String intro = sysPaperListDto.getIntro();
        List<Paper> list;
        if (containAdmin) {
            list = paperMapper.sysAllPushPaperList(sex, intro);
        } else {
            list = paperMapper.sysPushPaperList(sysUserBO.getId(), sex, intro);
        }

        for (Paper paper:list) {
            SysPaperBO sysPaperBO = new SysPaperBO();
            BeanUtils.copyProperties(paper, sysPaperBO);

            long uid = paper.getUid();
            User user = userService.getUserInfoWithUid(uid);
            sysPaperBO.setNickname(user.getNickname());

            long cid = paper.getCid();
            SysUser sysUser = sysUserService.getUserById(cid);
            sysPaperBO.setCompanyname(sysUser.getName());
            result.add(sysPaperBO);
        }

        return result;
    }

    public List<PaperHistoryBO> sysPullPaperList(String token, SysPaperListDto sysPaperListDto) throws Exception {
        List<PaperHistoryBO> result = new ArrayList<>();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Boolean containAdmin = false;
        for (Object object:sysUserBO.getRoles()) {
            if (object instanceof String) {
                String rolename = (String) object;
                if (rolename.equals("admin")) {
                    containAdmin = true;
                }
            }
        }

        int sex = sysPaperListDto.getSex();
        String intro = sysPaperListDto.getIntro();
        long cid = sysUserBO.getId();
        List<PaperHistory> list;
        if (containAdmin) {
            list = paperHistoryService.paperHistoryWithCidTypeSexIntro(0, 2, sex, intro);
        } else {
            list = paperHistoryService.paperHistoryWithCidTypeSexIntro(cid, 2, sex, intro);
        }

        for (PaperHistory paperHistory:list) {
            PaperHistoryBO paperHistoryBO = new PaperHistoryBO();
            BeanUtils.copyProperties(paperHistory, paperHistoryBO);

            long cuid = paperHistory.getUid();
            long puid = paperHistory.getPuid();
            paperHistoryBO.setPuid(puid);
            User cuser = userService.getUserInfoWithUid(cuid);
            User puser = userService.getUserInfoWithUid(puid);
            paperHistoryBO.setCnickname(cuser.getNickname());
            paperHistoryBO.setPnickname(puser.getNickname());

            SysCompanyUser sysCompanyUser = sysCompanyUserService.userInfoWithUid(cuid);

            SysUser sysUser = sysUserService.getUserById(sysCompanyUser.getCid());
            paperHistoryBO.setCompanyname(sysUser.getName());
            result.add(paperHistoryBO);
        }

        return result;
    }

    public Boolean sysUpdatePaperStatus(SysUpdatePaperStatusDto sysUpdatePaperStatusDto) {
        Date now = new Date();
        int status = sysUpdatePaperStatusDto.getStatus();
        long id = sysUpdatePaperStatusDto.getId();
        return paperMapper.sysUpdatePaperEnable(status, id, now) == 1;
    }

    public Boolean sysUpdatePaperChosen(SysUpdatePaperChosenDto sysUpdatePaperChosenDto) {
        Date now = new Date();
        int chosen = sysUpdatePaperChosenDto.getChosen();
        long id = sysUpdatePaperChosenDto.getId();
        return paperMapper.sysUpdatePaperChosen(chosen, id, now) == 1;
    }

    public Boolean sysDeletePushPaper(long id) throws Exception {
        Date now = new Date();
        return paperHistoryService.delPaperHistory(id);
    }
}
