package org.example.store.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getContextPath();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        System.out.println("User=" + session.getAttribute("userEmail"));
        session.invalidate();
        response.sendRedirect(path + "/login");
    }
}
