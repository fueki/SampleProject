package com.hidecheck.honeycomic;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DirectoryEntry {
	private String name;
	private int resId;

	public String getName() {
		return name;
	}
	
	public int getId() {
		return resId;
	}
	
	public DirectoryEntry(String name, int id) {
		this.resId = id;
		this.name = name;
	}
	
	public Bitmap getBìtmap(Resources res){
		return BitmapFactory.decodeResource(res, resId);
	}
}
