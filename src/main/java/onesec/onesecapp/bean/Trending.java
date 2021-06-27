package onesec.onesecapp.bean;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Trending {
	private int id;
	private String title;
	private String banner;
	private String link;
	private int itemindex;
	private Timestamp lastchange;

	public Trending() {
	}

	public Trending(int id, String title, String banner, String link, int itemindex, Timestamp lastchange) {
		this.id = id;
		this.title = title;
		this.banner = banner;
		this.link = link;
		this.itemindex = itemindex;
		this.lastchange = lastchange;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getItemindex() {
		return itemindex;
	}

	public void setItemindex(int itemindex) {
		this.itemindex = itemindex;
	}

	public Timestamp getLastchange() {
		return lastchange;
	}

	public void setLastchange(Timestamp lastchange) {
		this.lastchange = lastchange;
	}
}
