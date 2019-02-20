package com.wangkaihua.rpcdemo.info;

import java.io.Serializable;

/**
 * @desciption: rpc请求信息
 * @author: wangkaihua
 * @date: 2019/2/16 20:43
 */
public class RpcInfo implements Serializable {

    private String packageName;
    private String className;
    private String methodName;
    private Object [] methodParams;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(Object[] methodParams) {
        this.methodParams = methodParams;
    }
}
