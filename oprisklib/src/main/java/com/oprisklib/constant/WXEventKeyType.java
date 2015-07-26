package com.oprisklib.constant;

public enum WXEventKeyType {
	SCAN_BORROW("scan_borrow",1),
	SCAN_RETURN("scan_return",2),
	SEARCH_BY_BOOKNAME("search_by_bookname", 3),
	SEARCH_BY_AUTHOR("search_by_author",4),
	SEARCH_BY_OWNER("search_by_owner",5),
	SCAN_INPUT_BOOK("scan_inut_book",6),
	SCAN_DESCRIPTION("scan_description", 7);
	
	private String name;
	private int index;
	
	private WXEventKeyType(String name,int index){
		this.name = name;
		this.index = index;
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
