package com.fda.model;
import java.sql.Timestamp;

public class User {
   private int userid;
   private String name;
   private String username;
   private String password;
   private String email;
   private String phone;
   private String address;
   private Timestamp createdDate;
   private Timestamp lastLoginDate;
   
   public User()
   {
	   
   }

public User(int userid, String name, String username, String password, String email, String phone, String address,
		 Timestamp createdDate, Timestamp lastLoginDate) {
	super();
	this.userid = userid;
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.phone = phone;
	this.address = address;
	this.createdDate = createdDate;
	this.lastLoginDate = lastLoginDate;
}

public int getUserid() {
	return userid;
}

public void setUserid(int userid) {
	this.userid = userid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
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

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}





public Timestamp getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Timestamp createdDate) {
	this.createdDate = createdDate;
}

public Timestamp getLastLoginDate() {
	return lastLoginDate;
}

public void setLastLoginDate(Timestamp lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
}
	

	

}
