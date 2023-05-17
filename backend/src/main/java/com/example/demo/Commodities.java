package com.example.demo;

import com.example.code.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/commodities")
public class Commodities extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray data =  Data.dataHandler.getCommoditiesList().getJSONArray("commoditiesList");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        out.print(data.toString());
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.isLogin()) {
            String action = request.getParameter("action");
            String search = request.getParameter("search");
            JSONArray data = null;
            JSONObject query = new JSONObject();
            if(action.equals("search_by_name")){
                query.put("name" , search);
                JSONObject temp = new JSONObject(Data.dataHandler.getCommoditiesByName(query).getString("data"));
                data =  temp.getJSONArray("commoditiesListByName");
            } else if (action.equals("search_by_category")) {
                query.put("category" , search);
                JSONObject temp = new JSONObject(Data.dataHandler.getCommoditiesByCategory(query).getString("data"));
                data =  temp.getJSONArray("commoditiesListByCategory");
            }else if (action.equals("search_by_price_ascending")) {
                query.put("sort" , true);
                JSONObject temp = new JSONObject(Data.dataHandler.getCommoditiesBySort(query).getString("data"));
                data =  temp.getJSONArray("getCommoditiesBySort");
            }else if (action.equals("search_by_price_descending")) {
                query.put("sort" , false);
                JSONObject temp = new JSONObject(Data.dataHandler.getCommoditiesBySort(query).getString("data"));
                data =  temp.getJSONArray("getCommoditiesBySort");
            }
            else{

                data =  Data.dataHandler.getCommoditiesList().getJSONArray("commoditiesList");
            }

            request.setAttribute("data", data);
            request.setAttribute("user", Data.getUser());
            request.getRequestDispatcher("commodities.jsp").forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}