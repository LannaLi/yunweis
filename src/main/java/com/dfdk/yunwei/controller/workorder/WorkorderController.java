package com.dfdk.yunwei.controller.workorder;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.workorder.WorkorderModel;
import com.dfdk.yunwei.service.workorder.WorkorderManager;

@Controller
@Scope(value="prototype")
@RequestMapping("workorder/")
public class WorkorderController extends BaseController{
	
	

	@Autowired
	private WorkorderManager workorderSerivce;
	
	@SysLogControllerLog(description="获取工单列表")
	@RequestMapping("userlist")
	@RequiresPermissions("sys:user:view")
	public ModelAndView workorderList() {
		ModelAndView mv = this.getModelAndView();
		
		return null;
	}
	
	@SysLogControllerLog(description="新增工单")
	@RequestMapping("addWorkorder")
	@ResponseBody
	public JsonResult addWorkorder(WorkorderModel model) {
		try {
			int num = workorderSerivce.insertObject(model);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="修改工单")
	@RequestMapping("modifyWorkorder")
	@ResponseBody
	public JsonResult modifyWorkorder(WorkorderModel model) {
		try {
			workorderSerivce.updateObject(model);
			return new JsonResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="删除工单")
	@RequestMapping("removeWorkorder")
	@ResponseBody
	public JsonResult removeWorkorder(@RequestParam("workOrderId") String workOrderId) {
		try {
			workorderSerivce.deleteObject(workOrderId);
			return new JsonResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
}
