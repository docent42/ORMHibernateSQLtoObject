
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = SessionFactoryInit();
        Session session = sessionFactory.openSession();
        //
        //=============== ORM Hibernate Session =======================================================
        List<Object[]> resultList = session.createSQLQuery(
            "select st.id as student_id , cu.id as course_id from sk2.purchaselist pl \n"
             + "join sk2.students st on pl.student_name = st.name \n"
             + "join sk2.courses cu on pl.course_name = cu.name").list();

         resultList.forEach(row -> {
             Transaction transaction = session.beginTransaction();
             Connection connection = new Connection();
             connection.setId(new PurchasePK((int)row[0],(int)row[1]));
             session.save(connection);// вставляем запись в новую таблицу
             transaction.commit();
         });

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
