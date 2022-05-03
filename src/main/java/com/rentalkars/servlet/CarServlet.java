package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.entity.Car;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CarServlet", value = "/CarServlet")
public class CarServlet extends HttpServlet {

    private final CarDao cDao = new CarDao();
    private RequestDispatcher rd;

    private String errorMsg;


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

       String manufacturer = request.getParameter("manufacturer");
       String model = request.getParameter("model");
       String type = request.getParameter("type");
       String numPlate = request.getParameter("numPlate");
       LocalDate regDate = LocalDate.parse(request.getParameter("regDate"));

        if(LocalDate.now().isBefore(regDate)) {

            inputErrors("Questa macchina non è ancora stata registrata. Riprova", request, response);

        } else if (numPlate.equals(cDao.selByPlate(numPlate))) {

            inputErrors("Questa email è già utilizzata", request, response);

        } else {

            Car car = new Car(manufacturer, model, type, numPlate, regDate);

            cDao.saveCar(car);

        }

        listCars(request, response);

    }
    

    private void loadCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long carId = Long.valueOf(request.getParameter("carId"));

        Car car = cDao.selById(carId);

        request.setAttribute("carUpdate", car);
        rd = request.getRequestDispatcher("/admin/manage_cars.jsp");
        rd.forward(request, response);

    }
    

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long carId = Long.valueOf(request.getParameter("carId"));

        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String numPlate = request.getParameter("numPlate");
        LocalDate regDate = LocalDate.parse(request.getParameter("regDate"));

        Car car = cDao.selById(carId);

        if(LocalDate.now().isBefore(regDate)) {

            request.setAttribute("carUpdate", car);
            inputErrors("Questa macchina non è ancora stata registrata. Riprova", request, response);

        }  else if (numPlate.equals(cDao.selByPlate(numPlate))) {

            inputErrors("Questa email è già utilizzata", request, response);

        } else {

            car.setManufacturer(manufacturer);
            car.setModel(model);
            car.setType(type);
            car.setNumPlate(numPlate);
            car.setRegDate(regDate);

            cDao.updateCar(car);

        }

        listCars(request, response);

    }
    

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {

        Long carId = Long.valueOf(request.getParameter("carId"));

        Car car = cDao.selById(carId);

        cDao.removeCar(car);

        listCars(request, response);

    }

    public void inputErrors(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        errorMsg = error;
        request.setAttribute("errorMsg", errorMsg);
        rd = request.getRequestDispatcher("/admin/manage_cars.jsp");
        rd.forward(request, response);
    }

}