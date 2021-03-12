package com.jpeony.daisy.cloud.cms.service;

import com.jpeony.daisy.cloud.cms.domain.FileDO;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
public interface FileService {
	
	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO file);
	
	int update(FileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
