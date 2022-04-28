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
import java.util.List;

@WebServlet(name = "RentController", value = "/RentController")
public class RentController extends HttpServlet {

    private RentDao rDao = new RentDao();
    private Rent rent = null;
    private RequestDispatcher rd;

    private LocalDate startDate;
    private LocalDate endDate;

    private String errorMsg;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        startDate = LocalDate.parse(request.getParameter("startDate"));
        endDate = LocalDate.parse(request.getParameter("endDate"));

        if(endDate.isBefore(startDate)) {
            errorMsg = "La data finale inserita non è valida. Riprova";
            rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
            rd.forward(request, response);
        }

        String command = request.getParameter("command");
        if(command == null) {
            command = "LIST";
        }

        switch (command){

            case "LIST":
                listRents(request, response);
                break;

            case "CHECKorUPDATE":
                if(request.getParameter("carId") == ""){
                    checkAvailable(request, response);
                } else {
                    updateReservation(request, response);
                }
                break;

            case "LOAD":
                loadReservation(request, response);
                break;

            case "DELETE":
                deleteReservation(request, response);
                break;

            default:
                listRents(request, response);
                break;

        }
    }

    private void listRents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Rent> rentList = rDao.getRents();

        request.setAttribute("rentsList", rentList);

        rd = request.getRequestDispatcher("/sections/reservations_list.jsp");
        rd.forward(request, response);

    }


    private void checkAvailable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        startDate = LocalDate.parse(request.getParameter("startDate"));
        endDate = LocalDate.parse(request.getParameter("endDate"));


    }


    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long rentId = Long.valueOf(request.getParameter("rentId"));
        startDate = LocalDate.parse(request.getParameter("startDate"));
        endDate = LocalDate.parse(request.getParameter("endDate"));

        rent = rDao.selById(rentId);

        Period period = Period.between(startDate, rent.getStartDate());
        if(period.getYears() == 0 && period.getMonths() == 0 && period.getDays() <= 2) {

            errorMsg = "Non è possibile modificare la prenotazione. La data è troppo vicina";
            request.setAttribute("errorMsg", errorMsg);

            rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
            rd.forward(request, response);
        }


            rd = request.getRequestDispatcher("/sections/reservations_list.jsp");
            rd.forward(request, response);

    }


    private void loadReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
