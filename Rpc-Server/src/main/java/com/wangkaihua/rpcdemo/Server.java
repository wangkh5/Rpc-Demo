package com.wangkaihua.rpcdemo;

import com.wangkaihua.rpcdemo.request.ServerRequest;


/**
 * @desciption: 启动服务
 * @author: wangkaihua
 * @date: 2019/2/16 20:50
 */
public class Server {
    public static void main(String[] args) {
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.publishServer();
    }
}
