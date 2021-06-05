package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor from the db
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);

			//create some courses
			Course course1 = new Course("Air Guitar");
			Course course2 = new Course("YouTuber in no time");

			//add courses to instructors
			tempInstructor.add(course1);
			tempInstructor.add(course2);

			//save the courses
			session.save(course1);
			session.save(course2);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





