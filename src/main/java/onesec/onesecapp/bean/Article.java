package onesec.onesecapp.bean;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class Article {
	private Long id;
	private Long author;
	private int category;
	private Timestamp publishDate;
	private String banner;
	private String title;
	private String content;
	/**
	 * String类内部使用char[]字符数组来保存内容，最大长度为2^31-1
	 * mysql内类型为longtext
	 * 根本不可能有这么长的文章，放心用即可。
	 */
	public Article() {
	}

	public Article(Long id, Long author, int category, Timestamp publishDate, String banner, String title, String content) {
		this.id = id;
		this.author = author;
		this.category = category;
		this.publishDate = publishDate;
		this.banner = banner;
		this.title = title;
		this.content = content;
	}

	public Map<String,Object> toMap() {
		Map<String,Object> articleMap = new HashMap<>();
		articleMap.put("id",this.id);
		articleMap.put("author",this.author);
		articleMap.put("category",this.category);
		articleMap.put("publishDate",this.publishDate);
		articleMap.put("banner",this.banner);
		articleMap.put("title",this.title);
		articleMap.put("content",this.content);
		return articleMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Timestamp getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
