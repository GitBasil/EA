package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements ICustomerDAO{
	@Autowired
	private ILogger logger;
	
	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerDAO: saving  "+customer.getName());
		logger.log("saved in the DB: "+ customer.getName() );
	}

	@Override
	public void save(Product product) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerDAO: saving customer "+product.getName());
		logger.log("Customer is saved in the DB: "+ product.getName() );
	}
}
