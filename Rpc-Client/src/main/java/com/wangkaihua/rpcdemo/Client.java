package com.wangkaihua.rpcdemo;

import com.wangkaihua.rpcdemo.info.RpcInfo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * @desciption: 客户端测试主程序
 * @author: wangkaihua
 * @date: 2019/2/16 20:50
 */
public class Client {
    public static void main(String[] args) {
        RpcInfo rpcInfo = new RpcInfo();
        rpcInfo.setPackageName("com.wangkaihua.rpcdemo.dao");
        rpcInfo.setClassName("OrderDao");
        rpcInfo.setMethodName("getOrder");
        Object [] methodParams = {"iphone手机",1};
        rpcInfo.setMethodParams(methodParams);

        try {
            Socket socket = new Socket("127.0.0.1", 6666);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcInfo);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
