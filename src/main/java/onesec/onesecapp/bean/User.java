package onesec.onesecapp.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class User {
	private long uid;
	private String username;
	private int usertype;
	private String password;
	private String email;
	private String nickname;
	private Timestamp birthday;
	private int sex;
	private String avatar;
	private Timestamp regtime;

	public User() {
	}

	public User(long uid, String username, int usertype, String password, String email, String nickname, Timestamp birthday, int sex, String avatar, Timestamp regtime) {
		this.uid = uid;
		this.username = username;
		this.usertype = usertype;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.birthday = birthday;
		this.sex = sex;
		this.avatar = avatar;
		this.regtime = regtime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Timestamp getBirthday() {
		return birthday;
	}


	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

}
