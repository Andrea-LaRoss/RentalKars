package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.UserDao;
import com.rentalkars.hibernate.entity.User;
import com.rentalkars.hibernate.utils.HibernateConfig;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TestDB", value = "/TestDB")
public class TestDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        LocalDate nascita = LocalDate.of(1990, 01, 01);
        User user = new User("test@admin.com", "1234", "TestNome", "TestCognome", nascita, true);
        userDao.saveUser(user);

        List < User > reg =  userDao.getUsers();

        request.setAttribute("reg", reg);

        //Ottenere il request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sections/homepage.jsp");
        //Girare la request al file jsp
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
