package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor detail object
			int ID = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, ID);
			System.out.println("InstructorDetail: " + tempInstructorDetail);

			// print the associated instructor
			System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());

			// deleting the instructor detail in cascade
			System.out.println("Deleting InstructorDetail: " + tempInstructorDetail);
			// we need to break the bi-directional binding before deleting
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			// resolve leak connection
			session.close();
			factory.close();
		}
	}

}





