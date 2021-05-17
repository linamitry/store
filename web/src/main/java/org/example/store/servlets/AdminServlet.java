package org.example.store.servlets;

import org.apache.log4j.Logger;
import org.example.store.dao.UserDAO;
import org.example.store.model.User;
import org.example.store.utils.Messages;
import org.example.store.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//todo log
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    static Logger log = Logger.getLogger(AdminServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            insertUser(request, response);
        } catch (SQLException e) {
            log.error("Exception while trying to get insert user", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            listUser(request, response);
        } catch (SQLException e) {
            log.error("Exception while trying to get users", e);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.readAll();
        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    //todo поправить роль
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, String> messages = ServletUtils.validateEmailAndPassword(email, password);
        if(!ServletUtils.emailValidator(email)){
            messages.put("email", Messages.EMAIL_INVALID);
        }
        User newUser = User.newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build();
        userDAO.create(newUser);
        response.sendRedirect("list");
    }
}
