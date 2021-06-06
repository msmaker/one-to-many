package com.msmaker.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.msmaker.hibernate.demo.entity.Instructor;
import com.msmaker.hibernate.demo.entity.InstructorDetail;
import com.msmaker.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get instructor by primary key / id
			int TheId = 1;
			Instructor tempInstructor = session.get(Instructor.class, TheId);

			System.out.println("Found instructor: " + tempInstructor);

			// delete the instructors
			if (tempInstructor != null) {

				System.out.println("Deleting: " + tempInstructor);

				// Note: Will ALSO delete associated "details" object
				// because of CascadeType.ALL
				//
				session.delete(tempInstructor);
			}

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();

		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
