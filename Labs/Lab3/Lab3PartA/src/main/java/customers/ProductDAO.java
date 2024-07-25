package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
        jdbcTemplate.update("DELETE from Supplier",namedParameters);
    }

    public void save(Product product) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)",namedParameters);

        // save Supplier
        Map<String,Object> namedParameterscc = new HashMap<String,Object>();
        namedParameterscc.put("productnumber", product.getProductNumber());
        namedParameterscc.put("supplierNumber", product.getSupplier().getSupplierNumber());
        namedParameterscc.put("name", product.getSupplier().getName());
        namedParameterscc.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update("INSERT INTO Supplier VALUES ( :supplierNumber, :name, :phone, :productnumber)",namedParameterscc);
    }

    public void removeProduct(Product product) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productnumber", product.getProductNumber());
        jdbcTemplate.update("DELETE from product Where productNumber = :productnumber",namedParameters);
        // delete from supplier
        jdbcTemplate.update("DELETE from Supplier Where productNumber = :productnumber",namedParameters);
    }


    public Product findByProductNumber(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);

        return product;
    }

    public Product findByProductName(String productName){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("name", productName);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "name =:name ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);     

        return product;
    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                rs.getString("name"),
                rs.getDouble("price")));

        for (Product product: products){
            Supplier supplier = getSupplierForProduct(product.getProductNumber());
            product.setSupplier(supplier);  
        }      

        return products;
    }

    Supplier getSupplierForProduct(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM Supplier WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getInt("supplierNumber"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }
}
