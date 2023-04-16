package com.common.project.elasticsearch.repository;

import java.util.List;

/**
 * program: esdemo
 * description: 分页对象封装
 * author: X-Pacific zhang
 * create: 2019-01-21 17:06
 **/
public class PageList<T> {
	
    List<T> resultData;

    private int totalPages = 0;

    private long totalRecord = 0;

    private Object[] sortValues;

	public List<T> getResultData() {
		return resultData;
	}

	public void setResultData(List<T> resultData) {
		this.resultData = resultData;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Object[] getSortValues() {
		return sortValues;
	}

	public void setSortValues(Object[] sortValues) {
		this.sortValues = sortValues;
	}

}
