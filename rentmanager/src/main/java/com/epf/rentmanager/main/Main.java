package com.epf.rentmanager.main;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			System.out.println(ClientService.getInstance().findById(1));
			System.out.println(ClientService.getInstance().findAll());
			Client client = new Client ("bAA","bbb","aaa@bbb.fr",LocalDate.now());
			ClientService.getInstance().create(client);
			System.out.println(ClientService.getInstance().findAll());
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ApplicationContext context = new
				AnnotationConfigApplicationContext(AppConfiguration.class);
				ClientService clientService = context.getBean(ClientService.class);

	}

}
