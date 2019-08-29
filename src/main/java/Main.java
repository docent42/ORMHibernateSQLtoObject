import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.w3c.dom.ls.LSOutput;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = SessionFactoryInit();
        Session session = sessionFactory.openSession();
        //
        //=============== ORM Hibernate Session =======================================================
       String hql1 = "From " + Course.class.getSimpleName();
       String hql2 = "From " + Student.class.getSimpleName();
       String hql3 = "From " + Purchase.class.getSimpleName();
       List<Course> courseList = session.createQuery(hql1).getResultList();// получаем список курсов
        List<Student> studentList = session.createQuery(hql2).getResultList();// получаем список студентов
        List<Purchase> purchaseList = session.createQuery(hql3).getResultList();// получаем список покупок
        for (Purchase purchase : purchaseList) // для каждой покупки.......
        {
            Course coursePK = courseList.stream().filter(course -> course.getName()
                    .equals(purchase.getCourseName())).toArray(Course[]::new)[0];// создаем объект курс из его названия
            Student studentPK = studentList.stream().filter(student -> student.getName()
                    .equals(purchase.getStudentName())).toArray(Student[]::new)[0];// создаем объект студент из его имени
            // генерируем составной ключ ( содержит course_id и student_id )
            SubsPK purchasePK = new SubsPK(); purchasePK.setCourse(coursePK);purchasePK.setStudent(studentPK);
            Connection connection = new Connection(); connection.setId(purchasePK);// создаем запись Connection
        //================== Transaction start ==============================================
                Transaction transaction = session.beginTransaction();
                session.save(connection); // вставляем запись в новую таблицу
                transaction.commit();
        // ================= Transaction finish =============================================
        }
        // ======================= End of session =========================================================
        sessionFactory.close();
    }
    private static SessionFactory SessionFactoryInit()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
