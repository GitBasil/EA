package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ICustomerDAO customerDAO;
	@Autowired
	private IEmailSender emailSender;

	@Override
	public void addProduct(String email, String name, Double price) {
		Product product = new Product(name, price);
		customerDAO.save(product);
		emailSender.sendEmail(email, "Added " + name + " as a new product");
	}
}
