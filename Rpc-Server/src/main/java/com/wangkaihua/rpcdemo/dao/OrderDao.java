package com.wangkaihua.rpcdemo.dao;

import com.wangkaihua.rpcdemo.anno.RpcClazz;
import com.wangkaihua.rpcdemo.anno.RpcMethod;

/**
 * @desciption: 服务端的服务
 * @author: wangkaihua
 * @date: 2019/2/16 21:08
 */
@RpcClazz
public class OrderDao {

    @RpcMethod
    public String getOrder(String name, Integer id) {
        return "获得订单名称："+ name;
    }

    public void addOrder(String name) {
        System.out.println("添加订单成功！");
    }
}
