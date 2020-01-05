package com.sys.service.impl;

import java.util.List;

import com.sys.dao.EmpDao;
import com.sys.entity.Emp;
import com.sys.entity.Role;
import com.sys.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpserviceImpl
        implements EmpService {
    @Autowired
    private EmpDao dao;

    public List<Emp> findAllemp() {
        return this.dao.findAllemp();
    }

    public void empAdd(Emp emp, String roleIds) {
        if (emp.getId() == null) {
            this.dao.insertEmp(emp);
            int i = emp.getId().intValue();
            String[] arr = roleIds.split(",");
            for (String s : arr) {
                this.dao.insertEmpRole(Integer.parseInt(s), emp.getId());
            }
        } else {
            dao.updateEmp(emp);
            dao.deleteEmpRoleByEid(emp.getId());
            String[] arr = roleIds.split(",");
            for (String s : arr) {
                this.dao.insertEmpRole(Integer.parseInt(s), emp.getId());
            }
        }
    }

    public void deleteEmpById(String ids) {
        String[] arr = ids.split(",");
        String[] arrayOfString1;
        int j = (arrayOfString1 = arr).length;
        for (int i = 0; i < j; i++) {
            String s = arrayOfString1[i];
            this.dao.deleteEmpById(Integer.parseInt(s));
        }
    }

    public Emp findByEmpId(int parseInt) {
        Emp emp = this.dao.findByEmpId(parseInt);
        List<Role> list = this.dao.findRoleByEmpId(Integer.valueOf(parseInt));
        emp.setList(list);
        return emp;
    }


    public List<Emp> empLogin(Emp emp) {
        return dao.empLogin(emp);
    }
}
