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
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/rents/delete")
public class ResaDeleteServlet extends HttpServlet {
	@Autowired
	 ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
        id = Integer.valueOf(request.getQueryString().substring(3));	
		try {
			reservationService.delete(id);
			response.sendRedirect("/rentmanager/rents");
            
		} catch (ServiceException e) {
			e.printStackTrace();			
		}
		

	}
}
