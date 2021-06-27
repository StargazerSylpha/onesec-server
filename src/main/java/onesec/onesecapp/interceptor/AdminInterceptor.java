package onesec.onesecapp.interceptor;

import com.alibaba.fastjson.JSON;
import onesec.onesecapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminInterceptor implements HandlerInterceptor {

	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest _request, HttpServletResponse _response, Object _handler) throws Exception {
		_request.setCharacterEncoding("utf-8");
		_response.setCharacterEncoding("utf-8");
		_response.setContentType("application/json");
		String accessToken = _request.getHeader("Authorization") != null ? _request.getHeader("Authorization") : _request.getParameter("accessToken");
		Map<String,Object> errMsg = new HashMap<>();
		OutputStream os = _response.getOutputStream();
		try {
			//https://www.cnblogs.com/penghq/p/13163779.html 拦截器导致的跨域问题
			if(_request.getMethod().toUpperCase().equals("OPTIONS")) return true;
			if(userService == null) {
				userService = (UserService) WebApplicationContextUtils.getRequiredWebApplicationContext(_request.getServletContext()).getBean("userService");
			}
			if(userService.checkAccessToken(accessToken) && userService.verifyAccessToken(accessToken) ) {
				//确认已登录，获取用户类型
				if(userService.getUserInfoByUid(userService.getUidFromAccessToken(accessToken)).getUsertype() == 1) {
					return true;
				} else {
					errMsg.put("errcode",4011);
					errMsg.put("msg","没有权限");
					os.write(JSON.toJSONBytes(errMsg));
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		errMsg.put("errcode",1001);
		errMsg.put("msg","您没有登录！");

		os.write(JSON.toJSONBytes(errMsg));
		return false;
	}
}
