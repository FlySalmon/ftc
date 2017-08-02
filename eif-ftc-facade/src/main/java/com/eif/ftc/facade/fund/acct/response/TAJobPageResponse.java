package com.eif.ftc.facade.fund.acct.response;

import java.util.List;

public class TAJobPageResponse {

	private int totalSize;
	
	private List<?> doList;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<?> getDoList() {
		return doList;
	}

	public void setDoList(List<?> doList) {
		this.doList = doList;
	}
	
}
