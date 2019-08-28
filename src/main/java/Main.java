import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.PrintStream;
import java.io.Serializable;

public class Main
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = SessionFactoryInit();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //=============== ORM Hibernate Session =======================================================
        Course course = session.get(Course.class,2);
        Student student = session.get(Student.class,1);
        SubsPK subscriptionKey = new SubsPK();
        subscriptionKey.setStudent(student);
        subscriptionKey.setCourse(course);
        Subscription subscription = session.get(Subscription.class, subscriptionKey);
        
        System.out.printf("%nОбъект Course из таблицы Courses:%nНазвание <%s> " +
                "Преподаватель курса: %s%n%n",course.getName(),course.getTeacher().getName());
        course.getStudents().stream().map(Student::getName).forEach(System.out::println);
        System.out.printf("%nПодписка skillbox N 1: Студент %s купил(а) курс %s%nДата покупки: %s%n%n",
                subscription.getId().getStudent().getName(), subscription.getId().getCourse().getName(),
                subscription.getSubscriptionDate().toString());
        //======================= End of session =========================================================
        transaction.commit();
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
