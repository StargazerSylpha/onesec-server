package onesec.onesecapp.service;

import com.alibaba.fastjson.JSON;
import onesec.onesecapp.bean.Category;
import onesec.onesecapp.mapper.UserMapper;
import onesec.onesecapp.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

@Service("userService")
public class UserService {

	@Autowired
	UserMapper userMapper;


	/**
	 * 颁发accessToken
	 * 传入uid
	 * 加密逻辑
	 * 离线加密，在线解密
	 * accessToken为Map<String,Object>类型
	 *
	 * 转化成json串后base64以utf-8方式加密
	 * {
	 *     "uid": uid,
	 *     "tokensign": sha1Hex（uid + onesec + sha1加密的密码 + onesecapp + 当前毫秒数除1kw）
	 * }
	 */
	public String getAccessToken(Long _uid) {

		try {

			if(_uid != null) {
				//抛异常直接返回error
				String sha1Password = userMapper.getUserByUid(_uid).getPassword();

				String tokenSign = DigestUtils.sha1Hex(Long.toString(_uid) + "onesec" + sha1Password + "onesecapp" + Long.toString(new Date().getTime() / 10000000));
				Map<String,Object> accessToken = new HashMap<>();
				accessToken.put("uid",_uid);
				accessToken.put("tokenSign",tokenSign);

				return Base64.getEncoder().encodeToString(JSON.toJSONString(accessToken).getBytes(StandardCharsets.UTF_8));
				//token 有效期2小时（1kw毫秒）
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证accessToken
	 * 拿到base64串后解开拿到uid和tokenSign
	 * 然后用新拿到的密码和原有的uid再算一次
	 */
	public boolean verifyAccessToken(String _accessToken) {
		if(!checkAccessToken(_accessToken)) return false;
		try {
			Map<String,Object> accessToken = new HashMap<>();
			//这里不加判断会报nullpointer错误，最好加一个
			if(_accessToken != null && _accessToken.length() >5) {
				accessToken = (Map<String,Object>)JSON.parse(new String(Base64.getDecoder().decode(_accessToken), StandardCharsets.UTF_8));
				Long getUid = Long.parseLong(accessToken.get("uid").toString());
				String sha1Password = userMapper.getUserByUid(getUid).getPassword();
				String getTokenSign = accessToken.get("tokenSign").toString();
				String calcTokenSign = DigestUtils.sha1Hex(Long.toString(getUid) + "onesec" + sha1Password + "onesecapp" + Long.toString(new Date().getTime() / 10000000));
				return getTokenSign.equals(calcTokenSign);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 检查username/nickname合法性
	 * 传入username/nickname
	 * 不为null，长度>=6 && <=20
	 */
	public boolean checkUsername(String _username) {
		return (_username != null && _username.length() >= 6 && _username.length() <= 20);
	}
	public boolean checkNickname(String _nickname) {
		return (_nickname != null && _nickname.length() >= 6 && _nickname.length() <= 20);
	}

	/**
	 * 检查password合法性
	 * 传入password
	 * 不为null，长度>=8 && <=20
	 */
	public boolean checkPassword(String _password) {
		return (_password != null && _password.length() >=8 && _password.length() <= 20);
	}

	/**
	 * 检查uid合法性
	 * uid >0 且不为null
	 */
	public boolean checkUid(Long _uid) {
		return (_uid != null && _uid > 0 );
	}

	/**
	 * 检查sex合法性
	 */
	public boolean checkSex(Integer _sex) {
		return _sex != null && _sex >= 0 && _sex <= 2;
	}

	/**
	 * 检查日期date合法性
	 * 不会写 直接返回true
	 */
	public boolean checkBirthday(String _birthday) {
		//一个可能看起来很复杂，写起来也很复杂，但其实逻辑很简单的日期格式判断
		return _birthday != null && _birthday.contains("-") &&
				(
						(_birthday.length() == 19 && _birthday.contains(":") && _birthday.contains(" ")) || (_birthday.length() == 10)
				);

	}
	public boolean checkBirthday(Timestamp ts) {
		if(ts == null || ts.toString().length() < 19) {
			return false;
		} else {
			return checkBirthday(ts.toString().substring(0,19));
		}
	}
	/**
	 * 检查邮箱合法性
	 * 只检查长度和是否包含@以及.
	 */
	public boolean checkEmail(String _email) {
		return _email != null && _email.length() >= 6 && _email.length() <= 50 && _email.contains("@") && _email.contains(".");

	}

	/**
	 * 检查accessToken合法性
	 */

	public boolean checkAccessToken(String _accessToken) {
		return (_accessToken != null && _accessToken.length() >0);
	}

	public Long getUidFromAccessToken(String _accessToken) throws Exception {
		if(_accessToken == null || _accessToken.length() <= 0) return 0L;

		Map<String,Object> accessToken = new HashMap<>();
		accessToken = (Map<String,Object>)JSON.parse(new String(Base64.getDecoder().decode(_accessToken), StandardCharsets.UTF_8));
		return Long.valueOf(accessToken.get("uid").toString());

	}


	/**
	 * 登录
	 * 传入用户名密码
	 * 返回包含errcode,msg,uid,username,accessToken的errMsg map集合
	 */
	public Map<String,Object> login(String _username, String _password) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(checkUsername(_username) && checkPassword(_password) ) {
			String sha1Password = DigestUtils.sha1Hex(_password);
			List<User> lu = userMapper.login(_username,sha1Password);
			if(lu.size() == 1 && lu.get(0).getUsername().equals(_username)) {
				errMsg.put("errcode",0);
				errMsg.put("msg","success");
				errMsg.put("uid",lu.get(0).getUid());
				errMsg.put("usertype",lu.get(0).getUsertype());
				errMsg.put("username",lu.get(0).getUsername());
				errMsg.put("accessToken",getAccessToken(lu.get(0).getUid()));
				return errMsg;
			}
		}
		return errMsg;
	}
	public Map<String,Object> register(String _username,String _password, String _email) throws Exception  {
		Map<String,Object> errMsg = new HashMap<>();

		if(!checkUsername(_username)) {
			errMsg.put("errcode",1006);
			errMsg.put("msg","用户名不合法！");
			return errMsg;
		}
		if(!checkPassword(_password)) {
			errMsg.put("errcode",1007);
			errMsg.put("msg","密码不合法！");
			return errMsg;
		}
		if(!checkEmail(_email)) {
			errMsg.put("errcode",1008);
			errMsg.put("msg","邮箱不合法！");
			return errMsg;
		}
		List<User> lu = userMapper.checkInfoExist(_username,_email);
		if(lu.size() != 0) {
			errMsg.put("errcode",1009);
			errMsg.put("msg","用户名或邮箱已存在！");
			return errMsg;
		}
		/**
		 * 此处注册日期只能插入1970-2038年的日期（timestamp范围问题），需要重构
		 */
		int regResult = userMapper.register(_username,_email,DigestUtils.sha1Hex(_password),new Timestamp(System.currentTimeMillis()));
		if(regResult == 1) {
			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			return errMsg;
		}
		errMsg.put("errcode",1010);
		errMsg.put("msg","注册失败！");
		return errMsg;

	}

	public User getUserInfoByUid(Long _uid) throws Exception {
		if(checkUid(_uid)) {
			return userMapper.getUserByUid(_uid);
		}
		return null;
	}

	public int userChangeInfo(Long _uid, String _nickname, int _sex, String _birthday) throws Exception {
		if (checkUid(_uid) && checkNickname(_nickname) && checkSex(_sex) && checkBirthday(_birthday)) {

			/**
			 * 此处注册日期只能插入1970-2038年的日期（timestamp范围问题），需要重构
			 * 其中范围的左边界是 1970-01-01 08:00:01！
			 * 所以插入00:00:00会报错！
			 */
			Timestamp ts = Timestamp.valueOf(_birthday + " 00:00:00");
			return userMapper.userChangeInfo(_uid,_nickname,_sex,ts);
		}
		return 0;
	}

	public int userChangePassword(Long _uid,String _oldPassword, String _newPassword) throws Exception {
		/**
		 * 重构：返回map,每个错误都有相应提示
		 */
		if(checkUid(_uid) && checkPassword(_oldPassword) && checkPassword(_newPassword) && !(_oldPassword.equals(_newPassword))) {
			User u = userMapper.getUserByUid(_uid);
			if(u != null && u.getPassword().equals(DigestUtils.sha1Hex(_oldPassword))) {
				return userMapper.userChangePassword(_uid,DigestUtils.sha1Hex(_newPassword));
			}
		}
		return 0;
	}

	public List<User> getUserList() throws Exception {
		/**
		 * 返回类型不为-1的用户列表（即没有注销的用户）
		 */
		List<User> lu = new ArrayList<>();

		lu = userMapper.getUserList();

		if(lu != null && lu.size()>0) {
			return lu;

		}
		return null;

	}

	public Map<String,Object> editUserInfo(User _u) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkUid(_u.getUid())) {
			errMsg.put("errcode",1021);
			errMsg.put("msg","用户名不合法");
			return errMsg;
		}
		/*checkUsertype*/
		if(!(_u.getUsertype() == 0 || _u.getUsertype() == 1)  ) {
			errMsg.put("errcode",1022);
			errMsg.put("msg","用户类型不合法");
			return errMsg;
		}
		if(!checkUsername(_u.getUsername())) {
			errMsg.put("errcode",1023);
			errMsg.put("msg","用户名不合法");
			return errMsg;
		}
		if(!checkNickname(_u.getNickname())) {
			errMsg.put("errcode",1024);
			errMsg.put("msg","用户昵称不合法");
			return errMsg;
		}
		if(!checkEmail(_u.getEmail())) {
			errMsg.put("errcode",1025);
			errMsg.put("msg","电子邮箱不合法");
			return errMsg;
		}
		if(!checkSex(_u.getSex())) {
			errMsg.put("errcode",1026);
			errMsg.put("msg","用户性别不合法");
			return errMsg;
		}
		if(!checkBirthday(_u.getBirthday())) {
			errMsg.put("errcode",1027);
			errMsg.put("msg","用户生日不合法");
			return errMsg;
		}
		List<User> lu = userMapper.checkInfoExist(_u.getUsername(), _u.getEmail());
		if(!(lu.size() == 1 && lu.get(0).getUid() == _u.getUid())) {
			errMsg.put("errcode",10210);
			errMsg.put("msg","用户名或邮箱已存在！");
			return errMsg;
		}

		int result = userMapper.editUserInfo(_u.getUid(),_u.getUsertype(),_u.getUsername(),_u.getNickname(),_u.getEmail(),_u.getSex(),_u.getBirthday());

		if(result == 1) {
			errMsg.put("errcode",0);
			errMsg.put("msg","success");
			return errMsg;
		} else {
			errMsg.put("errcode",1028);
			errMsg.put("msg","修改用户信息失败（影响行数不为1）");
			return errMsg;
		}
	}

	public Map<String,Object> editUserPassword(Long _uid,String _newPassword) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkUid(_uid)) {
			errMsg.put("errcode",1031);
			errMsg.put("msg","uid不合法");
			return errMsg;
		}
		if(!checkPassword(_newPassword)) {
			errMsg.put("errcode",1032);
			errMsg.put("msg","新密码不合法");
			return errMsg;
		}

		int result = userMapper.editUserPassword(_uid,DigestUtils.sha1Hex(_newPassword));
		if(result == 1) {
			errMsg.put("errcode",0);
			errMsg.put("msg","修改成功");
			return errMsg;
		} else {
			errMsg.put("errcode",1034);
			errMsg.put("msg","修改失败");
			return errMsg;
		}

	}
	public Map<String,Object> deleteUser(Long _uid) throws Exception {
		Map<String,Object> errMsg = new HashMap<>();
		if(!checkUid(_uid)) {
			errMsg.put("errcode",1041);
			errMsg.put("msg","uid不合法");
			return errMsg;
		}

		int result = userMapper.deleteUser(_uid);
		if(result == 1) {
			errMsg.put("errcode",0);
			errMsg.put("msg","删除成功");
			return errMsg;
		} else {
			errMsg.put("errcode",1042);
			errMsg.put("msg","删除失败");
			return errMsg;
		}

	}

	public boolean checkUserExist(Long _uid) throws Exception {
		//检查用户存在且未注销
		if(!checkUid(_uid)) return false;

		User u = userMapper.getUserByUid(_uid);
		return u!= null && u.getUid() == _uid && u.getUsertype() != -1;
	}


}

