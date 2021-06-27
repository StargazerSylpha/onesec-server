package onesec.onesecapp.service;

import onesec.onesecapp.bean.Trending;
import onesec.onesecapp.mapper.ArticleMapper;
import onesec.onesecapp.mapper.UserMapper;
import onesec.onesecapp.bean.Article;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;

	/**
	 * 检查articleId合法性
	 * uid >0 且不为null
	 */
	public boolean checkArticleId(Long _articleId) {
		//21
		return (_articleId != null && _articleId > 0 );
	}


	public Map<String,Object> getArticleById(Long _articleId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkArticleId(_articleId)) {
			errMsg.put("errcode",2011);
			errMsg.put("msg","文章ID不合法");
			return errMsg;
		}
		Article articleDetail = articleMapper.getArticleById(_articleId);
		if(articleDetail != null) {
			Map<String,Object> data = new HashMap<>();

			User author = userMapper.getUserByUid(articleDetail.getAuthor());
			Category cat = articleMapper.getCategoryById(articleDetail.getCategory());
			data = articleDetail.toMap();
			data.put("authorNickname",author.getNickname());
			data.put("authorAvatar",author.getAvatar());
			//分类删除时报nullpointer 上面用户删除可以不用管，因为只是标注用户已删除，并不会真正删除数据
			data.put("catName",(cat==null)?"[分类已删除]":cat.getCatname());
			/**
			 * 数据库存储html代码时以htmlescape存
			 * 但前端v-html插入代码时，若是以escape内容插入，则会直接显示代码，而不会渲染出效果
			 * 故需要unescape一下 虽然会带\转义斜杠，但没办法了
			 */
			data.put("content",HtmlUtils.htmlUnescape(data.get("content").toString()));

			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			errMsg.put("data",data);
			return errMsg;
		}
		errMsg.put("errcode",2012);
		errMsg.put("msg","文章不存在");
		return errMsg;
	}

	public Map<String,Object> getArticleList(Integer _catId, Integer _pageId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		/**
		 * 分类catid若不传或<0 直接传-1，后台会给出所有分类的文章
		 * 页码pageid若不传或<0 直接传0，从第一页开始加载
		 */
		int catId = (_catId == null || _catId < 0)? -1 : _catId;
		int pageId = (_pageId == null || _pageId <0)? 0 : _pageId;

		List<Article> la = new ArrayList<>();
		if(catId < 0) {
			/**
			 * 页码从1开始还是从0开始无所谓了 从0开始（震声
			 */
			la = articleMapper.getArticleListByPage(pageId * 20);
		} else {
			la = articleMapper.getArticleListByCategoryAndPage(catId,pageId * 20);
		}

		if(la != null && la.size()>0) {
			//确认文章表有数据才计算数据
			int dataSize = articleMapper.getArticleListSize();
			List<Map<String,Object>> data = new ArrayList<>();

			for(Article a : la) {
				/**
				 * 不能用map.clear
				 * 每次list.add添加的是同一个map，其指针相同，代表同一地址
				 * 销毁map:直接令其=null
				 */

				Map<String,Object> dataItem = new HashMap<>();
				dataItem = a.toMap();
				dataItem.put("authorNickname",userMapper.getUserByUid(a.getAuthor()).getNickname());
				Category catObject = articleMapper.getCategoryById(a.getCategory());
				/**
				 * 删除分类后，再查找分类名称时会报错
				 */
				dataItem.put("catName", catObject == null ? "[分类已删除]": catObject.getCatname());
				data.add(dataItem);
				dataItem = null;
			}
			/**
			 * 有出现「没有返回内容却还显示success」的情况
			 */
			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			errMsg.put("size",dataSize);
			errMsg.put("data",data);
			return errMsg;
		}
		errMsg.put("errcode",2021);
		errMsg.put("msg","未找到任何内容");
		return errMsg;
	}

	public Map<String,Object> articleSearch(String _keyword, Integer _pageId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();

		int pageId = (_pageId == null || _pageId <0)? 0 : _pageId;
		List<Article> la = new ArrayList<>();

		if(_keyword != null && _keyword.length() > 0) {
			String keyword = HtmlUtils.htmlEscape(_keyword); //字符转义，防止sql注入

			la = articleMapper.articleSearch(keyword,pageId * 20);

			if(la != null && la.size()>0) {
				//确认文章表有数据才计算数据
				int dataSize = articleMapper.getArticleSearchSize(keyword);
				List<Map<String,Object>> data = new ArrayList<>();

				for(Article a : la) {
					/**
					 * 不能用map.clear
					 * 每次list.add添加的是同一个map，其指针相同，代表同一地址
					 * 销毁map:直接令其=null
					 */
					Map<String,Object> dataItem = new HashMap<>();
					dataItem = a.toMap();
					dataItem.put("authorNickname",userMapper.getUserByUid(a.getAuthor()).getNickname());
					Category catObject = articleMapper.getCategoryById(a.getCategory());
					/**
					 * 删除分类后，再查找分类名称时会报错
					 */
					dataItem.put("catName", catObject == null ? "[分类已删除]": catObject.getCatname());
					data.add(dataItem);
					dataItem = null;
				}
				/**
				 * 有出现「没有返回内容却还显示success」的情况
				 */
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				errMsg.put("size",dataSize);
				errMsg.put("data",data);
				return errMsg;
			}
		}


		errMsg.put("errcode",20101);
		errMsg.put("msg","未找到任何内容");
		return errMsg;
	}

	public List<Category> getCategoryList() throws Exception {
		List<Category> lc = new ArrayList<>();

		lc = articleMapper.getCategoryList();

		if(lc != null && lc.size()>0) {
			return lc;

		}
		return null;
	}
	public boolean checkCategoryName(String _catName) {
		//22
		return _catName != null && _catName.length() <= 20 && _catName.length() >= 1;
	}
	public Map<String,Object> addCategory(String _catName) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryName(_catName)) {
			errMsg.put("errcode",2031);
			errMsg.put("msg","分类名称不合法");
			return errMsg;
		}
		List<Category> lc = articleMapper.checkCategoryExist(_catName);
		if(lc.size() != 0) {
			errMsg.put("errcode",2034);
			errMsg.put("msg","分类名称已存在");
			return errMsg;
		}


		int result = articleMapper.addCategory(_catName);
		if(result == 1) {
			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			return errMsg;
		} else {
			errMsg.put("errcode",2032);
			errMsg.put("msg","添加失败");
			return errMsg;
		}

	}

	public boolean checkCategoryId(Integer _catId) {
		//23
		return _catId != null && _catId >= 0;
	}
	public Map<String,Object> deleteCategory(int _catId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryId(_catId)) {
			errMsg.put("errcode",2041);
			errMsg.put("msg","分类ID有误");
			return errMsg;
		} else if(_catId == 0) {
			errMsg.put("errcode",2042);
			errMsg.put("msg","默认分类不可删除");
			return errMsg;
		} else  {
			int result = articleMapper.deleteCategory(_catId);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2043);
				errMsg.put("msg","删除失败");
				return errMsg;
			}
		}
	}



	public Map<String,Object> editCategory(Integer _catId,String _catName,Integer _catIndex) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryId(_catId)) {
			errMsg.put("errcode",2051);
			errMsg.put("msg","分类ID有误");
			return errMsg;
		} else if(!checkCategoryName(_catName)) {
			errMsg.put("errcode",2052);
			errMsg.put("msg","分类名称有误");
			return errMsg;
		} else if(_catIndex == null || _catIndex < 0 || _catIndex > 100) {
			errMsg.put("errcode",2053);
			errMsg.put("msg","分类顺序有误！（介于0到100之间）");
			return errMsg;
		} else {
			int result = articleMapper.editCategory(_catId,_catName,_catIndex);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2054);
				errMsg.put("msg","修改失败");
				return errMsg;
			}
		}
	}

	public Map<String,Object> deleteArticle(Long _articleId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkArticleId(_articleId)) {
			errMsg.put("errcode",21);
			errMsg.put("msg","文章ID有误");
			return errMsg;
		} else  {
			int result = articleMapper.deleteArticle(_articleId);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2061);
				errMsg.put("msg","删除失败");
				return errMsg;
			}
		}
	}

	public List<Trending> getTrendingList() throws Exception {
		List<Trending> lt = new ArrayList<>();

		lt = articleMapper.getTrendingList();

		if(lt != null && lt.size()>0) {
			return lt;

		}
		return null;
	}

	public boolean checkArticleTitle(String _articleTitle) {
		//24
		return _articleTitle != null && _articleTitle.length() > 1 && _articleTitle.length() < 50;
	}

	public boolean checkUrl(String _url) {
		return _url != null && _url.length() >= 6 && _url.length() <= 200;
	}


	public Map<String,Object> editTrending(Trending _req) throws Exception {
		/**
		 * 直接用category check函数就行，基本上都差不多
		 */
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryId(_req.getId())) {
			errMsg.put("errcode",23);
			errMsg.put("msg","条目ID有误");
			return errMsg;
		} else if(!checkArticleTitle(_req.getTitle())) {
			errMsg.put("errcode",24);
			errMsg.put("msg","条目名称有误");
			return errMsg;
		} else if(((Integer)_req.getItemindex()) == null || _req.getItemindex() < 0 || _req.getItemindex() > 100) {
			errMsg.put("errcode",2071);
			errMsg.put("msg","分类顺序有误！（介于0到100之间）");
			return errMsg;
		} else if(!checkUrl(_req.getLink())) {
			errMsg.put("errcode",2072);
			errMsg.put("msg","链接有误");
			return errMsg;
		}/*  else if(!checkUrl(_req.getBanner())) {
			errMsg.put("errcode",2073);
			errMsg.put("msg","横幅URL有误");
			return errMsg;
		} */ else {
			int result = articleMapper.editTrending(_req.getId(), _req.getTitle(), _req.getItemindex(),
					_req.getLink(),_req.getBanner(),new Timestamp(System.currentTimeMillis()));
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2074);
				errMsg.put("msg","修改失败");
				return errMsg;
			}
		}
	}

	public Map<String,Object> deleteTrending(Integer _id) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryId(_id)) {
			errMsg.put("errcode",21);
			errMsg.put("msg","条目ID有误");
			return errMsg;
		} else  {
			int result = articleMapper.deleteTrending(_id);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2081);
				errMsg.put("msg","删除失败");
				return errMsg;
			}
		}
	}

	public Map<String,Object> addTrending(Trending _req) throws Exception {
		/**
		 * 直接用category check函数就行，基本上都差不多
		 */
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkArticleTitle(_req.getTitle())) {
			errMsg.put("errcode",24);
			errMsg.put("msg","条目名称有误");
			return errMsg;
		} else if(((Integer)_req.getItemindex()) == null || _req.getItemindex() < 0 || _req.getItemindex() > 100) {
			errMsg.put("errcode",2071);
			errMsg.put("msg","分类顺序有误！（介于0到100之间）");
			return errMsg;
		} else if(!checkUrl(_req.getLink())) {
			errMsg.put("errcode",2072);
			errMsg.put("msg","链接有误");
			return errMsg;
		}/*  else if(!checkUrl(_req.getBanner())) {
			errMsg.put("errcode",2073);
			errMsg.put("msg","横幅URL有误");
			return errMsg;
		} */ else {
			int result = articleMapper.addTrending(_req.getTitle(),_req.getItemindex(),_req.getLink(),_req.getBanner());
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",2092);
				errMsg.put("msg","添加失败");
				return errMsg;
			}
		}
	}

	public boolean checkArticleExist(Long _articleId) throws Exception {
		if(!checkArticleId(_articleId)) {
			return false;
		}
		Article a = articleMapper.getArticleById(_articleId);
		//包装类数字利用equals对比
		return a != null && a.getId().equals(_articleId);
	}

	public boolean checkCategoryExist(int _catId) throws Exception {
		if(!checkCategoryId(_catId)) return false;

		Category c = articleMapper.getCategoryById(_catId);
		return c!= null && c.getCatid() == _catId;
	}

	public Map<String,Object> addArticle(Article _a) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryExist(_a.getCategory())) {
			errMsg.put("errcode",20111);
			errMsg.put("msg","分类不存在");
			return errMsg;
		} else if(_a.getTitle() == null || _a.getTitle().length() < 1) {
			errMsg.put("errcode",20112);
			errMsg.put("msg","请输入标题！");
			return errMsg;
		} else if(_a.getContent() == null || _a.getContent().length() < 1) {
			errMsg.put("errcode",20113);
			errMsg.put("msg","请输入内容！");
			return errMsg;
		} else if(_a.getAuthor() == null || !userService.checkUserExist(_a.getAuthor())) {
			errMsg.put("errcode",20114);
			errMsg.put("msg","用户不存在或已注销！");
			return errMsg;
		} else {

			String articleContent = HtmlUtils.htmlEscape(_a.getContent());
			int result = articleMapper.addArticle(_a.getAuthor(),_a.getCategory(), _a.getBanner(), _a.getTitle(), articleContent);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",20115);
				errMsg.put("msg","添加文章失败");
				return errMsg;
			}
		}
	}

	public Map<String,Object> editArticle(Article _a) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCategoryExist(_a.getCategory())) {
			errMsg.put("errcode",20121);
			errMsg.put("msg","分类不存在");
			return errMsg;
		} else if(_a.getTitle() == null || _a.getTitle().length() < 1) {
			errMsg.put("errcode",20122);
			errMsg.put("msg","请输入标题！");
			return errMsg;
		} else if(_a.getContent() == null || _a.getContent().length() < 1) {
			errMsg.put("errcode",20123);
			errMsg.put("msg","请输入内容！");
			return errMsg;
		} else if(!checkArticleExist(_a.getId())) {
			errMsg.put("errcode",20125);
			errMsg.put("msg","文章不存在");
			return errMsg;
		} else {
			String articleContent = HtmlUtils.htmlEscape(_a.getContent());
			int result = articleMapper.editArticle(_a.getId(), _a.getCategory(), _a.getBanner(), _a.getTitle(), articleContent);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",20126);
				errMsg.put("msg","修改文章失败");
				return errMsg;
			}
		}
	}


}
