package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
	@Autowired
    private ClientService clientService;
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
		
	}
	protected void doPost (HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		String name;
        String lastName;
        String email;
        LocalDate birthDate;

        name = request.getParameter("last_name");
        lastName = request.getParameter("first_name");
        email = request.getParameter("email");
        birthDate = LocalDate.parse(request.getParameter("birthdate"));

        Client client = new Client(name, lastName, email, birthDate);
        try {
            clientService.create(client);
            response.sendRedirect("/rentmanager/users");
        } catch (ServiceException e) {
        	e.printStackTrace();
        	this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        }
        



		
	}

}
