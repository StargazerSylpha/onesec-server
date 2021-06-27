package onesec.onesecapp.config;

import onesec.onesecapp.interceptor.AdminInterceptor;
import onesec.onesecapp.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry _registry) {
		List<String> pathList = new ArrayList<>();
		pathList.add("/api/user/getUserInfo");
		pathList.add("/api/user/changeInfo");
		pathList.add("/api/user/changePassword");
		pathList.add("/api/comment/getUserComment");
		pathList.add("/api/comment/userDeleteComment");
		pathList.add("/api/comment/addComment");
		//注册auth拦截器
		List<String> adminList = new ArrayList<>();
		//或："/api/category/**"
		adminList.add("/api/category/addCategory");
		adminList.add("/api/category/deleteCategory");
		adminList.add("/api/category/editCategory");

		adminList.add("/api/user/getUserList");
		adminList.add("/api/user/editUserInfo");
		adminList.add("/api/user/editUserPassword");
		adminList.add("/api/article/deleteArticle");
		adminList.add("/api/trending/editTrending");
		adminList.add("/api/trending/deleteTrending");
		adminList.add("/api/trending/addTrending");
		adminList.add("/api/comment/deleteComment");
		adminList.add("/api/article/addArticle");
		adminList.add("/api/article/editArticle");

		_registry.addInterceptor(new AuthInterceptor()).addPathPatterns(pathList);
		/**
		 * 不要在拦截器类里使用_response.getWriter() ！
		 * 多个需要输出自定义信息的拦截器 使用getWriter时，会报错，出现
		 * getWriter() has already been called for this response，illegalStateException
		 * 并导致某些拦截器即使正确使用也无法显示对应结果，出现500错误
		 * 请使用outputstream以byte输出errmsg！
		 */
		_registry.addInterceptor(new AdminInterceptor()).addPathPatterns(adminList);


	}
}
