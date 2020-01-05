package com.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Emp implements Serializable
{
  private Integer id;//主键ID
  private String username;//登录名
  private String password;//密码
  private String realname;//姓名
  private Integer sex;//性别
  private Integer age;//年龄
  private String phone;//电话
  private List<Role> list = new ArrayList();
  
  public List<Role> getList()
  {
    return this.list;
  }
  
  public void setList(List<Role> list)
  {
    this.list = list;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = (username == null ? null : username.trim());
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = (password == null ? null : password.trim());
  }
  
  public String getRealname()
  {
    return this.realname;
  }
  
  public void setRealname(String realname)
  {
    this.realname = (realname == null ? null : realname.trim());
  }
  
  public Integer getSex()
  {
    return this.sex;
  }
  
  public void setSex(Integer sex)
  {
    this.sex = sex;
  }
  
  public Integer getAge()
  {
    return this.age;
  }
  
  public void setAge(Integer age)
  {
    this.age = age;
  }

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}
  
 
}
