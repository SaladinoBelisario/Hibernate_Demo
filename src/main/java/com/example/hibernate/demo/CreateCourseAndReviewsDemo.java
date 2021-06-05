package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();

			//create a course
			Course tempCourse = new Course("Pacman course");

			//add reviews to the course
			tempCourse.addReview(new Review("Excelent"));
			tempCourse.addReview(new Review("Good"));
			tempCourse.addReview(new Review("Bad"));
			tempCourse.addReview(new Review("Medium rare"));

			//save the course.. and leverage the cascade all
			System.out.println("Saving the course in cascade");
			session.save(tempCourse);

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





