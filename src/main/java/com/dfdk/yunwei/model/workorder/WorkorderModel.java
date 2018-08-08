package com.dfdk.yunwei.model.workorder;

import java.io.Serializable;
import java.util.Date;

import com.dfdk.yunwei.model.base.BaseModel;

/**
 * 工单实体类
 * @author 加肥猫
 *
 */
public class WorkorderModel extends BaseModel<WorkorderModel> implements Serializable {

	private static final long serialVersionUID = 6834675226347222620L;
	private String workOrderId; //工单ID
	private String workOrderBh; //工单编号 DFDK20180704100010001
	private String workOrderType; //工单类型
	private String createType; //工单创建类型
	private String sourceId; //来源ID
	private String wdBelongTo; //受理人
	private Date wOrderStart; //预约起始时间
	private Date wOrderEnd; //预约结束时间
	private String productId; //产品ID
	private String serviceType; //服务方式
	private String servAreaId; //服务区域ID
	private String servAddress; //服务地址
	private String acceptTime; //受理时间
	private String engineerId; //工程师ID
	private String wdHandlerUser; //工单处理人
	private char wdStatus; //工单状态 1.已派单 2.未派单
	private String wdRemark; //工单内容
	private String servicePrice; //服务评价
	public String getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}
	public String getWorkOrderBh() {
		return workOrderBh;
	}
	public void setWorkOrderBh(String workOrderBh) {
		this.workOrderBh = workOrderBh;
	}
	public String getWorkOrderType() {
		return workOrderType;
	}
	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}
	public String getCreateType() {
		return createType;
	}
	public void setCreateType(String createType) {
		this.createType = createType;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getWdBelongTo() {
		return wdBelongTo;
	}
	public void setWdBelongTo(String wdBelongTo) {
		this.wdBelongTo = wdBelongTo;
	}
	public Date getwOrderStart() {
		return wOrderStart;
	}
	public void setwOrderStart(Date wOrderStart) {
		this.wOrderStart = wOrderStart;
	}
	public Date getwOrderEnd() {
		return wOrderEnd;
	}
	public void setwOrderEnd(Date wOrderEnd) {
		this.wOrderEnd = wOrderEnd;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServAreaId() {
		return servAreaId;
	}
	public void setServAreaId(String servAreaId) {
		this.servAreaId = servAreaId;
	}
	public String getServAddress() {
		return servAddress;
	}
	public void setServAddress(String servAddress) {
		this.servAddress = servAddress;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(String engineerId) {
		this.engineerId = engineerId;
	}
	public String getWdHandlerUser() {
		return wdHandlerUser;
	}
	public void setWdHandlerUser(String wdHandlerUser) {
		this.wdHandlerUser = wdHandlerUser;
	}
	public char getWdStatus() {
		return wdStatus;
	}
	public void setWdStatus(char wdStatus) {
		this.wdStatus = wdStatus;
	}
	public String getWdRemark() {
		return wdRemark;
	}
	public void setWdRemark(String wdRemark) {
		this.wdRemark = wdRemark;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
