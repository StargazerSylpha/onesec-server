package onesec.onesecapp.bean;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class Comment {
	private Long cid;
	private Long article;
	private Long author;
	private String comment;
	private Timestamp publishdate;

	public Map<String,Object> toMap() {
		Map<String,Object> commentMap = new HashMap<>();
		commentMap.put("cid",this.cid);
		commentMap.put("article",this.article);
		commentMap.put("author",this.author);
		commentMap.put("comment",this.comment);
		commentMap.put("publishDate",this.publishdate);
		return commentMap;
	}

	public Comment() {
	}

	public Comment(Long cid, Long article, Long author, String comment, Timestamp publishdate) {
		this.cid = cid;
		this.article = article;
		this.author = author;
		this.comment = comment;
		this.publishdate = publishdate;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getArticle() {
		return article;
	}

	public void setArticle(Long article) {
		this.article = article;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Timestamp publishdate) {
		this.publishdate = publishdate;
	}
}
