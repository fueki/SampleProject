package com.hidecheck.honeycomic;

public class DirectoryCategory {
	private String name;
	private DirectoryEntry[] entries;
	
	public DirectoryCategory(String name, DirectoryEntry[] entries) {
		super();
		this.name = name;
		this.entries = entries;
	}

	public String getName() {
		return name;
	}

	public DirectoryEntry[] getEntries() {
		return entries;
	}
	
	public int getEntryCount(){
		return entries.length;
	}
	
}
