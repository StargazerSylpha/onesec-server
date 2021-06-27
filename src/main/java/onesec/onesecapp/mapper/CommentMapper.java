package onesec.onesecapp.mapper;

import onesec.onesecapp.bean.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommentMapper {

	@Select("select * from comment where article = #{_articleId} order by cid desc,publishdate desc limit #{_skipItem},20;")
	public List<Comment> getArticleCommentList(Long _articleId,Integer _skipItem) throws Exception;

	@Select("select count(*) from comment where article = #{_articleId};")
	public int getArticleCommentListSize(Long _articleId) throws Exception;

	@Select("select * from comment order by cid desc,publishdate desc limit #{_skipItem},20;")
	public List<Comment> getCommentList(Integer _skipItem) throws Exception;

	@Select("select count(*) from comment;")
	public int getCommentListSize() throws Exception;

	@Delete("delete from comment where cid = #{_cid};")
	public int deleteComment(Long _cid) throws Exception;

	@Select("select * from comment where author = #{_uid} order by cid desc, publishdate desc limit #{_skipItem},20;")
	public List<Comment> getUserComment(Long _uid,Integer _skipItem) throws Exception;

	@Select("select count(*) from comment where author = #{_uid};")
	public int getUserCommentSize(Long _uid) throws Exception;

	@Delete("delete from comment where cid = #{_cid} and author = #{_uid};")
	public int userDeleteComment(Long _uid, Long _cid) throws Exception;

	@Insert("insert into comment(article,author,comment) values(#{_articleId}, #{_uid}, #{_comment});")
	public int addComment(Long _articleId, Long _uid, String _comment) throws Exception;
}
