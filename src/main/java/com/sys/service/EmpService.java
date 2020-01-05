package com.sys.service;

import com.sys.entity.Emp;

import java.util.List;

public abstract interface EmpService
{
  public abstract List<Emp> findAllemp();
  
  public abstract void empAdd(Emp paramEmp, String paramString);
  
  public abstract void deleteEmpById(String paramString);
  
  public abstract Emp findByEmpId(int paramInt);

public abstract List<Emp> empLogin(Emp emp);
}
