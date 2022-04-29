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

    private UserDao uDao = new UserDao();
    private RequestDispatcher rd;


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

            case "ADDorUPDATE":
                if(request.getParameter("userId") == ""){
                    addUser(request, response);
                } else {
                    updateUser(request, response);
                }
                break;

            case "VALIDATE":
                validateUser(request, response);
                break;

            case "LOAD":
                loadUser(request, response);
                break;

            case "DELETE":
                deleteUser(request, response);
                break;

            case "SEARCH":
                searchUser(request, response);
                break;

            default:
                listUsers(request, response);
                break;

        }
    }


    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> userList = uDao.getUsers();

        request.setAttribute("usersList", userList);

        rd = request.getRequestDispatcher("/admin/users_list.jsp");
        rd.forward(request, response);

    }


    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        User user = new User(email, password, firstName, lastName, birthday, false);
        uDao.saveUser(user);

        listUsers(request, response);

    }


    private void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //User user = uDao.selEmailPassword(email, password);
       //request.setAttribute("loggedUser", user);

        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }


    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId = Long.valueOf(request.getParameter("userId"));

        User user = uDao.selById(userId);

        request.setAttribute("userUpdate", user);
        rd = request.getRequestDispatcher("/admin/manage_users.jsp");
        rd.forward(request, response);

    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId = Long.valueOf(request.getParameter("userId"));

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        User user = uDao.selById(userId);

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);

        uDao.updateUser(user);

        listUsers(request, response);

    }


    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));

        User user = uDao.selById(userId);

        uDao.removeUser(user);

        listUsers(request, response);

    }


    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("nameSearch");


        List<User> userList = uDao.getUsers(firstName);

        request.setAttribute("usersList", userList);

        rd = request.getRequestDispatcher("/admin/users_list.jsp");
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
