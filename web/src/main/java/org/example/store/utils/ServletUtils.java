package org.example.store.utils;

import org.apache.log4j.Logger;
import org.example.store.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletUtils extends HttpServlet {
    static Logger log = Logger.getLogger(ServletUtils.class.getName());

    public static void showMessage(HttpServletRequest request, HttpServletResponse response, Map<String, String> messages) {
        request.setAttribute("messages", messages);
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Exception while trying to show message",e);
        }
    }
    public static void createSession(HttpServletRequest request, HttpServletResponse response, User model) {
        try {
            String path = request.getContextPath();
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", model.getEmail());
            session.setAttribute("userRole", model.getRole());
            response.sendRedirect(path + "/profile");
        } catch (IOException e) {
            log.error("Exception while trying to create session",e);
        }
    }

    public static Map<String, String> validateEmailAndPassword(String email, String password) {
        Map<String, String> messages = new HashMap<>();

        if (email == null || email.isEmpty()) {
            messages.put("email", Messages.EMAIL_INVALID);
        }
        if (password == null || password.isEmpty()) {
            messages.put("password", Messages.PASSWORD_INVALID);
        }
        return messages;
    }
    public static boolean emailValidator(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
