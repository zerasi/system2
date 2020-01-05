package com.sys.utils;

import java.io.Serializable;
import java.util.List;

public class PageBean<T>
  implements Serializable
{
  private int total;
  private List<T> rows;
  private int totalPage;
  private int startIndex;
  private int pageSize;
  private int pageNumber;
  private int start;
  private int end;
  
  public void suan()
  {
    this.start = 1;
    this.end = 10;
    if (this.totalPage < 10)
    {
      this.start = 1;
      this.end = getTotalPage();
    }
    else
    {
      this.start = (getPageNumber() - 4);
      this.end = (getPageNumber() + 5);
      if (this.start < 1)
      {
        this.start = 1;
        this.end = 10;
      }
      if (this.end > getTotalPage())
      {
        this.end = getTotalPage();
        this.start = (getTotalPage() - 9);
      }
    }
  }
  
  public int getStart()
  {
    suan();
    return this.start;
  }
  
  public void setStart(int start)
  {
    this.start = start;
  }
  
  public int getEnd()
  {
    suan();
    return this.end;
  }
  
  public void setEnd(int end)
  {
    this.end = end;
  }
  
  public int getTotalPage()
  {
    return this.totalPage = getTotal() % getPageSize() == 0 ? getTotal() / getPageSize() : 
      getTotal() / getPageSize() + 1;
  }
  
  public void setTotalPage(int totalPage)
  {
    this.totalPage = totalPage;
  }
  
  public int getStartIndex()
  {
    return this.startIndex = (getPageNumber() - 1) * getPageSize();
  }
  
  public void setStartIndex(int startIndex)
  {
    this.startIndex = startIndex;
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setTotal(int total)
  {
    this.total = total;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }
  
  public int getPageNumber()
  {
    return this.pageNumber;
  }
  
  public void setPageNumber(int pageNumber)
  {
    this.pageNumber = pageNumber;
  }
  
  public List<T> getRows()
  {
    return this.rows;
  }
  
  public void setRows(List<T> rows)
  {
    this.rows = rows;
  }
  
  public PageBean(int pageNumber, int pageSize)
  {
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
  }
  
  public PageBean() {}
}
