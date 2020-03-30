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
            req.setAttribute("user", req.getSession().getAttribute("user"));
        }

        switch(url) {
            case "edit": {
                if(this.session != null) {
                    req.getRequestDispatcher("/WEB-INF/users/account/edit.jsp").forward(req, resp);
                } else {
                    throw new ServletException("Invalid request. Error Code: 3941");
                }

                break;
            }
            case "account": {
                req.getRequestDispatcher(this.session != null ? "/WEB-INF/users/profile.jsp" : "/WEB-INF/users/account/account.jsp").forward(req, resp);
                break;
            }

            case "users": {
                req.setAttribute("users", users);
                req.getRequestDispatcher("/WEB-INF/users/users.jsp").forward(req, resp);
                break;
            }

            case "user": {

                if(req.getParameterMap().containsKey("username")) {
                    String username = req.getParameter("username");

                    User foundUser = null;

                    for(User user : users) {
                        if(user.getName().equals(username)) {
                            foundUser = user;
                            break;
                        }
                    }

                    if(foundUser != null) {
                        if(this.session != null) {
                            User sessionUser = (User) this.session.getAttribute("user");

                            if(foundUser.getName().equals(sessionUser.getName()))
                                resp.sendRedirect("account");
                        } else {
                            req.setAttribute("user", foundUser);
                            req.getRequestDispatcher("/WEB-INF/users/userProfile.jsp").forward(req, resp);
                        }
                    } else {
                        throw new ServletException("The user is not found");
                    }
                } else {
                    throw new ServletException("Invalid request. Error Code: 3841");
                }

                break;
            }

            case "logout": {
                if(this.session != null) {
                    this.session.invalidate();
                    this.session = null;

                    resp.sendRedirect(req.getContextPath());
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

        if(req.getParameterMap().containsKey("register")) {
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
                    boolean isUserFound = true;

                    if(!users.isEmpty()) {
                        for(User user : users) {
                            if(!user.getName().equals(username) && !user.getEmail().equals(email)) {
                                isUserFound = false;
                            } else {
                                throw new ServletException("The user already exists");
                            }
                        }

                        if(!isUserFound) {
                            users.add(new User(username, email, Hash.createHash(password)));
                        }
                    } else {
                        users.add(new User(username, email, Hash.createHash(password)));
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    e.printStackTrace();
                }

                req.setAttribute("successMsg", "You have registered successfully. You can now log in!");
                req.getRequestDispatcher("/WEB-INF/users/account/account.jsp").forward(req, resp);
            }
        } else if(req.getParameterMap().containsKey("login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(username.isEmpty()) {
                throw new ServletException("Username should be entered");
            } else if(password.isEmpty()) {
                throw new ServletException("Password should be entered");
            }

            if(!users.isEmpty()) {
                User foundUser = null;

                for(User user : users) {
                    try {
                        if(user.getName().equals(username) && Hash.validatePassword(password, user.getPassword())) {
                            foundUser = user;
                            break;
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();
                    }
                }

                if(foundUser != null) {

                    this.session = req.getSession();
                    this.session.setAttribute("user", foundUser);

                    resp.sendRedirect(req.getContextPath());
                } else {
                    throw new ServletException("Incorrect login credentials");
                }
            } else {
                throw new ServletException("There was an internal error. Code: 5403");
            }
        } else if(req.getParameterMap().containsKey("save")) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");

            for(User user : users) {
                if(user.equals(this.session.getAttribute("user"))) {
                    user.setEmail(email);

                    this.session.setAttribute("user", user);

                    req.setAttribute("successMsg", "Changes saved successfully!");
                    req.getRequestDispatcher("/WEB-INF/users/account/edit.jsp").forward(req, resp);

                    break;
                }
            }
        } else {
            throw new ServletException("Invalid POST request");
        }
    }
}
