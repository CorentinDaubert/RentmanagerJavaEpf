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
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users")
public class UserServelet extends HttpServlet {
	
   
	@Autowired
    ClientService clientService;

    
    /** 
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


		

	@Override
	protected void doGet (HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException {
			try {
				request.setAttribute("listUsers", clientService.findAll());
				System.out.println( clientService.findAll());
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
