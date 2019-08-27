import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Course course = session.get(Course.class,1); int teacherId = course.getTeacherId();
        Teacher teacher = session.get(Teacher.class,teacherId);
        Student student = session.get(Student.class,(int) Math.round(Math.random() * 100));
        System.out.printf("%nОбъект Course из таблицы Courses:%nНазвание <%s> " +
                "Преподаватель курса: %s%n",course.getName(),teacher.getName());
        System.out.printf("%nСлучайный студент skillbox: %s %nДата регистрации: %tD%n%n",student.getName(),
                        student.getRegistrationDate());
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
