package com.kona.kms.web.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridPageData<T extends Serializable>{

	/** Total Number of Pages */
	private int total;
	
	/** The current page number */
	private int page;
	
	/** Total Number of records */
	private int records;	
	
	/** The actual data */
	private List<T> rows;	
	
	public GridPageData(){
		
	}
	
	public GridPageData(int rowSize, int currPage, int totalRecords, List<T> rows){		
		super();
		this.page = currPage;
		this.records = totalRecords;
		this.rows = rows;	
		this.total = (totalRecords - 1) / rowSize + 1;
	}		

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getRecords() {
		return records;
	}

	public List<T> getRows() {
		
		if(rows == null) return new ArrayList<T>();
		return rows;
	}	

	@Override
	public String toString() {
		return "GridPageData [total=" + total + ", page=" + page + ", records="
				+ records + ", rows=" + rows + "]";
	}	
	
}
