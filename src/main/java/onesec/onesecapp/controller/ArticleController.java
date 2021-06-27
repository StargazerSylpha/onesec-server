package onesec.onesecapp.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import onesec.onesecapp.bean.Article;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.bean.Trending;
import onesec.onesecapp.bean.User;
import onesec.onesecapp.service.ArticleService;
import onesec.onesecapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ArticleController {
	/**
	 * 400错误：api传入参数类型不匹配，参数连拦截器和controller都没进！更别提trycatch了
	 */

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;


	@GetMapping(path = "/article/getArticle")
	public String getArticleById(@RequestParam(value = "id",required = false) Long _articleId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.getArticleById(_articleId);
			return JSON.toJSONString(errMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2013);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@GetMapping(path = "/article/getArticleList")
	public String getArticleList(@RequestParam(value = "category",required = false) Integer _catId,@RequestParam(value = "page",required = false) Integer _pageId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.getArticleList(_catId,_pageId);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2022);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@GetMapping(path = "/article/articleSearch")
	public String getArticleList(@RequestParam(value = "keyword",required = false) String _keyword,
								 @RequestParam(value = "page",required = false) Integer _pageId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.articleSearch(_keyword,_pageId);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",20102);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@GetMapping(path = "/category/getCategoryList")
	public String getCategoryList() {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			List<Category> lc = new ArrayList<>();
			lc = articleService.getCategoryList();
			if(lc != null && lc.size() > 0) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				errMsg.put("data",lc);
				return JSON.toJSONString(errMsg);
			} else {
				errMsg.put("errcode",2031);
				errMsg.put("msg","没有找到内容");
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2032);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/category/addCategory")
	public String addCategory(@RequestParam(value = "catname",required = false) String _catName) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.addCategory(_catName);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2033);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/category/deleteCategory")
	public String deleteCategory(@RequestParam(value = "catid",required = false) int _catId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.deleteCategory(_catId);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2044);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/category/editCategory")
	public String editCategory(@RequestParam(value = "catid",required = false) Integer _catId,
							   @RequestParam(value = "catname",required = false) String _catName,
							   @RequestParam(value = "catindex",required = false) Integer _catIndex) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.editCategory(_catId,_catName,_catIndex);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2055);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/article/deleteArticle")
	public String deleteArticle(@RequestParam(value = "id",required = false) Long _articleId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.deleteArticle(_articleId);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2062);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@GetMapping(path = "/trending/getTrendingList")
	//公用api，还是get吧
	public String getTrendList(@RequestParam(value = "type",required = false) String _type) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			List<Trending> lt = new ArrayList<>();
			lt = articleService.getTrendingList();
			if(_type != null && _type.equals("banner")) {
				List<Trending> bannerList = new ArrayList<>();
				for(Trending t : lt) {
					//图片url至少要包含http://，所以肯定大于6 就算是//开头的，应该也大于6
					if(t.getBanner() != null && t.getBanner().length() > 6) {
						bannerList.add(t);
					}
				}
				lt = bannerList;
			}

			if(lt != null && lt.size() > 0) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");

				errMsg.put("data",lt);
				return JSON.toJSONString(errMsg);
			} else {
				errMsg.put("errcode",2071);
				errMsg.put("msg","没有找到推荐位内容");
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2072);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/trending/editTrending")
	public String editTrending(@RequestBody(required = false) Trending _req) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			if(_req != null)
			errMsg = articleService.editTrending(_req);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2075);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/trending/deleteTrending")
	public String deleteTrending(@RequestParam(value = "id",required = false) Integer _id) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = articleService.deleteTrending(_id);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2082);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/trending/addTrending")
	public String addTrending(@RequestBody(required = false) Trending _req) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			if(_req != null)
				errMsg = articleService.addTrending(_req);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",2075);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/article/addArticle")
	public String addArticle(@RequestBody(required = false) Article _a) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(_a != null && userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				//清空已有uid
				_a.setAuthor(0L);
				_a.setAuthor(uid);
				errMsg = articleService.addArticle(_a);
				return JSON.toJSONString(errMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",20116);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/article/editArticle")
	/**
	 * 后台管理员更改用户信息
	 * 获取json时，直接用@requestbody，而不是@requestparam获取
	 */
	public String editArticle(@RequestBody(required = false) Article _a) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			if(_a != null) {
				errMsg = articleService.editArticle(_a);
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",20127);
		errMsg.put("msg","ctrl try-catch错误");
		return JSON.toJSONString(errMsg);
	}





}
