package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.entity.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "Cars", value = "/Cars")
public class Cars extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String numPlate = request.getParameter("numPlate");
        LocalDate regDate = LocalDate.parse(request.getParameter("regDate"));

        CarDao cDao = new CarDao();
        Car car = new Car(manufacturer, model, type, numPlate, regDate);
        cDao.saveCar(car);

        RequestDispatcher rd = request.getRequestDispatcher("CarsLister");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
