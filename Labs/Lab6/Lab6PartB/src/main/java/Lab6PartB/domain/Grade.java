package Lab6PartB.domain;

import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private int id;
    private double grade;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    private Course course;

    public Grade() {
    }

    public Grade(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return this.id;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", grade='" + getGrade() + "'" +
            "}";
    }

}
