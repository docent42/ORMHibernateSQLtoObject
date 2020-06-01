import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PurchasePK implements Serializable {


  int studentId;

  int courseId;

  public PurchasePK(int studentId, int courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  @Override
  public String toString() {
    return "Student_id: " + studentId + " Course_id: " + courseId;
  }
}
