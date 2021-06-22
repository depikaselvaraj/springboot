package com.example.demo.model;

import org.springframework.data.domain.Sort;

public class UserPage {
	private int pageno=0;
	private int pagesize=2;
	private Sort.Direction sortdirection=Sort.Direction.ASC;
	private String sortBy="email";
	public UserPage() {
		
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public Sort.Direction getSortdirection() {
		return sortdirection;
	}
	public void setSortdirection(Sort.Direction sortdirection) {
		this.sortdirection = sortdirection;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	

}
