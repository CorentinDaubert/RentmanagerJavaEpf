package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/home")

public class HomeServlet extends HttpServlet {

	@Autowired
	private ClientService clientService;
	private static final long serialVersionUID = 1L;
	 @Autowired
	    VehicleService vehicleService; 
	 @Autowired
	 ReservationService reservationService;


	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
			// TODO
		try {
			request.setAttribute("countUsers", clientService.count());
			request.setAttribute("countCars", vehicleService.count());
			request.setAttribute("countResa", reservationService.count());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
	        dispatcher.forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
}

