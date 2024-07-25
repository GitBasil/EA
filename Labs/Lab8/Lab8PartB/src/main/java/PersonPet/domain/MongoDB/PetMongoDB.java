package PersonPet.domain.MongoDB;

public class PetMongoDB {
    private String name;
    private double age;

    public PetMongoDB() {
    }

    public PetMongoDB(String name, double age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return this.age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }
    
}
