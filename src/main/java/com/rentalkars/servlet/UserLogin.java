package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.UserDao;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List  reg = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        UserDao userDao = new UserDao();
        User user = userDao.selEmailPassword(email, password);

        if(user == null){

            out.println("Credenziali errate. Riprova");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login.jsp");

        }

        if(user.isAdmin()){

            reg = userDao.getUsers();

        } else {

            reg = null;

        }

        request.setAttribute("reg", reg);

        //Ottenere il request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sections/homepage.jsp");
        //Girare la request al file jsp
        dispatcher.forward(request, response);
    }
}
