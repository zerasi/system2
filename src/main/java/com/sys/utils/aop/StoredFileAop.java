package com.sys.utils.aop;

import com.sys.dao.TUserCarMapper;
import com.sys.entity.TUserCar;
import com.sys.utils.baidu.utils.Base64Util;
import com.sys.utils.baidu.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;

@Aspect
@Component
@Slf4j
public class StoredFileAop {

    @Autowired
    private TUserCarMapper tUserCarMapper;

    @Before("@annotation(storedFile)")
    public void storedFileBefore(JoinPoint point, StoredFile storedFile) throws Throwable {
        Object[] obj = point.getArgs();//获取目标方法参数信息
        for (Object argItem : obj) {
            if (argItem instanceof File) {
                byte[] imgData = FileUtil.readFileByBytes((File) argItem);
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                TUserCar tUserCar = new TUserCar();
                tUserCar.setBak1(imgParam);
                this.tUserCarMapper.insert(tUserCar);
            }
        }

    }

    @AfterReturning(value = "@annotation(storedFile)",returning = "obj")
    public void storedFileAfterReturning(Object obj,StoredFile storedFile) {
        System.out.println(obj);
    }
}
