package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.dao.UserDao;
import com.rentalkars.hibernate.entity.Car;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command == null) {
            command = "LIST";
        }

        switch (command){

            case "LIST":
                listUsers(request, response);
                break;

            case "ADD":
                addUser(request, response);
                break;

            default:
                listUsers(request, response);
                break;

        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao uDao = new UserDao();
        List<User> userList = uDao.getUsers();

        request.setAttribute("usersList", userList);

        RequestDispatcher rd = request.getRequestDispatcher("/admin/users_list.jsp");
        rd.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        UserDao uDao = new UserDao();
        User user = new User(email, password, firstName, lastName, birthday, false);
        uDao.saveUser(user);

        listUsers(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
