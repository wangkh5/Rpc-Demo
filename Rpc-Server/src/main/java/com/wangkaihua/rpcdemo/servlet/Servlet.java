package com.wangkaihua.rpcdemo.servlet;

import com.alibaba.fastjson.JSON;
import com.wangkaihua.rpcdemo.util.AnnoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @desciption: 发布所有的服务
 * @author: wangkaihua
 * @date: 2019/2/20 19:09
 */
@WebServlet("/service")
public class Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<Map<String, List<String>>>> stringListMap = null;
        try {
            stringListMap = AnnoUtils.parseAnno("com.wangkaihua.rpcdemo.dao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSON(stringListMap).toString());
        writer.flush();
        writer.close();
    }
}
