package com.gupao.delegate.mvc;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req,resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String mid = req.getParameter("mid");
        if(uri.equals("getMemberById")){
            new MemberController().getMemberById(mid);
        }else if(uri.equals("getOrderById")){
            new OrderController().getOrderById(mid);
        }else{
            throw new RuntimeException("404 Not Found Service");
        }
    }
}
