package org.java.hibernateProject;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.ForeignGenerator;
import org.java.hibernate.dto.Address;
import org.java.hibernate.dto.FourWheeler;
import org.java.hibernate.dto.TwoWheeler;
import org.java.hibernate.dto.UserDetails;
import org.java.hibernate.dto.Vehicle;
import org.java.hibernate.dto.VehicleSuper;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */

/* Inheritance */
public class HibernateTest 
{
	public static void main( String[] args )
	{
		UserDetails user1 = new UserDetails();
		UserDetails user2 = new UserDetails();
		
		VehicleSuper vehicle1 = new VehicleSuper();
		vehicle1.setVehicleName("Car");
		
		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("Bike Apache");
		bike.setSteeringHandle("Bike Steering handle");
		
		FourWheeler car1 = new FourWheeler();
		car1.setVehicleName("Swift");
		car1.setSteeringWheel("Swift Steering Wheel");
		
		FourWheeler car2 = new FourWheeler();
		car2.setVehicleName("BREZZA");
		car2.setSteeringWheel("BREZZA Steering Wheel");
		
		TwoWheeler bike2 = new TwoWheeler();
		bike2.setVehicleName("KTM Apache");
		bike2.setSteeringHandle("KTM Steering handle");
		
		
		
		
		/* Configuration().configure will load the configuration from default hibernate.cfg.xml
		 * buildSessionFactory() will return a session object which could be used to create new session
		 * Session Factory is only created once*/
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		/*Open a new session from sessionFactory*/
		Session session = sessionFactory.openSession();
		try{        
			session.beginTransaction();
			session.save(vehicle1);
			session.save(car2);
			session.save(bike);
			session.save(car1);
			session.save(bike2);
			session.getTransaction().commit(); 
		}
		catch(Exception e){
			System.out.println("Error in Quering and exception is"+e.getMessage());
		}
		finally{
			System.out.println("Closing Session");
			session.close();
		}
		
	/*	session = sessionFactory.openSession();
		session.beginTransaction();
		session.close();*/
	}
}



/* cascade example 
public class HibernateTest 
{
	public static void main( String[] args )
	{
		UserDetails user = new UserDetails();
		UserDetails user2 = new UserDetails();
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle();
		Vehicle vehicle4 = new Vehicle();
		Vehicle vehicle5 = new Vehicle();
		
		vehicle1.setVehicleType("Car");
		vehicle1.setVehicleName("Swift");
		vehicle2.setVehicleType("Car");
		vehicle2.setVehicleName("i20");
		vehicle3.setVehicleType("Jeep");
		vehicle3.setVehicleName("Thar");
		vehicle4.setVehicleType("SUV");
		vehicle4.setVehicleName("T500");
		vehicle5.setVehicleType("Car");
		vehicle5.setVehicleName("Skoda");
		
		Address address1 =new Address();
		address1.setStreet("Peterson Road");
		address1.setCity("Belgaum");
		address1.setState("Karnataka");
		address1.setPincode("590001");
		
		Address address2 =new Address();
		address2.setStreet("Petersberger strasse");
		address2.setCity("Fulda");
		address2.setState("Hessen");
		address2.setPincode("36037");
		
		
		//user.setUserId(1);
		user.setUserName("first name");
		user.setHomeAddress(address1);
		user.setOfficeAddress(address2);
		user.setDescription("Description of first user");
		user.setJoinedDate(new Date());
	
		user2.setUserName("Second name");
		user2.setHomeAddress(address2);
		user2.setDescription("Description of Second user");
		user2.setJoinedDate(new Date());
		
		
		user.setUserName("Veeragh Goudar");
		user.getListOfAddresses().add(address1);
		user.getListOfAddresses().add(address2);
		user.setDescription("Description of first user");
		user.setJoinedDate(new Date());
	//	user.setPrimaryVehicle(vehicle1);
		user.getManyVehicle().add(vehicle1);
		user.getManyVehicle().add(vehicle3);
		user.getManyVehicle().add(vehicle4);
		user.getManyVehicle().add(vehicle5);
		
	
		user2.setUserName("Second name i 20");
		user2.getListOfAddresses().add(address2);
		user2.setDescription("Description of Second user");
		user2.setJoinedDate(new Date());
//		user2.setPrimaryVehicle(vehicle2);
	//	user2.getManyVehicle().add(vehicle1);
		user2.getManyVehicle().add(vehicle2);
	//	user2.getManyVehicle().add(vehicle5);
		
		 Configuration().configure will load the configuration from default hibernate.cfg.xml
		 * buildSessionFactory() will return a session object which could be used to create new session
		 * Session Factory is only created once
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Open a new session from sessionFactory
		Session session = sessionFactory.openSession();
		try{        
			session.beginTransaction();
			session.save(vehicle1);
			session.save(vehicle2);
			session.save(vehicle3);
			session.save(vehicle4);
			session.save(vehicle5);
			session.persist(user2);
			session.persist(user);
			

			session.getTransaction().commit(); 
		}
		catch(Exception e){
			System.out.println("Error in Quering and exception is"+e.getMessage());
		}
		finally{
			System.out.println("Closing Session");
			session.close();
		}
		
		user=null;
		vehicle1=null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails)session.get(UserDetails.class, 6);
		vehicle1 = (Vehicle)session.get(Vehicle.class, 3);
		System.out.println("No of addresses for user "+user.getUserName()+" : "+user.getListOfAddresses().size());
		Gson gson = new Gson();
		String jsonInString = gson.toJson(user);
		System.out.println("User Object :"+jsonInString);
		System.out.println("vehicle1 Details : "+gson.toJson(vehicle1));
		session.close();
	}
}
*/
/*public class HibernateTest 
{
	public static void main( String[] args )
	{
		UserDetails user = new UserDetails();
		UserDetails user2 = new UserDetails();
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();
		Vehicle vehicle3 = new Vehicle();
		Vehicle vehicle4 = new Vehicle();
		Vehicle vehicle5 = new Vehicle();
		
		vehicle1.setVehicleType("Car");
		vehicle1.setVehicleName("Swift");
		vehicle1.getOwnersOfVehicle().add(user);
		vehicle2.setVehicleType("Car");
		vehicle2.setVehicleName("i20");
		vehicle2.getOwnersOfVehicle().add(user2);
		vehicle3.setVehicleType("Jeep");
		vehicle3.setVehicleName("Thar");
		vehicle3.getOwnersOfVehicle().add(user);
		vehicle4.setVehicleType("SUV");
		vehicle4.setVehicleName("T500");
		vehicle4.getOwnersOfVehicle().add(user);
		vehicle5.setVehicleType("Car");
		vehicle5.setVehicleName("Skoda");
		vehicle5.getOwnersOfVehicle().add(user);
		
		Address address1 =new Address();
		address1.setStreet("Peterson Road");
		address1.setCity("Belgaum");
		address1.setState("Karnataka");
		address1.setPincode("590001");
		
		Address address2 =new Address();
		address2.setStreet("Petersberger strasse");
		address2.setCity("Fulda");
		address2.setState("Hessen");
		address2.setPincode("36037");
		
		
		//user.setUserId(1);
		user.setUserName("first name");
		user.setHomeAddress(address1);
		user.setOfficeAddress(address2);
		user.setDescription("Description of first user");
		user.setJoinedDate(new Date());
	
		user2.setUserName("Second name");
		user2.setHomeAddress(address2);
		user2.setDescription("Description of Second user");
		user2.setJoinedDate(new Date());
		
		
		user.setUserName("Veeragh Goudar");
		user.getListOfAddresses().add(address1);
		user.getListOfAddresses().add(address2);
		user.setDescription("Description of first user");
		user.setJoinedDate(new Date());
	//	user.setPrimaryVehicle(vehicle1);
		user.getManyVehicle().add(vehicle1);
		user.getManyVehicle().add(vehicle3);
		user.getManyVehicle().add(vehicle4);
		user.getManyVehicle().add(vehicle5);
		
	
		user2.setUserName("Second name i 20");
		user2.getListOfAddresses().add(address2);
		user2.setDescription("Description of Second user");
		user2.setJoinedDate(new Date());
//		user2.setPrimaryVehicle(vehicle2);
		user2.getManyVehicle().add(vehicle1);
		user2.getManyVehicle().add(vehicle2);
		user2.getManyVehicle().add(vehicle5);
		
		 Configuration().configure will load the configuration from default hibernate.cfg.xml
		 * buildSessionFactory() will return a session object which could be used to create new session
		 * Session Factory is only created once
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Open a new session from sessionFactory
		Session session = sessionFactory.openSession();
		try{        
			session.beginTransaction();
			session.save(user);
			session.save(user2);
			session.save(vehicle1);
			session.save(vehicle2);
			session.save(vehicle3);
			session.save(vehicle4);
			session.save(vehicle5);
			session.getTransaction().commit(); 
		}
		catch(Exception e){
			System.out.println("Error in Quering and exception is"+e.getMessage());
		}
		finally{
			System.out.println("Closing Session");
			session.close();
		}
		
		user=null;
		vehicle1=null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails)session.get(UserDetails.class, 1);
		vehicle1 = (Vehicle)session.get(Vehicle.class, 3);
		System.out.println("No of addresses for user "+user.getUserName()+" : "+user.getListOfAddresses().size());
		Gson gson = new Gson();
		String jsonInString = gson.toJson(user);
		System.out.println("User Object :"+jsonInString);
		System.out.println("vehicle1 Details : "+gson.toJson(vehicle1));
		session.close();
	}
}
*/