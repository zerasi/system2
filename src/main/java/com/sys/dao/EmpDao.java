package com.sys.dao;

import com.sys.entity.Emp;
import com.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public abstract interface EmpDao {
    public abstract List<Emp> findAllemp();

    public abstract void insertEmp(Emp paramEmp);

    public abstract void insertEmpRole(int paramInt1, int paramInt2);

    public abstract void deleteEmpById(int paramInt);

    public abstract Emp findByEmpId(Integer paramInteger);

    public abstract List<Role> findRoleByEmpId(Integer paramInteger);

    public abstract void updateEmp(Emp emp);

    public abstract void deleteEmpRoleByEid(Integer id);

    public abstract List<Emp> empLogin(Emp emp);
}
