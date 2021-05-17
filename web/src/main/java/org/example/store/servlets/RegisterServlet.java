package org.example.store.servlets;

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
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, String> messages = ServletUtils.validateEmailAndPassword(email, password);

        if(!ServletUtils.emailValidator(email)){
            messages.put("email", Messages.EMAIL_INVALID);
        }

        if (messages.isEmpty() && !userDAO.findByEmail(email).isPresent()) {
            User newUser = User.newBuilder()
                    .setEmail(email)
                    .setPassword(password)
                    .build();
            userDAO.create(newUser);
            ServletUtils.createSession(request, response, newUser);
            return;
        }
        ServletUtils.showMessage(request, response, messages);
    }
}
