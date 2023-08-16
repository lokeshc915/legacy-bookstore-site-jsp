package usertest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bookstore.entity.Users;
import com.bookstore.util.HibernateUtil;

public class UserTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.openSessionFactory();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			
			Users user1 = new Users();
			user1.setName("Han");
			user1.setSurname("Man");
			user1.setEmail("h@h.com");
			user1.setPassword("321");
			
			session.save(user1);
			
			transaction.commit();
						
			System.out.println("Baþarýlý bir þekilde eklendi..");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			if(transaction != null) {
				//session.close();
				HibernateUtil.closeSessionFactory();
			}
		}
		
	}
}
