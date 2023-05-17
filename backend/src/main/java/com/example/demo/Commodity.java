package com.example.demo;

import com.example.code.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Commodity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String[] pathInfo = request.getPathInfo().split("/");
                JSONObject query = new JSONObject();
                int id = Integer.parseInt(pathInfo[1]);
                query.put("id" , id);
                JSONObject result = Data.dataHandler.getCommodityById(query);
                PrintWriter out = response.getWriter();

                if(result.getBoolean("status")) {
                    response.setStatus(200);
                }else {
                    response.setStatus(404);
                }
                out.print(result);
                out.flush();
            }catch (Exception e){
                response.setStatus(404);
            }



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {
            String action = request.getParameter("action");
            String username = Data.getUser();
            JSONObject query = new JSONObject();
            String[] pathInfo = request.getPathInfo().split("/");
            int commodityId = Integer.parseInt(pathInfo[1]);
            JSONObject result = null;
            query.put("username" , username);
            query.put("commodityId" , commodityId);

            if(action.equals("comment")){
                String message = request.getParameter("message");
                query.put("userEmail" , Data.getUserEmail());
                query.put("text" ,message);
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                query.put("date" , date);
                result = Data.dataHandler.addComment(query);

            } else if (action.equals("rate")) {
                String quantity = request.getParameter("quantity");
                query.put("score" , quantity);
                result = Data.dataHandler.rateCommodity(query);
            }else if (action.equals("add")) {
                result = Data.dataHandler.addToBuyList(query);
            }else if (action.equals("like") ||action.equals("dislike") ) {
                String vote = request.getParameter("vote");
                String commentId = request.getParameter("id");
                query.put("commentId" , commentId);
                query.put("vote" , vote);
                 result = Data.dataHandler.addCommentVote(query);
            }
            request.setAttribute("user",username);
            request.setAttribute("data", result.getString("data"));
            if (result.getBoolean("status")){
                request.getRequestDispatcher("/200.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/400.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
