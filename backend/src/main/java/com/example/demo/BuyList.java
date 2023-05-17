package com.example.demo;

import com.example.code.Data;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buyList")
public class BuyList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {

                String username = Data.getUser();
                JSONObject query = new JSONObject();
                query.put("username" , username);
                JSONObject result = Data.dataHandler.getUserById(query);
                request.setAttribute("user", username);

                JSONObject data = new JSONObject(result.getString("data"));
                request.setAttribute("data", data);
                request.getRequestDispatcher("/buyList.jsp").forward(request, response);



        }
        else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {
            String action = request.getParameter("action");
            String username = Data.getUser();
            JSONObject query = new JSONObject();
            JSONObject result = null;
            query.put("username" , username);

            if(action.equals("pay")){
              result = Data.dataHandler.buyCart(query);

            } else if (action.equals("remove")) {
                String commodityId = request.getParameter("commodityId");
                query.put("commodityId" , commodityId);
                result = Data.dataHandler.removeFromBuyList(query);
            }else if (action.equals("discount")) {
                String discountCode = request.getParameter("discountCode");
                query.put("discountCode" , discountCode);
                result = Data.dataHandler.applyDiscount(query);
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
