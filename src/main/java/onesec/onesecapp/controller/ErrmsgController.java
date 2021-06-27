package onesec.onesecapp.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin

public class ErrmsgController implements ErrorController {
	@Autowired
	HttpServletRequest request;
	@Override
	//全局异常处理，将400、500等错误包装成固定方式，方便前端处理
	//不能不写requestmapping /error，不然不会显示！因为springboot处理这些错误都是跳转到/error页面！
	//https://caidingnu.blog.csdn.net/article/details/86547371
	@RequestMapping("/error")
	public String getErrorPath() {
		Map<String,Object> errMsg = new HashMap<>();
		Integer errCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		errMsg.put("errcode",errCode);
		errMsg.put("msg",errCode + "错误");
		return JSON.toJSONString(errMsg);
	}
}
