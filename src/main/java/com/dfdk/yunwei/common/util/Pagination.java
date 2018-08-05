package com.dfdk.yunwei.common.util;
/**
 * 分页插件
 * @author Lanna
 *
 */
public class Pagination {
	
	/**每页的条数*/
	private int currentPage = 1;
	/**每页的条数*/
	private int pageSize = 10;
	/**总页数*/
	private int pageCount;
	/**总条数*/
	private int rowCount;
	/**开始页*/
	private int startPage;
	/**结束页*/
	private int endPage;
	
	
	public Pagination(int rowCount) {
		this.rowCount = rowCount;
		if(rowCount % pageSize == 0) {
			this.pageCount = rowCount/pageSize;
		} else {
			this.pageCount = (rowCount/pageSize) + 1;
		}
		this.startPage = (currentPage - 1) * pageSize;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getRowCount() {
		return rowCount;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	
	
	
}
