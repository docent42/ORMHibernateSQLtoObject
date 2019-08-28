import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = SessionFactoryInit();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //=============== ORM Hibernate Session =======================================================
        Course course = session.get(Course.class,5);

        System.out.printf("%nОбъект Course из таблицы Courses:%nНазвание <%s> " +
                "Преподаватель курса: %s%n",course.getName(),course.getTeacher().getName());
        //System.out.printf("%nСлучайный студент skillbox: %s %nДата регистрации: %tD%n%n",student.getName(),
                        //student.getRegistrationDate());
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
