package onesec.onesecapp.bean;

import org.springframework.stereotype.Component;

@Component
public class Category {
	private int catid;
	private String catname;
	private int catindex;

	public Category() {
	}

	public Category(int catid, String catname, int catindex) {
		this.catid = catid;
		this.catname = catname;
		this.catindex = catindex;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public int getCatindex() {
		return catindex;
	}

	public void setCatindex(int catindex) {
		this.catindex = catindex;
	}
}
