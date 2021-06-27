package onesec.onesecapp.controller;


import com.alibaba.fastjson.JSON;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.bean.User;
import onesec.onesecapp.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@CrossOrigin()
@RequestMapping(value = "/api")
/**
 * 需要重构！加上各变量不满足条件的返回！
 */
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	/**
	 * ////////////////////////////////////生产用//////////////////////////////////////
	 */

	@PostMapping(path = "/auth/login")
	public String login(@RequestParam(value = "username", required = false) String _username, @RequestParam(value = "password",required = false) String _password) {
		Map<String,Object> errMsg = new HashMap<>();
		try {

			errMsg = userService.login(_username,_password);
			if(errMsg.size() != 0) {
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1002);
		errMsg.put("msg","登录失败，请检查用户名和密码是否正确");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/auth/register")
	public String register(@RequestParam(value = "username", required = false) String _username,
						   @RequestParam(value = "password",required = false) String _password,
						   @RequestParam(value = "email",required = false) String _email) {
		Map<String,Object> errMsg = new HashMap<>();
		try {

			errMsg = userService.register(_username,_password,_email);
			return JSON.toJSONString(errMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(errMsg);
	}


	@PostMapping(path = "/user/getUserInfo")
	public String getUserInfo(/*@RequestParam(value = "accessToken",required = false) String _accessToken*/) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				User u = userService.getUserInfoByUid(uid);
				if(u != null) {

					u.setPassword(null);
					errMsg.put("errcode",0);
					errMsg.put("msg","success");
					errMsg.put("data",u);
					return JSON.toJSONString(errMsg);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1003);
		errMsg.put("msg","获取用户信息失败");
		return JSON.toJSONString(errMsg);

	}

	@PostMapping(path = "/user/changeInfo")
	public String userChangeInfo(@RequestParam(value = "nickname",required = false) String _nickname,
							  @RequestParam(value = "sex",required = false) Integer _sex,
							  @RequestParam(value = "birthday",required = false) String _birthday) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				int updateResult = userService.userChangeInfo(uid,_nickname,_sex,_birthday);
				if(updateResult == 1) {

					errMsg.put("errcode",0);
					errMsg.put("msg","success");

					return JSON.toJSONString(errMsg);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1004);
		errMsg.put("msg","修改用户信息失败");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/user/changePassword")
	public String userChangePassword(@RequestParam(value = "oldpassword",required = false) String _oldPassword,
									 @RequestParam(value = "newpassword",required = false) String _newPassword) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			String accessToken = request.getHeader("Authorization") != null ? request.getHeader("Authorization") : request.getParameter("accessToken");
			if(userService.verifyAccessToken(accessToken)) {
				Long uid = userService.getUidFromAccessToken(accessToken);
				int updateResult = userService.userChangePassword(uid,_oldPassword,_newPassword);

				if(updateResult == 1) {

					errMsg.put("errcode",0);
					errMsg.put("msg","success");

					return JSON.toJSONString(errMsg);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1005);
		errMsg.put("msg","修改用户密码失败");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/user/getUserList")
	public String getUserList() {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			List<User> lu = new ArrayList<>();
			lu = userService.getUserList();
			if(lu != null && lu.size() > 0) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				errMsg.put("data",lu);
				return JSON.toJSONString(errMsg);
			} else {
				errMsg.put("errcode",1011);
				errMsg.put("msg","没有找到内容");
				return JSON.toJSONString(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1012);
		errMsg.put("msg","ctrl-trycatch错误");
		return JSON.toJSONString(errMsg);
	}

	@PostMapping(path = "/user/editUserInfo")
	/**
	 * 后台管理员更改用户信息
	 * 获取json时，直接用@requestbody，而不是@requestparam获取
	 */
	public String editUserInfo(@RequestBody(required = false) User _req) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			if(_req != null) {
				return JSON.toJSONString(userService.editUserInfo(_req));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1029);
		errMsg.put("msg","ctrl try-catch错误");
		return JSON.toJSONString(errMsg);
	}
	@PostMapping(path = "/user/editUserPassword")
	public String editUserPassword(@RequestParam(value = "uid",required = false) Long _uid,
								   @RequestParam(value = "password",required = false) String _newPassword) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = userService.editUserPassword(_uid,_newPassword);
			return JSON.toJSONString(errMsg);
		}catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1035);
		errMsg.put("msg","ctrl错误");
		return JSON.toJSONString(errMsg);

	}
	@PostMapping(path = "/user/deleteUser")
	public String editUserPassword(@RequestParam(value = "uid",required = false) Long _uid) {
		Map<String,Object> errMsg = new HashMap<>();
		try {
			errMsg = userService.deleteUser(_uid);
			return JSON.toJSONString(errMsg);
		}catch (Exception e) {
			e.printStackTrace();
		}
		errMsg.put("errcode",1043);
		errMsg.put("msg","ctrl错误");
		return JSON.toJSONString(errMsg);

	}




}
