package com.example.demo;


import com.example.code.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/logout")
public class Logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Data.removeUser();
        response.sendRedirect(request.getContextPath());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean status;
        String message;
        JSONObject ans = new JSONObject();
        PrintWriter out = response.getWriter();
        if(Data.isLogin()) {

            response.setStatus(200);
            status =true;
            message = "bye"+ Data.getUser();
            Data.removeUser();
        }else{
            response.setStatus(400);
            status =false;
            message = "not login!!!";
        }
        ans.put("status" , status);
        ans.put("message",message);
        out.print(ans);
        out.flush();
    }
}