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

@WebServlet("/api/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            JSONObject data2 =  new JSONObject(jb.toString());
            JSONObject data = Data.dataHandler.login(data2);
            if(data.getBoolean("status"))
            {
                Data.setUser(data2.getString("username"));
                response.setStatus(200);
            }
            else {
                response.setStatus(400);
            }
            out.print(data);
            out.flush();
        } catch (JSONException e) {
            response.setStatus(500);
        }
    }
}