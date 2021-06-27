package onesec.onesecapp.mapper;

import onesec.onesecapp.bean.Article;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.bean.Trending;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ArticleMapper {

	@Select("select * from article where id = #{_articleId}")
	@Results({
		@Result(column = "publishdate",property = "publishDate",jdbcType = JdbcType.TIMESTAMP),
	})
	public Article getArticleById(Long _articleId) throws Exception;

	@Select("select * from category where catid = #{_catid}")
	public Category getCategoryById(int _catid) throws Exception;

	/**
	 *
	 * skipItem为跳过的条目数，
	 * servie层修改一次加载多少条，及跳过条目数时，这里也要改，以及前端后台articleList el-pagination也要改
	 * 跳过条数 = 页数 x 一次加载条目数
	 */
	@Select("select id,author,category,publishdate,banner,title from article order by id desc, publishdate desc limit #{_skipItem},20")
	public List<Article> getArticleListByPage(int _skipItem) throws Exception;
	@Select("select id,author,category,publishdate,banner,title from article where category = #{_catId} order by id desc, publishdate desc limit #{_skipItem},20")
	public List<Article> getArticleListByCategoryAndPage(int _catId,int _skipItem) throws Exception;
	@Select("select * from category order by catindex desc;")
	public List<Category> getCategoryList() throws Exception;
	@Insert("insert into category(catname) values(#{_catName});")
	public int addCategory(String _catName) throws Exception;
	@Delete("delete from category where catid = #{_catId}")
	public int deleteCategory(Integer _catId) throws Exception;

	@Update("update category set catname = #{_catName},catindex = #{_catIndex} where catid = #{_catId};")
	public int editCategory(Integer _catId,String _catName, Integer _catIndex) throws Exception;
	@Select("select catid from category where catname = #{_catName};")
	public List<Category> checkCategoryExist(String _catName) throws Exception;
	@Select("select count(*) from article;")
	public int getArticleListSize() throws Exception;

	@Delete("delete from article where id= #{_articleId};")
	public int deleteArticle(Long _articleId) throws Exception;

	@Select("select * from trending order by itemindex desc;")
	public List<Trending> getTrendingList() throws Exception;

	@Update("update trending set title = #{_title}, itemindex = #{_itemIndex}, link = #{_link}, banner = #{_banner}, lastchange = #{_lastchange,jdbcType=TIMESTAMP} where id = #{_id};")
	public int editTrending(Integer _id, String _title, Integer _itemIndex, String _link, String _banner, Timestamp _lastchange) throws Exception;

	@Delete("delete from trending where id = #{_id};")
	public int deleteTrending(Integer _id) throws Exception;

	@Insert("insert into trending(title,banner,link,itemindex) values(#{_title}, #{_banner}, #{_link}, #{_itemIndex});")
	public int addTrending(String _title, Integer _itemIndex, String _link, String _banner) throws Exception;

	/**
	 * 注解方式like：https://blog.csdn.net/weixin_42547014/article/details/108789452
	 */
	@Select("select id,author,category,publishdate,banner,title from article where title like CONCAT('%', #{_keyword}, '%') or content like CONCAT('%', #{_keyword}, '%') order by id desc, publishdate desc limit #{_skipItem},20; ")
	public List<Article> articleSearch(String _keyword,Integer _skipItem) throws Exception;
	@Select("select count(*) from article where title like CONCAT('%', #{_keyword}, '%') or content like CONCAT('%', #{_keyword}, '%'); ")
	public int getArticleSearchSize(String _keyword) throws Exception;

	@Insert("insert into article(author,category,banner,title,content) values(#{_author}, #{_catId}, #{_banner}, #{_title}, #{_content});")
	public int addArticle(Long _author,Integer _catId,String _banner,String _title,String _content) throws Exception;

	@Update("update article set category = #{_catId}, banner = #{_banner}, title = #{_title}, content = #{_content} where id = #{_articleId};")
	public int editArticle(Long _articleId, Integer _catId,String _banner, String _title, String _content) throws Exception;

}
