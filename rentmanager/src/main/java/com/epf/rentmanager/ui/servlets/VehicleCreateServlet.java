package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
@WebServlet("/cars/create")

public class VehicleCreateServlet extends HttpServlet {
	@Autowired
    private VehicleService vehicleService;
	//ClientService clientService = ClientService.getInstance();
	private static final long serialVersionUID = 1L;
	  @Override
	    public void init() throws ServletException {
	        super.init();
	        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	    }
	
	@Override
	protected void doGet (HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
		
	}
	protected void doPost (HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		String constructor;
        String model;
        int numPlace;

        constructor = request.getParameter("manufacturer");
        model = request.getParameter("modele");
        numPlace = Integer.valueOf(request.getParameter("seats"));

        Vehicle vehicle= new Vehicle(constructor, model, numPlace);
        try {
        	vehicleService.create(vehicle);
            response.sendRedirect("/rentmanager/cars");
        } catch (ServiceException e) {
        	e.printStackTrace();
        	this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
        }
        



		
	}

}
