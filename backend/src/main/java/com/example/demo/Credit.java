package com.example.demo;

import com.example.code.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/credit")
public class Credit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {
            request.setAttribute("user", Data.getUser());
            request.getRequestDispatcher("credit.jsp").forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {
            String credit = request.getParameter("credit");
            String username = Data.getUser();
            JSONArray data = null;
            JSONObject query = new JSONObject();
            query.put("username" , username);
            query.put("credit" , credit);
            JSONObject result = Data.dataHandler.increaseUserCredit(query);
            request.setAttribute("data", result.getString("data"));
            request.setAttribute("user", Data.getUser());
            if(result.getBoolean("status")){
                request.getRequestDispatcher("200.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("400.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}