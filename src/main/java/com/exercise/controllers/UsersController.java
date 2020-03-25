package com.exercise.controllers;

import com.exercise.models.User;
import com.exercise.utils.Hash;
import com.exercise.utils.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class UsersController extends HttpServlet {
    String appName = "My App";
    HttpSession session;
    public static List<User> users = new ArrayList<User>();

    @Override
    public void init() throws ServletException {
        this.appName = getInitParameter("appName");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URL.extractPageName(req.getRequestURL().toString());
        req.setAttribute("pageName", url);

        if(this.session != null) {
            req.setAttribute("username", req.getSession().getAttribute("usrName"));
        }

        switch(url) {
            case "account": {
                req.getRequestDispatcher(this.session != null ? "/WEB-INF/users/profile.jsp" : "/WEB-INF/users/account.jsp").forward(req, resp);
                break;
            }
            case "users": {
                req.setAttribute("users", users);
                req.getRequestDispatcher("/WEB-INF/users/users.jsp").forward(req, resp);
                break;
            }
            case "logout": {
                if(this.session != null) {
                    this.session.invalidate();
                    req.getRequestDispatcher("/WEB-INF/users/account.jsp").forward(req, resp);
                } else {
                    throw new ServletException("Invalid request. Error Code: 3941");
                }
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = URL.extractPageName(((HttpServletRequest) req).getRequestURL().toString());
        req.setAttribute("pageName", url);

        String registerForm = req.getParameter("register");
        String loginForm = req.getParameter("login");

        if(registerForm != null) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            if(username.isEmpty()) {
                throw new ServletException("Username should be entered");
            } else if(email.isEmpty()) {
                throw new ServletException("Email should be entered");
            } else if(password.isEmpty()) {
                throw new ServletException("Password should be entered");
            } else if(confirmPassword.isEmpty()) {
                throw new ServletException("Confirm password should be entered");
            } else if(!password.equals(confirmPassword)) {
                throw new ServletException("Passwords do not match");
            } else {
                try {
                    users.add(new User(username, email, Hash.createHash(password)));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    e.printStackTrace();
                }

                req.setAttribute("successMsg", "You have registered successfully. You can now log in!");
                req.getRequestDispatcher("/WEB-INF/users/account.jsp").forward(req, resp);
            }
        } else if(loginForm != null) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(username.isEmpty()) {
                throw new ServletException("Username should be entered");
            } else if(password.isEmpty()) {
                throw new ServletException("Password should be entered");
            }

            if(!users.isEmpty()) {
                for(User user : users) {
                    try {
                        if(user.getName().equals(username) && Hash.validatePassword(password, user.getPassword())) {
                            this.session = req.getSession();
                            this.session.setAttribute("usrName", username);

                            resp.sendRedirect(req.getContextPath());
                        } else {
                            throw new ServletException("Incorrect login credentials");
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                throw new ServletException("There was an internal error. Code: 5403");
            }
        } else {
            throw new ServletException("Invalid POST request");
        }
    }
}
