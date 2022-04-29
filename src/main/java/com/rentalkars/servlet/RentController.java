package com.rentalkars.servlet;

import com.rentalkars.hibernate.dao.RentDao;
import com.rentalkars.hibernate.entity.Rent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@WebServlet(name = "RentController", value = "/RentController")
public class RentController extends HttpServlet {

    private final RentDao rDao = new RentDao();
    private Rent rent = null;
    private RequestDispatcher rd;

    private Long rentId;
    private LocalDate startDate;
    private LocalDate endDate;

    private String errorMsg;


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

        checkBefore(endDate, startDate, request, response);
        checkBefore(startDate, LocalDate.now(), request, response);
    }

    private void checkBefore (LocalDate d1, LocalDate d2, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(d1.isBefore(d2)) {
            errorMsg = "La data finale inserita non è valida. Riprova";
            request.setAttribute("errorMsg", errorMsg);
            rd = request.getRequestDispatcher("/user/manage_reservations.jsp");
            rd.forward(request, response);
        }
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rentId = Long.valueOf(request.getParameter("rentId"));
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
            rDao.updateReservation(rent);
            rd = request.getRequestDispatcher("/sections/reservations_list.jsp");
            rd.forward(request, response);
    }


    private void loadReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rentId = Long.valueOf(request.getParameter("rentId"));

        rent = rDao.selById(rentId);

        request.setAttribute("rentUpdate", rent);
        rd = request.getRequestDispatcher("/admin/manage_reservations.jsp");
        rd.forward(request, response);
    }


    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rentId = Long.valueOf(request.getParameter("rentId"));

        rent = rDao.selById(rentId);

        rDao.removeReservation(rent);

        listRents(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
