/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jirap
 */
public class StudentWithDB {
    public static void main(String[] args) {
       Student std1 = new Student(1, "John", 4.00);
       Student std2 = new Student(2, "Marry", 3.75);
//       StudentTable.insertStudent(std1);
//       StudentTable.insertStudent(std2);
       Student std;
       std = StudentTable.findStudentById(1);
       if (std != null) {
           System.out.println("Detected");
           std.setName("Jack");
       }
//       StudentTable.removeStudent(std2);
       StudentTable.updateStudent(std);
       //}
       //List<Student> stdList = StudentTable.findStudentByName("Marry"); 
       List<Student> stdList = StudentTable.findAllStudent();
       printAllStudent(stdList);
    }
    public static void printAllStudent(List<Student> studentList) {
        for(Student std : studentList) {
           System.out.print(std.getId() + " ");
           System.out.print(std.getName() + " ");
           System.out.println(std.getGpa() + " ");
       }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
