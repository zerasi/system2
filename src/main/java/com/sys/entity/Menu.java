package com.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable
{
  private Integer id;//主键ID
  private String name;//菜单名称
  private String page;//对应地址
  private String des;//详细
  private Menu pmenu;//父节点
  private List<Menu> childrenMenus = new ArrayList();//子节点
  
  public List<Menu> getChildren()
  {
    return this.childrenMenus;
  }
  
  public String getText()
  {
    return this.name;
  }
  
  public Menu getPmenu()
  {
    return this.pmenu;
  }
  
  public void setPmenu(Menu pmenu)
  {
    this.pmenu = pmenu;
  }
  
  public List<Menu> getChildrenMenus()
  {
    return this.childrenMenus;
  }
  
  public void setChildrenMenus(List<Menu> childrenMenus)
  {
    this.childrenMenus = childrenMenus;
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
  
  public String getPage()
  {
    return this.page;
  }
  
  public void setPage(String page)
  {
    this.page = (page == null ? null : page.trim());
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
