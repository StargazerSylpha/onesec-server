package onesec.onesecapp.mapper;

import onesec.onesecapp.bean.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserMapper {

	@Select("select * from user where uid = #{_uid}")
 	public User getUserByUid(Long _uid) throws Exception;

	@Select("select * from user where username = #{_username} and password = #{_password} and usertype != -1")
	public List<User> login(String _username, String _password) throws Exception;

	/**
	 * 存timestamp时将java的变量类型转换成timestamp，然后sql语句不要加单引号！会报jdbcType=null的错！
	 */
	@Update("update user set nickname = #{_nickname}, sex = #{_sex}, birthday = #{_birthday,jdbcType=TIMESTAMP} where uid = #{_uid}")
	public int userChangeInfo(Long _uid, String _nickname, int _sex, Timestamp _birthday);

	@Update("update user set password = #{_newpassword} where uid = #{_uid}")
	public int userChangePassword(Long _uid,String _newpassword) throws Exception;

	@Select("select uid from user where username = #{_username} or email = #{_email}")
	public List<User> checkInfoExist(String _username, String _email) throws Exception;

	@Insert("insert into user(username,password,email,nickname,regtime) values(#{_username}, #{_password}, #{_email}, #{_username}, #{_regtime})")
	public int register(String _username, String _email, String _password,Timestamp _regtime) throws Exception;
	@Select("select uid,usertype,username,email,nickname,birthday,sex,avatar,regtime from user where usertype != -1 order by uid desc,regtime desc;")
	public List<User> getUserList() throws Exception;

	@Update("update user set usertype = #{_usertype}, username = #{_username}, nickname = #{_nickname}, email = #{_email}, sex = #{_sex}, birthday = #{_birthday,jdbcType=TIMESTAMP} where uid = #{_uid} and usertype != -1;")
	public int editUserInfo(Long _uid,int _usertype,String _username,String _nickname,String _email,int _sex,Timestamp _birthday);

	@Update("update user set password = #{_newPassword} where uid = #{_uid} and usertype != -1;")
	public int editUserPassword(Long _uid,String _newPassword) throws Exception;

	@Update("update user set usertype = -1, password = 0,nickname = '[已注销]',avatar = '' where uid = #{_uid};")
	public int deleteUser(Long _uid) throws Exception;
}
