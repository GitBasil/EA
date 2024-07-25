package dao;


import java.util.Collection;
import java.util.Iterator;

public class Application {
	    
	    public static void main(String[] args) {
//	        Application application = new Application();
	        ProductDAO productDAO = new ProductDAO();
	        // create Products
	        Product product1 = new Product(1101, "Flatscreen TV", 445.25);
	        productDAO.save(product1);
	        Product product2 = new Product(1106, "DVD Recorder", 189.40);
	        productDAO.save(product2);
	        // show Products
	        showProducts(productDAO);
	        // update Product
	        product2.setPrice(175.00);
	        productDAO.update(product2);
	        // get Product
	        Product product3=productDAO.load(1106);
	        if (product3 != null)
	           System.out.println("Get Product: number = "+product3.getProductnumber() +" name = "+product3.getProductName() +" price = "+ product3.getPrice());
	        // delete Product
	        productDAO.delete(product1);
	        // show Products
	        showProducts(productDAO);
	    }
	    
	    public static void showProducts(ProductDAO productDAO){
	        Collection productlist = productDAO.getAllProducts();
	        System.out.println("show all Products. We have "+productlist.size()+" Products");
	        Iterator iterator= productlist.iterator();
	        while (iterator.hasNext()){
	            Product product = (Product)iterator.next();
	            System.out.println("Product number = "+product.getProductnumber() +" name = "+product.getProductName() +" price = "+ product.getPrice());
	        }
	    }
	    
	}
