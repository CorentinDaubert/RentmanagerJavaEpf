package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/create")
public class ResaCreateServlet extends HttpServlet {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Vehicle> listV = new ArrayList<>();
        try {
            listV = vehicleService.findAll();
        } catch (ServiceException e) {
        }
        request.setAttribute("vehicles", listV);

        List<Client> listC = new ArrayList<>();
        try {
            listC = clientService.findAll();
        } catch (ServiceException e) {
        }
        request.setAttribute("clients", listC);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int client_id;
        int vehicle_id;
        LocalDate start;
        LocalDate end;

        client_id = Integer.valueOf(request.getParameter("client"));
        vehicle_id = Integer.valueOf(request.getParameter("vehicle"));
        start = LocalDate.parse(request.getParameter("begin"));
        end = LocalDate.parse(request.getParameter("end"));

        Reservation reservation = new Reservation(client_id, vehicle_id, start, end);
        try {
            reservationService.create(reservation);
        } catch (ServiceException e) {
            // TODO: handle exception
        }
        response.sendRedirect("/rentmanager/rents");

    }


}
