package net.dabits.android.nicoplayer.serializables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoItems implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -141861516429605309L;
	private ArrayList<HashMap<String, String>> videoItems;

	public ArrayList<HashMap<String, String>> get() {
		return videoItems;
	}

	public void set(ArrayList<HashMap<String, String>> videoItems) {
		this.videoItems = videoItems;
	}
}
