package com.caeme.activityreport.rest.dto;

public abstract class PageableRequest {
	
	public static final int ITEMS_PER_PAGE = 20;
	
	private Integer page;

	private Integer per_page;
	
	public void validate() {
		if (page == null ) {
			page = 1;
		}
		
		if (per_page == null) {
			per_page = ITEMS_PER_PAGE;
		}
		validateRequest();
	}
	
	protected abstract void validateRequest();

	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getPer_page() {
		return per_page;
	}
	
	public void setPer_page(Integer per_page) {
		this.per_page = per_page;
	}
	
	

}
