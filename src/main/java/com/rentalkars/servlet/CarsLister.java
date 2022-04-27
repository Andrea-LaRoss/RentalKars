package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.entity.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CarsLister", value = "/CarsLister")
public class CarsLister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listCars(request, response);
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDao cDao = new CarDao();
        List<Car> carList = cDao.getCars();

        request.setAttribute("carsList", carList);

        RequestDispatcher rd = request.getRequestDispatcher("/sections/cars_list.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
