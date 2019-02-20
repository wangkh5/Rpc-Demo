package com.wangkaihua.rpcdemo.util;

import com.alibaba.fastjson.JSON;
import com.wangkaihua.rpcdemo.anno.RpcClazz;
import com.wangkaihua.rpcdemo.anno.RpcMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desciption: 解析注解工具类
 * @author: wangkaihua
 * @date: 2019/2/20 16:52
 */
public class AnnoUtils {

    private AnnoUtils() {
    }

    /**
     * 1. 获取类路径
     * 2. 获取所有的dao类
     * 3. 解析所有注解
     * 4. 发布我们需要发布的方法
     * @param packageName
     */
    public static Map<String, List<Map<String, List<String>>>> parseAnno(String packageName) throws ClassNotFoundException {
        // 根据包名获取该包下所有的类名
        String [] classNames = getAllClassName(packageName);

        // 类名：所有方法（方法：所有参数类型）
        Map<String, List<Map<String, List<String>>>> result = new HashMap<>();

        // 获取类名和方法名的映射
        for (String className: classNames) {
            // 根据包名+类名 反射获取所有Class类
            Class<?> clazz = Class.forName(packageName + "." + className);
            // 遍历所有存在RpcClazz注解的类
            // 不存在
            if (!clazz.isAnnotationPresent(RpcClazz.class)) {
                continue;
            }
            // 存在
            // 获取该类所有存在RpcMethod的方法并获取该方法的所有参数类型
            List<Map<String, List<String>>> methodNameToParamsTypeMapList = getAllAnnoMethodAndParams(clazz);
            result.put(className, methodNameToParamsTypeMapList);
        }
        return result;
    }

    /**
     * 此方法多引入一层list 原因是方法可能会重载方法名称相同参数列表不同
     *
     * @param clazz
     * @return
     */
    private static List<Map<String, List<String>>> getAllAnnoMethodAndParams(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<Map<String, List<String>>> result = new ArrayList<>();
        // 方法名和该方法所有参数类型的映射
        for (Method method: methods) {
            Map<String, List<String>> methodNameToParamsTypeMap = new HashMap<>();
            // 不存在
            if (!method.isAnnotationPresent(RpcMethod.class)) {
                continue;
            }
            // 存在，获取所有的参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            List<String> paramsNameList = new ArrayList<>();
            for (Class c: parameterTypes) {
                paramsNameList.add(c.getSimpleName());
            }
            methodNameToParamsTypeMap.put(method.getName(), paramsNameList);
            result.add(methodNameToParamsTypeMap);
        }
        return result;
    }

    private static String[] getAllClassName(String packageName) {
        String basePath = AnnoUtils.class.getResource("/").getPath();
        packageName = packageName.replace(".", "/");
        File file = new File(basePath + packageName);
        // 获得所有的class文件的名称
        String[] filesName = file.list();
        // 获得类名
        for (int i = 0; i<filesName.length; i++) {
            filesName[i] = filesName[i].replace(".class", "");
        }
        return filesName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Map<String, List<Map<String, List<String>>>> result = parseAnno("com.wangkaihua.rpcdemo.dao");
        System.out.println(JSON.toJSON(result));
    }

}
