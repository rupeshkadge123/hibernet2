import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.otp.hibernate.pojo.Employee;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder
				.build());
		
		Session session = factory.openSession();

		System.out.println("Reading Complete Entity with Condition");

		Criteria criteria = session.createCriteria(Employee.class);
		Criterion criterion = Restrictions.eq("departmentId", 101);
		Criterion criterion4 = Restrictions.eq("employeeName", "amruta");
//		Criterion criterion2 = Restrictions.gt("salary", 4000);
		Criterion criterion3 = Restrictions.and(criterion,criterion4);

		criteria.add(criterion3);
		
		List list = criteria.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Employee emp = (Employee) it.next();
			System.out.println("Employee : " + emp.toString());
		}

		session.close();
	}

}
