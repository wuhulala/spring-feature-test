package com.wuhulala.spring.session.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                String attributeName = req.getParameter("attributeName");
                String attributeValue = req.getParameter("attributeValue");
                req.getSession(false).setAttribute(attributeName, attributeValue);
                resp.sendRedirect(req.getContextPath() + "/");
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.getSession(true);

                Enumeration<String> enumeration = req.getSession().getAttributeNames();
                while (enumeration.hasMoreElements()){
                        String key = enumeration.nextElement();
                        System.out.println(">>>>>>>>>>>>>>>>" + key + "::" + req.getSession().getAttribute(key));
                }
        }

        private static final long serialVersionUID = 2878267318695777395L;
}