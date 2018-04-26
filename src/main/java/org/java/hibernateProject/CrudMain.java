package org.java.hibernateProject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.java.hibernate.dto.UserDetailsNew;

public class CrudMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//crudOperations();
		//TransientPersistance();
		//DetachedToPersistance();
		//runHQLqueries();
		//namedQueries();
		//criteriaAPI();
		//projectionsAPI();
		//queryByExample();
		//cachingExample();		
		NoCahcingWith2Sessions();
	}

	private static void NoCahcingWith2Sessions() {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
			Session session1 = sessionFactory.openSession();
			session1.beginTransaction();
			UserDetailsNew user = (UserDetailsNew) session1.get(UserDetailsNew.class, 2);
			session1.close();
			
			Session session2 = sessionFactory.openSession();
			session2.beginTransaction();
			UserDetailsNew user2 = (UserDetailsNew) session2.get(UserDetailsNew.class, 2);
			session2.close();
			
			System.out.println(user.getUserName());
			System.out.println(user2.getUserName());
			
		
	}

	private static void cachingExample() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			UserDetailsNew user = (UserDetailsNew) session.get(UserDetailsNew.class, 2);
			user.setUserName("Veeragh");
			UserDetailsNew user2 = (UserDetailsNew) session.get(UserDetailsNew.class, 2);
			
			System.out.println(user.getUserName());
			System.out.println(user2.getUserName());
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
		
	}

	private static void queryByExample() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			UserDetailsNew exampleUser = new UserDetailsNew();
			//exampleUser.setUserId(18);
			exampleUser.setUserName("User 2%");
			Example example = Example.create(exampleUser).enableLike();
			Criteria criteriaObj = session.createCriteria(UserDetailsNew.class)
										.add(example);
			
			
			List<UserDetailsNew> users = (List<UserDetailsNew>) criteriaObj.list();
			session.getTransaction().commit();
			session.close();
			System.out.println("Total list of Users: "+users.size());
			for(int i=0;i<users.size();i++){
				System.out.println("User no:"+i+" with user_ID:"+users.get(i).getUserId()+" Name:"+users.get(i).getUserName());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
		
	}

	private static void projectionsAPI() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Criteria criteriaObj = session.createCriteria(UserDetailsNew.class)
									.setProjection(Projections.property("userName"));
			
			List<String> users = (List<String>) criteriaObj.list();
			session.getTransaction().commit();
			session.close();
			System.out.println("Total list of Users: "+users.size());
			for(int i=0;i<users.size();i++){
				System.out.println("Name:"+users.toString());
			}
			
			for(String employeeName: users){
		         String firstName = (String)employeeName;
		         System.out.println(firstName);
		     }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
		
	}

	private static void criteriaAPI() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Criteria criteriaObj = session.createCriteria(UserDetailsNew.class);
/*			criteriaObj.add(Restrictions.like("userName", "User %"))
						.add(Restrictions.gt("userId", 14));*/
/*			OR Condtion*/
			criteriaObj.add(Restrictions.or(Restrictions.between("userId", 5, 10), Restrictions.between("userId", 25, 30)));
			
			
			List<UserDetailsNew> users = (List<UserDetailsNew>) criteriaObj.list();
			session.getTransaction().commit();
			session.close();
			System.out.println("Total list of Users: "+users.size());
			for(int i=0;i<users.size();i++){
				System.out.println("User no:"+i+" with user_ID:"+users.get(i).getUserId()+" Name:"+users.get(i).getUserName());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
	}

	private static void namedQueries() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.getNamedQuery("UserDetailsNew.byId");
			query.setInteger(0, 18);
			List<UserDetailsNew> users = (List<UserDetailsNew>) query.getResultList();
			session.getTransaction().commit();
			session.close();
			System.out.println("Total list of Users: "+users.size());
			for(int i=0;i<users.size();i++){
				System.out.println("User no:"+i+" with user_ID:"+users.get(i).getUserName()+" Name:"+users.get(i).getUserName());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
		
	}

	private static void runHQLqueries() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from UserDetailsNew where userId > 4");
			query.setFirstResult(15);
			query.setMaxResults(4);
			List<UserDetailsNew> users = (List<UserDetailsNew>) query.getResultList();
			session.getTransaction().commit();
			session.close();
			System.out.println("Total list of Users: "+users.size());
			for(int i=0;i<users.size();i++){
				System.out.println("User no:"+i+" with user_ID:"+users.get(i).getUserName()+" Name:"+users.get(i).getUserName());
			}
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			//String value ="18 or 1 = 1";
			String minUserId ="18";
			String userName ="User 3%";
			query = session.createQuery("select userId, userName from UserDetailsNew where userId > :userId AND userName like :likeUsername");
			query.setInteger("userId", Integer.parseInt(minUserId));
			query.setString("likeUsername", userName);
			/*query = session.createQuery("select userId, userName from UserDetailsNew where userId > ? AND userName like ?");
			query.setInteger(0, Integer.parseInt(minUserId));
			query.setString(1, userName);*/
			/*query = session.createQuery("select userId, userName from UserDetailsNew where userId > :value ");
			query.setParameter("value", Integer.parseInt(minUserId));*/
			List<Object[]> usersString = (List<Object[]>) query.getResultList();
			session.getTransaction().commit();
			session.close();
/*			for(List secondList:usersString){
				for(List<String> secondList:secondList.get(0))
					System.out.println(secondList);
			}*/
		     for(Object[] employee: usersString){
		         Integer id = (Integer)employee[0];
		         String firstName = (String)employee[1];
		         System.out.println(id+firstName);
		     }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			session.close();
		}
	}

	private static void DetachedToPersistance() {
		UserDetailsNew userInfo = new UserDetailsNew();
		userInfo.setUserName("Transient State User");
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try{
			
			session.beginTransaction();
			userInfo = session.get(UserDetailsNew.class, 1);
			session.getTransaction().commit();
			session.close();
			
			//The below code could be in different class
		    userInfo.setUserName("Changed");
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(userInfo);
/*			Even after the session close, the same object can be updated with new session
			We need not pass any new Id or get the object again*/
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			session.close();
		}
		
	}

	private static void TransientPersistance() {
		UserDetailsNew userInfo = new UserDetailsNew();
		userInfo.setUserName("Transient State User");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			userInfo.setUserName("User ");
			session.save(userInfo);
			userInfo.setUserName("Updating user info after save");
			session.getTransaction().commit();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			session.close();
		}
	}
	private static void crudOperations() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		/* CREATE OPERATION */
			try{
				session.beginTransaction();
				for(int i=0; i<10; i++){	
					UserDetailsNew user = new UserDetailsNew();
					user.setUserName("User "+(i+1));
					session.save(user);
				}
				session.getTransaction().commit();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				session.close();
			}
			
			/* READ OPERATION */
			int i=0;
			UserDetailsNew userInfo = null;
			try{
				session = sessionFactory.openSession();
				session.beginTransaction();
				for(i=0; i<10; i++){
				userInfo = (UserDetailsNew)session.get(UserDetailsNew.class, i+1);
				System.out.println("The User information fetched is of User -"+userInfo.getUserName());
				}
				session.getTransaction().commit();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Error with i----------"+i);
			}
			finally{
				session.close();
			}
			System.out.println("The User information fetched is of User -"+userInfo.getUserName());
			
			/* UPDATE OPERATION */
			try{
				session = sessionFactory.openSession();
				session.beginTransaction();
				for(i=0; i<5; i++){
					userInfo = (UserDetailsNew)session.get(UserDetailsNew.class, i+1);
					userInfo.setUserName("Mr "+userInfo.getUserName());
					System.out.println("UPDATING user as -"+userInfo.getUserName());
					session.update(userInfo);
					}

				session.getTransaction().commit();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				session.close();
			}
			
			/* DELETE OPERATION */
			try{
				session = sessionFactory.openSession();
				session.beginTransaction();
				for(i=4; i<7; i++){
					userInfo = (UserDetailsNew)session.get(UserDetailsNew.class, i+1);
					System.out.println("Deleting user is -"+userInfo.getUserName());
					session.delete(userInfo);
					}

				session.getTransaction().commit();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				session.close();
			}
			
		}

}
