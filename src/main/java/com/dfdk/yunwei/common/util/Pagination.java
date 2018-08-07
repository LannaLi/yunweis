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
	
	public Pagination() {
		super();
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
		this.pageCount = this.rowCount /this.pageSize;
		if (this.rowCount % this.pageSize != 0) {
			this.pageCount += 1;
		}
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
		this.startPage = (currentPage-1) * pageSize;
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		this.endPage = currentPage * this.pageSize;
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", pageSize=" + pageSize + ", pageCount=" + pageCount
				+ ", rowCount=" + rowCount + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
