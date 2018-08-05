package com.dfdk.yunwei.common.tId;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.dfdk.yunwei.common.config.Propertiess;
import com.dfdk.yunwei.common.config.model.Configuration;
import com.dfdk.yunwei.common.util.Map2Bean;

public class ConfigTag extends TagSupport{

	private static final long serialVersionUID = -6862860672551086405L;
	
	private PageContext pageContext;
	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			writeHtml();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return super.doStartTag();
	}
	
	public void writeHtml() {
		JspWriter out = pageContext.getOut();
		try {
			Configuration config = (Configuration) Map2Bean.map2JavaBean(
					Propertiess.getProp2Map(),
					Configuration.class);
			out.println("<div class='header'>"+
							"<h3 class='page-header'>"+config.getCompany()+"</h3>"+
							"<ol class='breadcrumb' id='header-nav'>"+
								"<li><a>主页</a></li>"+
							"</ol>"+
						"</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
