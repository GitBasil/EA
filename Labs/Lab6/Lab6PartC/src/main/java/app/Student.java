package app;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private String emailaddress;
    @ManyToOne(fetch = FetchType.LAZY)
    private School school;


    public Student() {
    }

    public Student(String firstname, String lastname, String emailaddress, School school) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.school = school;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailaddress() {
        return this.emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", emailaddress='" + getEmailaddress() + "'" +
            ", school='" + getSchool().getName() + "'" +
            "}";
    }
    
}
