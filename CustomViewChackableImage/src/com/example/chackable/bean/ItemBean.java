package com.example.chackable.bean;

public class ItemBean {
	public String id;
	public String filename;
	public String url;
	public ItemBean(String id, String filename, String url) {
		super();
		this.id = id;
		this.filename = filename;
		this.url = url;
	}
	@Override
	public String toString() {
		return "ItemBean [id=" + id + ", filename=" + filename + ", url=" + url + "]";
	}
	
}
