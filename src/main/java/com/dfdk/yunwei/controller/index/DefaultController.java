package com.dfdk.yunwei.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.controller.base.BaseController;

@Controller
public class DefaultController extends BaseController{
	
	@SysLogControllerLog(description="加载默认页面")
	@RequestMapping("loadDefaultPage")
	public ModelAndView loadDefaultPage() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("default/default");
		return mv;
	}
}
