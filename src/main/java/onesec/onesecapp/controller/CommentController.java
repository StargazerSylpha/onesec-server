package onesec.onesecapp.controller;

import com.alibaba.fastjson.JSON;
import onesec.onesecapp.bean.Comment;
import onesec.onesecapp.bean.User;
import onesec.onesecapp.service.CommentService;
import onesec.onesecapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class CommentController {
	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;
	@Autowired
	HttpServletRequest request;

	@GetMapping(path = "/comment/getCommentList")
	public String getCommentList(@RequestParam(value = "article",required = false) Long _articleId,
								 @RequestParam(value = "page",required = false) Integer _pageId ) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = commentService.getCommentList(_articleId,_pageId);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",5013);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/comment/deleteComment")
	public String deleteComment(@RequestParam(value = "cid",required = false) Long _cid) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = commentService.deleteComment(_cid);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",5023);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/comment/getUserComment")
	public String getUserComment(@RequestParam(value = "page",required = false) Integer _pageId) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				errMsg = commentService.getUserComment(uid,_pageId);
				return JSON.toJSONString(errMsg);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",5032);
		errMsg.put("msg","获取用户评论列表失败（try-catch错误）");
		return JSON.toJSONString(errMsg);

	}

	@PostMapping(path = "/comment/userDeleteComment")
	public String userDeleteComment(@RequestParam(value = "cid",required = false) Long _cid) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				errMsg = commentService.userDeleteComment(uid,_cid);
				return JSON.toJSONString(errMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",5044);
		errMsg.put("msg","try-catch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/comment/addComment")
	public String addComment(@RequestBody Comment _c) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				//清空已有uid
				_c.setAuthor(0L);
				_c.setAuthor(uid);
				errMsg = commentService.addComment(_c);
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",5053);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}
}
