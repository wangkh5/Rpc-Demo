package com.wangkaihua.rpcdemo.dao;

import com.wangkaihua.rpcdemo.anno.RpcClazz;
import com.wangkaihua.rpcdemo.anno.RpcMethod;

/**
 * @desciption: TODO
 * @author: wangkaihua
 * @date: 2019/2/20 18:00
 */
@RpcClazz
public class UserDao {

    @RpcMethod
    public void getUser(Integer id) {
        System.out.println("获取用户"+id);
    }

    @RpcMethod
    public void getUser(Integer id, String[] str) {
        System.out.println("获取用户"+id);
    }

    @RpcMethod
    public void addUser(Integer id) {
        System.out.println("添加用户"+id);
    }
}
