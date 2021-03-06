package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.CarDao;
import com.rentalkars.hibernate.dao.RentDao;
import com.rentalkars.hibernate.entity.Car;
import com.rentalkars.hibernate.entity.Rent;
import com.rentalkars.hibernate.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RentServlet", value = "/RentServlet")
public class RentServlet extends HttpServlet {

    private final RentDao rDao = new RentDao();
    private RequestDispatcher rd;

    private String errorMsg = null;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");
        if(command == null) {
            command = "LIST";
        }

        switch (command){
            case "LIST":
                listRents(request, response);
                break;

            case "CHECKorUPDATE":
                if(request.getParameter("rentId").equals("")){
                    checkAvailable(request, response);
                } else {
                    updateReservation(request, response,"In Attesa");
                }
                break;

            case "APPROVE":
                approveReservation(request, response,"Approvata");
                break;

            case "RESERVE":
                addReservation(request, response);
                break;

            case "LOAD":
                loadReservation(request, response);
                break;

            case "DELETE":
                deleteReservation(request, response);
                break;

            default:
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                break;
        }

    }

    private void listRents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Rent> rentList = new ArrayList<>();
        if(!request.getSession().getAttribute("loggedUser").equals("")) {
            User user = (User) request.getSession().getAttribute("loggedUser");
            if (user.isAdmin()) {
                rentList = rDao.getRents();
            } else {
                rentList = rDao.listUserReservation(user);
            }
        }
        request.setAttribute("rentsList", rentList);

        rd = request.getRequestDispatcher("/sections/reservations_list.jsp");
        rd.forward(request, response);

    }


    private void checkAvailable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        Rent rent = new Rent(startDate, endDate);

        if(endDate.isBefore(startDate) || startDate.isBefore(LocalDate.now())) {

            inputErrors("La data finale inserita non ?? valida. Riprova", request, response);

        } else {

            List<Car> cars = rDao.availableCars(startDate, endDate);
            request.setAttribute("cars", cars);
            request.setAttribute("rentUpdate", rent);
            rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
            rd.forward(request, response);


        }

    }


    public void addReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        Long carId = Long.valueOf(request.getParameter("carId"));

        CarDao cDao = new CarDao();
        Car car = cDao.selById(carId);

        User user = (User) request.getSession().getAttribute("loggedUser");

        Rent rent = new Rent(startDate, endDate, car, user, "In Attesa");
        rDao.saveRent(rent);
        listRents(request, response);

    }

    private void approveReservation(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException {

        Long rentId = Long.valueOf(request.getParameter("rentId"));
        Rent rent = rDao.selById(rentId);
        LocalDate startDate = rent.getStartDate();
        LocalDate endDate = rent.getEndDate();

        if(checkTime(LocalDate.now().until(startDate))) {

            request.setAttribute("rentUpdate", rent);
            inputErrors("Non ?? possibile modificare la prenotazione. La data ?? troppo vicina", request, response);

        } else {

            rent.setStartDate(startDate);
            rent.setEndDate(endDate);
            rent.setStatus(status);
            rDao.updateReservation(rent);
            listRents(request, response);

        }

    }


    private void updateReservation(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException {

        Long rentId = Long.valueOf(request.getParameter("rentId"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        Rent rent = rDao.selById(rentId);

        if(rent != null){
            if(endDate.isBefore(startDate) || startDate.isBefore(LocalDate.now())) {

                inputErrors("La data finale inserita non ?? valida. Riprova", request, response);

            } else if (checkTime(LocalDate.now().until(startDate))) {

                request.setAttribute("rentUpdate", rent);
                inputErrors("Non ?? possibile modificare la prenotazione. La data ?? troppo vicina", request, response);

            } else {

                rent.setStartDate(startDate);
                rent.setEndDate(endDate);
                rent.setStatus(status);
                rDao.updateReservation(rent);
                listRents(request, response);

            }
        } else {
            checkAvailable(request, response);
        }
    }


    public boolean checkTime(Period timeDiff) {
        return (timeDiff.getYears() == 0 && timeDiff.getMonths() == 0 && timeDiff.getDays() < 2);
    }


    private void loadReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long rentId = Long.valueOf(request.getParameter("rentId"));

        Rent rent = rDao.selById(rentId);

        if(checkTime(LocalDate.now().until(rent.getStartDate()))) {

            errorMsg = "Non ?? possibile modificare la prenotazione. La data ?? troppo vicina";
            request.setAttribute("errorMsg", errorMsg);

        } else {
            request.setAttribute("rentUpdate", rent);
            rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
            rd.forward(request, response);
        }

        listRents(request, response);

    }


    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long rentId = Long.valueOf(request.getParameter("rentId"));
        Rent rent = rDao.selById(rentId);

        if(checkTime(LocalDate.now().until(rent.getStartDate()))) {

            errorMsg = "Non ?? possibile cancellare la prenotazione. La data ?? troppo vicina";
            request.setAttribute("errorMsg", errorMsg);

        } else {
            rDao.removeReservation(rent);
        }

        listRents(request, response);

    }


    public void inputErrors(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        errorMsg = error;
        request.setAttribute("errorMsg", errorMsg);
        rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
        rd.forward(request, response);

    }

}
