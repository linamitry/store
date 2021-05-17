package org.example.store.servlets;

import org.example.store.dao.UserDAO;
import org.example.store.utils.Messages;
import org.example.store.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, String> messages = ServletUtils.validateEmailAndPassword(email, password);

        if (!messages.isEmpty()) {
            ServletUtils.showMessage(request, response, messages);
            return;
        }
        userDAO.findByEmailAndPassword(email, password)
                .ifPresentOrElse((user) -> ServletUtils.createSession(request, response, user),
                        () -> {
                            messages.put("login", Messages.USER_NOT_FOUND);
                            ServletUtils.showMessage(request, response, messages);
                        });
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}


