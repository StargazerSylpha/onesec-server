package onesec.onesecapp.interceptor;

import com.alibaba.fastjson.JSON;
import onesec.onesecapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest _request, HttpServletResponse _response, Object _handler) throws Exception {

		_request.setCharacterEncoding("utf-8");
		_response.setCharacterEncoding("utf-8");
		_response.setContentType("application/json");
		String accessToken = _request.getHeader("Authorization") != null ? _request.getHeader("Authorization") : _request.getParameter("accessToken");
		Map<String,Object> errMsg = new HashMap<>();

		try {
			if(_request.getMethod().toUpperCase().equals("OPTIONS")) return true;
			if(userService == null) {
				userService = (UserService) WebApplicationContextUtils.getRequiredWebApplicationContext(_request.getServletContext()).getBean("userService");
			}
			if(userService.checkAccessToken(accessToken) && userService.verifyAccessToken(accessToken) ) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		errMsg.put("errcode",1001);
		errMsg.put("msg","您没有登录！");
		//PrintWriter pw = _response.getWriter();
		//pw.print(JSON.toJSONString(errMsg));
		//pw.flush();
		//pw.close();
		OutputStream os = _response.getOutputStream();
		os.write(JSON.toJSONBytes(errMsg));
		return false;
	}

}
