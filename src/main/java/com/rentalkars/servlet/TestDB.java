package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.UserDao;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TestDB", value = "/TestDB")
public class TestDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao studentDao = new UserDao();
        Date nascita1 = new Date (1, 1, 1990);
        User user = new User("test@admin.com", "1234", "TestNome", "TestCognome", nascita1, true);
        studentDao.saveUser(user);

        List< User > users = studentDao.getUsers();

        request.setAttribute("users_list", users);

        //Ottenere il request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("sections/homepage.jsp");
        //Girare la request al file jsp
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
