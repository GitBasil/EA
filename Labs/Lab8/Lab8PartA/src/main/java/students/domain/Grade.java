package students.domain;

public class Grade {
    private String courseName;
    private String grade;

    public Grade() {
    }

    public Grade(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "{" +
            " courseName='" + this.courseName + "'" +
            ", grade='" + this.grade + "'" +
            "}";
    }
    
}
