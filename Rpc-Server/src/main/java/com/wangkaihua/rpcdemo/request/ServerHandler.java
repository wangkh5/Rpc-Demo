package com.wangkaihua.rpcdemo.request;

import com.wangkaihua.rpcdemo.info.RpcInfo;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @desciption: 多线程处理每一个连接请求
 * @author: wangkaihua
 * @date: 2019/2/16 20:41
 */
public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo)inputStream.readObject();
            Class clazz = Class.forName(rpcInfo.getPackageName() + "." + rpcInfo.getClassName());
            // 获取方法的参数
            Object [] params = rpcInfo.getMethodParams();
            // 方法的参数类型
            Class [] paramsType = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsType[i] = params[i].getClass();
            }
            // clazz.getMethod不能获取私有方法，获取私有方法要用getDeclaredMethod()
            Method method = clazz.getMethod(rpcInfo.getMethodName(), paramsType);
            // 反射调用
            Object invoke = method.invoke(clazz.newInstance(), params);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
