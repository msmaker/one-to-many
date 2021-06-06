package com.msmaker.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.msmaker.hibernate.demo.entity.Course;
import com.msmaker.hibernate.demo.entity.Instructor;
import com.msmaker.hibernate.demo.entity.InstructorDetail;
import com.msmaker.hibernate.demo.entity.Student;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get a course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);

			// delete course
			System.out.println("Deleting course: " + tempCourse);

			session.delete(tempCourse);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {

			// add clean code
			session.close();

			factory.close();

		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
