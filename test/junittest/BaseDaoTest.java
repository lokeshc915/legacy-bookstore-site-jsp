package junittest;

import org.hibernate.SessionFactory;

import com.bookstore.util.HibernateUtil;

public class BaseDaoTest {

	protected static SessionFactory sessionFactory  = null;
	
	public static void setUpClass() {
		sessionFactory  = HibernateUtil.getSessionFactory();
	}
	
	public static void tearDownClass() {
		sessionFactory.close();
	}

}
