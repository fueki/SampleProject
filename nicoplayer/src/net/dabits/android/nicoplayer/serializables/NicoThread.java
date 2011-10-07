package net.dabits.android.nicoplayer.serializables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NicoThread implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4121547675251439659L;
	private ArrayList<HashMap<String, String>> comments;
	private HashMap<String, String> thread;

	public ArrayList<HashMap<String, String>> getComments() {
		return comments;
	}

	public void setComments(ArrayList<HashMap<String, String>> videoItems) {
		this.comments = videoItems;
	}
	
	public HashMap<String, String> getThread(){
		return thread;
	}
	
	public void setThread(HashMap<String, String> thread){
		this.thread = thread;
	}
}
