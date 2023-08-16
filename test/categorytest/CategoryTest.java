package categorytest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bookstore.entity.Category;
import com.bookstore.util.HibernateUtil;

public class CategoryTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.openSessionFactory();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			
			Category category = new Category();
			category.setName("White Goods");
			
			session.save(category);
			
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
