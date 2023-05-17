package com.example.demo;

import com.example.code.Data;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Data.getData();
    }

}
