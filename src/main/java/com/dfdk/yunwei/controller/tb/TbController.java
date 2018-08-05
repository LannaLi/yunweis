package com.dfdk.yunwei.controller.tb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.Map2Bean;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.tb.TbModel;
import com.dfdk.yunwei.service.tb.EstablishTbManager;

@Controller
@Scope(value="prototype")
@RequestMapping("table/")
public class TbController extends BaseController{
	@Autowired
	private EstablishTbManager establishTbService;
	
	@SysLogControllerLog(description="数据库表管理")
	@RequestMapping("tablelist")
	public ModelAndView tableList() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("db/table");
		return mv;
	}
	
	@SysLogControllerLog(description="添加数据库表")
	@RequestMapping("addTable")
	@ResponseBody
	public JsonResult addTable(@RequestParam(value="tableName")String tableName
			,@RequestParam(value="array[]")String[] array) {
		try {
			//存放所有的字段
			List<TbModel> list = new ArrayList<TbModel>();
			for (int i=0;i<array.length;i++) {
				Map<String,Object> notKeymap = new HashMap<String,Object>();
				String[] arr = array[i].split("&");
				for (int k=0;k<arr.length;k++) {
					String[] param = arr[k].trim().split("=");
					notKeymap.put(param[0],param[1]);
				}
				TbModel tbModel = (TbModel) Map2Bean.map2JavaBean(notKeymap,TbModel.class);
				list.add(tbModel);
			}
			
			//用来存放主键字段
			TbModel tb = new TbModel();
			List<TbModel> notkeyList = new ArrayList<TbModel>();
			for (int j=0;j<list.size();j++) {
				TbModel tbModel = list.get(j);
				if (!"2".equals(tbModel.getKey())) {
					notkeyList.add(tbModel);
				} else {
					tb.setColums(tbModel.getColums());
					tb.setComment(tbModel.getComment());
					tb.setName(tbModel.getName());
				}
			}
			int num = establishTbService.createTb(tableName,tb,notkeyList);
			return new JsonResult(num);
		} catch (RuntimeException e) {
			return new JsonResult(e);
		}
	}
}
