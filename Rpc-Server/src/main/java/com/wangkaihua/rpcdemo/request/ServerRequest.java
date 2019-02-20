package com.wangkaihua.rpcdemo.request;

import com.wangkaihua.rpcdemo.info.RpcInfo;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desciption: Rpc服务端
 * @author: wangkaihua
 * @date: 2019/2/16 20:37
 */
public class ServerRequest {

    public void publishServer() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            ServerSocket socket = new ServerSocket(6666);
            while (true) {
                System.out.println("wait connect...");
                Socket accept = socket.accept();
                executorService.execute(new ServerHandler(accept));
                System.out.println("connect success!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
