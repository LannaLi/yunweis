package com.dfdk.yunwei.controller.ex;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.controller.base.BaseController;
@Controller
public class ExController extends BaseController{
	
	@RequestMapping("error")
	public ModelAndView error() {
		Map<String,Object> ex = this.getRequestParam();
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("error");
		mv.addObject("ex", ex);
		return mv;
	}
	
}
