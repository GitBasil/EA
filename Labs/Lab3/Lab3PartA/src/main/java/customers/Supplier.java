package customers;

// CREATE TABLE Supplier (
//     supplierNumber INT PRIMARY KEY not null,
//     name VARCHAR(255),
//     phone VARCHAR(255),
//     productNumber INT
// );

public class Supplier {
    private int supplierNumber;
    private String name;
    private String phone;

    public Supplier(int supplierNumber, String name, String phone) {
        this.supplierNumber = supplierNumber;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierNumber='" + supplierNumber + '\'' +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public int getSupplierNumber() {
        return supplierNumber;
    }
}
