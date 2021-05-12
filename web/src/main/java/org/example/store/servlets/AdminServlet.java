package org.example.store.servlets;

import org.example.store.dao.UserDAO;
import org.example.store.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            insertUser(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();


        System.out.println(servletPath);

        try {
            listUser(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.readAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    //    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        dispatcher.forward(request, response);
//    }
//
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        String role = request.getParameter("role");
        User newUser = new User(email, password);
        userDAO.create(newUser);
        response.sendRedirect("list");
    }
//
//    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        userDAO.delete(id);
//        response.sendRedirect("list");
//    }
//
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        User existingUser = userDAO.readById(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//    }
//
//    private void updateUser(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String role = request.getParameter("role");
//
//        User updtUser = new User(id,name,email,role);
//        userDAO.update(updtUser);
//        response.sendRedirect("list");
//    }
}
