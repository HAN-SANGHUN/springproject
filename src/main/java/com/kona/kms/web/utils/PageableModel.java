package com.kona.kms.web.utils;

public class PageableModel {

	/** The current page number */
	private int page = 1;
	
	/** Total Number of records */
	private int rowSize = 10;
	
	private String sortname;
	
	private String sortorder;
	
	public PageableModel(){
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		
		if(rowSize == 0) rowSize = 10;
		
		this.rowSize = rowSize;
	}
	
	public int getStartRowNum(){
		return rowSize * (page-1)+1;
	}
	
	public int getEndRowNum(){
		return (rowSize * (page -1)) + rowSize;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	
	
}
