package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.UserDao;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "UserRegister", value = "/UserRegister")
public class UserRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));


        UserDao userDao = new UserDao();
        User user = new User(email, password, firstName, lastName, birthday, false);
        userDao.saveUser(user);

        //Ottenere il request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        //Girare la request al file jsp
        dispatcher.forward(request, response);
    }
}
