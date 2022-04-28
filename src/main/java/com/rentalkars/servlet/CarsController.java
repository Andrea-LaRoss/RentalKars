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

    private final CarDao cDao = new CarDao();
    private Car car;
    private RequestDispatcher rd;

    private Long carId;
    private String manufacturer;
    private String model;
    private String type;
    private String numPlate;
    private LocalDate regDate;
    

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

            case "ADDorUPDATE":
                if(request.getParameter("carId").equals("")){
                    addCar(request, response);
                } else {
                    updateCar(request, response);
                }
                break;

            case "LOAD":
                loadCar(request, response);
                break;

            case "DELETE":
                deleteCar(request, response);
                break;

            case "SEARCH":
                searchCar(request, response);
                break;

            default:
                listCars(request, response);
                break;

        }
    }
    

    private void listCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> carList = cDao.getCars();

        request.setAttribute("carsList", carList);

        RequestDispatcher rd = request.getRequestDispatcher("/sections/cars_list.jsp");
        rd.forward(request, response);
    }
    

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        manufacturer = request.getParameter("manufacturer");
        model = request.getParameter("model");
        type = request.getParameter("type");
        numPlate = request.getParameter("numPlate");
        regDate = LocalDate.parse(request.getParameter("regDate"));

        Car car = new Car(manufacturer, model, type, numPlate, regDate);
        cDao.saveCar(car);

        listCars(request, response);
    }
    

    private void loadCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        carId = Long.valueOf(request.getParameter("carId"));

        car = cDao.selById(carId);

        request.setAttribute("carUpdate", car);
        rd = request.getRequestDispatcher("/admin/manage_cars.jsp");
        rd.forward(request, response);

    }
    

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        carId = Long.valueOf(request.getParameter("carId"));

        manufacturer = request.getParameter("manufacturer");
        model = request.getParameter("model");
        type = request.getParameter("type");
        numPlate = request.getParameter("numPlate");
        regDate = LocalDate.parse(request.getParameter("regDate"));

        cDao.updateCar(manufacturer, model, type, numPlate, regDate, carId);

        listCars(request, response);

    }
    

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        carId = Long.valueOf(request.getParameter("carId"));

        cDao.deleteCar(carId);

        listCars(request, response);

    }
    

    private void searchCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        model = request.getParameter("nameSearch");


        List<Car> carList = cDao.getCars(model);

        request.setAttribute("carsList", carList);

        rd = request.getRequestDispatcher("/admin/cars_list.jsp");
        rd.forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
