package com.child.box.core.base;


public class ApiRes {
	private String status = ApiCons.STATUS_ERROR;
	private String msg;

	private Object data;

	private ApiPage page;
	
	public void setPagea(Integer page_num,Integer page_size,Integer total_page){
		this.page  = new ApiPage();
		this.page.setPage_num(page_num);
		this.page.setPage_size(page_size);
		this.page.setTotal_page(total_page);
	}
	
	public void setPage(Page page){
		this.page  = new ApiPage();
		this.page.setPage_num(page.getCurrentPage());
		this.page.setPage_size(page.getShowCount());
		this.page.setTotal_page(page.getTotalPage());
		this.page.setTotal_row(page.getTotalResult());
	}
	
	
	public ApiPage getPage() {
		return page;
	}

	public void setPage(ApiPage page) {
		this.page = page;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static class ApiPage{
		private String page_num;
		private String page_size;
		private String total_page;
		private String total_row;
		
		
		public String getTotal_row() {
			return total_row;
		}
		public void setTotal_row(Integer total_row) {
			this.total_row = String.valueOf(total_row);
		}
		public String getPage_num() {
			return page_num;
		}
		public void setPage_num(Integer page_num) {
			this.page_num = String.valueOf(page_num);
		}
		public String getPage_size() {
			return page_size;
		}
		public void setPage_size(Integer page_size) {
			this.page_size = String.valueOf(page_size);
		}
		public String getTotal_page() {
			return total_page;
		}
		public void setTotal_page(Integer total_page) {
			this.total_page = String.valueOf(total_page);
		}
	}
	
}
