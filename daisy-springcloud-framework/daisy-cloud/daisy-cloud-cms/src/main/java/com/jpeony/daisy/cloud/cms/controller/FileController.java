package com.jpeony.daisy.cloud.cms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jpeony.daisy.cloud.common.context.FilterContextHandler;
import com.jpeony.daisy.cloud.common.utils.FileUtils;
import com.jpeony.daisy.cloud.cms.dto.FileDTO;
import com.jpeony.daisy.cloud.cms.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.jpeony.daisy.cloud.cms.domain.FileDO;
import com.jpeony.daisy.cloud.common.utils.PageUtils;
import com.jpeony.daisy.cloud.common.utils.Query;
import com.jpeony.daisy.cloud.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${app.filePath}")
    String filePath;

    @Value("${app.pre}")
    String filePre;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public R get(@PathVariable Long id) {
        FileDO fileDO = fileService.get(id);
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(fileDO, fileDTO);

        return R.data(fileDTO);
    }

    @GetMapping("getToken")
    public R getToken() {
        return R.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-cms/file/upload")
                .put("key", UUID.randomUUID().toString());
    }

    @PostMapping("upload")
    public R upload(MultipartFile file, String key) {
        try {
            String resPath = FileUtils.saveFile(file.getBytes(), filePath, key);
            fileService.save(new FileDO() {{
                setCreateDate(new Date());
                setUrl("http://localhost:8004" + filePre + "/" + resPath);
                setType(1);
            }});
            return R.ok().put("resPath", resPath);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("文件上传失败");
        }
    }

    /**
     * 分页查询
     */
    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<FileDO> fileList = fileService.list(query);
        int total = fileService.count(query);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return R.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public R save(FileDO file) {
        return R.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(FileDO file) {
        return R.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public R remove(Long id) {
        return R.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        return R.operate(fileService.batchRemove(ids) > 0);
    }
}
