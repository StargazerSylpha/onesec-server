package onesec.onesecapp.service;

import com.alibaba.fastjson.JSON;
import onesec.onesecapp.bean.Article;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.bean.Comment;
import onesec.onesecapp.mapper.ArticleMapper;
import onesec.onesecapp.mapper.CommentMapper;
import onesec.onesecapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentService {

	@Autowired
	CommentMapper commentMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	ArticleMapper articleMapper;

	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;

	public Map<String,Object> getCommentList(Long _articleId,Integer _pageId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		/**
		 * 页码pageid若不传或<0 直接传0，从第一页开始加载
		 */
		int pageId = (_pageId == null || _pageId < 0)? 0 : _pageId;

		List<Comment> lc = new ArrayList<>();
		int resultSize;
		if(_articleId != null) {
			if(_articleId > 0) {
				lc = commentMapper.getArticleCommentList(_articleId,pageId * 20);
				resultSize = commentMapper.getArticleCommentListSize(_articleId);
			} else {
				errMsg.put("errcode",5012);
				errMsg.put("msg","评论所属文章不存在");
				return errMsg;
			}

		} else {
			lc = commentMapper.getCommentList(pageId * 20);
			resultSize = commentMapper.getCommentListSize();
		}

		if(lc != null && lc.size()>0) {
			List<Map<String,Object>> data = new ArrayList<>();
			for( Comment c : lc) {
				/**
				 * 不能用map.clear
				 * 每次list.add添加的是同一个map，其指针相同，代表同一地址
				 * 销毁map:直接令其=null
				 */

				Map<String,Object> dataItem = new HashMap<>();
				dataItem = c.toMap();
				dataItem.put("authorNickname",userMapper.getUserByUid(c.getAuthor()).getNickname());
				dataItem.put("authorAvatar",userMapper.getUserByUid(c.getAuthor()).getAvatar());
				Article articleObj = articleMapper.getArticleById(c.getArticle());
				dataItem.put("articleTitle",articleObj == null ? "[文章已删除]" : articleObj.getTitle());
				data.add(dataItem);
				dataItem = null;
			}

			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			errMsg.put("size",resultSize);
			errMsg.put("data",data);
			return errMsg;
		}
		errMsg.put("errcode",5011);
		errMsg.put("msg","未找到任何评论内容");
		return errMsg;
	}

	public boolean checkCommentId(Long _cid) {
		return _cid != null && _cid > 0;
	}

	public Map<String,Object> deleteComment(Long _cid) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCommentId(_cid)) {
			errMsg.put("errcode",5021);
			errMsg.put("msg","评论ID有误");
			return errMsg;
		} else  {
			int result = commentMapper.deleteComment(_cid);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",5022);
				errMsg.put("msg","删除评论失败");
				return errMsg;
			}
		}
	}

	public Map<String,Object> getUserComment(Long _uid,Integer _pageId) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		/**
		 * 页码pageid若不传或<0 直接传0，从第一页开始加载
		 */
		int pageId = (_pageId == null || _pageId < 0)? 0 : _pageId;

		List<Comment> lc = new ArrayList<>();
		int resultSize = -1;

		if(userService.checkUid(_uid)) {
			lc = commentMapper.getUserComment(_uid,pageId * 20);
			resultSize = commentMapper.getUserCommentSize(_uid);
		} else {
			errMsg.put("errcode",5033);
			errMsg.put("msg","UID不合法");
			return errMsg;
		}

		if(lc != null && lc.size()>0) {
			List<Map<String,Object>> data = new ArrayList<>();
			for( Comment c : lc) {
				/**
				 * 不能用map.clear
				 * 每次list.add添加的是同一个map，其指针相同，代表同一地址
				 * 销毁map:直接令其=null
				 */

				Map<String,Object> dataItem = new HashMap<>();
				dataItem = c.toMap();
				/*
				dataItem.put("authorNickname",userMapper.getUserByUid(c.getAuthor()).getNickname());
				dataItem.put("authorAvatar",userMapper.getUserByUid(c.getAuthor()).getAvatar());
				 */
				Article articleObj = articleMapper.getArticleById(c.getArticle());
				dataItem.put("articleTitle",articleObj == null ? "[文章已删除]" : articleObj.getTitle());
				data.add(dataItem);
				dataItem = null;
			}

			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			errMsg.put("size",resultSize);
			errMsg.put("data",data);
			return errMsg;
		}
		errMsg.put("errcode",5031);
		errMsg.put("msg","未找到任何评论内容");
		return errMsg;
	}

	public Map<String,Object> userDeleteComment(Long _uid, Long _cid) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkCommentId(_cid)) {
			errMsg.put("errcode",5041);
			errMsg.put("msg","评论ID有误");
			return errMsg;
		} else if(!userService.checkUid(_uid)) {
			errMsg.put("errcode",5042);
			errMsg.put("msg","UID有误");
			return errMsg;
		} else  {
			//方便起见，不搞"您没有权限了"
			int result = commentMapper.userDeleteComment(_uid,_cid);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",5043);
				errMsg.put("msg","删除失败（并非评论作者，或已经删除）");
				return errMsg;
			}
		}
	}

	public Map<String,Object> addComment(Comment _c) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();

		//检查文章是否存在
		if(articleService.checkArticleExist(_c.getArticle()) && userService.checkUid(_c.getAuthor())) {

			if(_c.getComment() == null || _c.getComment().length() < 1 ) {
				errMsg.put("errcode",5054);
				errMsg.put("msg","评论不能为空！");
				return errMsg;
			}
			if(_c.getAuthor() == null || !userService.checkUserExist(_c.getAuthor()) ) {
				errMsg.put("errcode",5055);
				errMsg.put("msg","用户不存在或已注销！");
				return errMsg;
			}
			String commentDetail = HtmlUtils.htmlEscape(_c.getComment());
			int result = commentMapper.addComment(_c.getArticle(),_c.getAuthor(),commentDetail);
			if(result == 1) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				return errMsg;
			} else {
				errMsg.put("errcode",5052);
				errMsg.put("msg","评论失败");
				return errMsg;
			}

		} else {
			errMsg.put("errcode",5051);
			errMsg.put("msg","文章不存在");
			return errMsg;
		}

	}




}
