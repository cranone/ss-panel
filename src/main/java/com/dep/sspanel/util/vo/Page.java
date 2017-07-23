package com.dep.sspanel.util.vo;

import java.util.List;

/**
 * 分页
 * @author Maclaine
 *
 * @param <T>
 */
public class Page<T> {
	private Integer currentPage=1;
	private Integer sizePage=10;
//	private Integer totalPage=1;
	private Integer total=1;
	private List<T> list;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public Integer getSizePage() {
		return sizePage;
	}
	public List<T> getList() {
		return list;
	}
/*	public Integer getTotalPage() {
		return totalPage;
	}*/
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
/*	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}*/
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
