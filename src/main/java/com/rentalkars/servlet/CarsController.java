package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.entity.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CarsController", value = "/CarsController")
public class CarsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command == null) {
            command = "LIST";
        }

        switch (command){

            case "LIST":
                listCars(request, response);
                break;

            case "ADD":
                addCar(request, response);
                break;

            default:
                listCars(request, response);
                break;

        }
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDao cDao = new CarDao();
        List<Car> carList = cDao.getCars();

        request.setAttribute("carsList", carList);

        RequestDispatcher rd = request.getRequestDispatcher("/sections/cars_list.jsp");
        rd.forward(request, response);
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String numPlate = request.getParameter("numPlate");
        LocalDate regDate = LocalDate.parse(request.getParameter("regDate"));

        CarDao cDao = new CarDao();
        Car car = new Car(manufacturer, model, type, numPlate, regDate);
        cDao.saveCar(car);

        listCars(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
