package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.RentDao;
import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RentController", value = "/RentController")
public class RentController extends HttpServlet {

    private RentDao rDao = new RentDao();
    private Rent rent = null;
    private RequestDispatcher rd;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listRents(request, response);
    }

    private void listRents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Rent> rentList = rDao.getRents();

        request.setAttribute("rentsList", rentList);

        rd = request.getRequestDispatcher("/sections/reservations_list.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
