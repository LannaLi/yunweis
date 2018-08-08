package com.dfdk.yunwei.service.workorder.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.dao.workorder.WorkorderMapper;
import com.dfdk.yunwei.model.workorder.WorkorderModel;
import com.dfdk.yunwei.service.workorder.WorkorderManager;

/**
 * 工单Service
 * @author 加肥猫
 *
 */
@Transactional(readOnly=false)
@Service
public class WorkorderService implements WorkorderManager {

	@Autowired
	private WorkorderMapper mapper;
	
	@Override
	public WorkorderModel queryObject(String id) throws Exception {
		return mapper.query(id);
	}

	@Override
	public int insertObject(WorkorderModel model) throws Exception {
		return mapper.insert(model);
	}

	@Override
	public void updateObject(WorkorderModel model) throws Exception {
		mapper.update(model);
	}

	@Override
	public void deleteObject(String id) throws Exception {
		mapper.delete(id);
	}

}
