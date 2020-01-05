package com.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable//角色表
{
  private Integer id;//主键ID
  private String name;//角色名称
  private String keyword;//关键字
  private String des;//详细
  private List<Menu> menus = new ArrayList();
  
  public List<Menu> getMenus()
  {
    return this.menus;
  }
  
  public void setMenus(List<Menu> menus)
  {
    this.menus = menus;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = (name == null ? null : name.trim());
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = (keyword == null ? null : keyword.trim());
  }
  
  public String getDes()
  {
    return this.des;
  }
  
  public void setDes(String des)
  {
    this.des = (des == null ? null : des.trim());
  }
}
