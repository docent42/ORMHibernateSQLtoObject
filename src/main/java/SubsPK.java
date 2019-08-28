
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
class SubsPK implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    Student getStudent() {
        return student;
    }

    void setStudent(Student student) {
        this.student = student;
    }

    Course getCourse() {
        return course;
    }

    void setCourse(Course course) {
        this.course = course;
    }
}
