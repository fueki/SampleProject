package jp.oesf.listsample.model;

import android.graphics.drawable.Drawable;

public class RowModel {
	private String title;
	private Drawable thumbnailImage;
	private String summary;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Drawable getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(Drawable thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "RowModel [title=" + title + ", thumbnailImage="
				+ thumbnailImage + ", summary=" + summary + "]";
	}
}
