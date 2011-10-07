package jp.oesf.httpsample.bean;

public class DecoBean {

	public String _id;
	public String url;
	public String file_name;
	public String kind;
	public DecoBean(String _id, String url, String file_name, String kind) {
		super();
		this._id = _id;
		this.url = url;
		this.file_name = file_name;
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "DecoBean [_id=" + _id + ", url=" + url + ", file_name=" + file_name + ", kind="
				+ kind + "]";
	}
	
}
