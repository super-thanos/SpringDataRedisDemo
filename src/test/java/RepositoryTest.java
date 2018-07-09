import com.xt.study.spring.redis.model.Student;
import com.xt.study.spring.redis.repository.StudentRepository;
import java.util.Iterator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryTest extends BaseTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  public void repositoryTest() {
    Student student = new Student(100, "Super Thanos", (byte)0);
    Student student1 = new Student(101, "Thomas", (byte)1);
    studentRepository.save(student);
    studentRepository.save(student1);

    Student temp = studentRepository.findOne("studen:100");
    System.out.println(student);
    Iterator<Student> studentsIter = studentRepository.findAll().iterator();
    while (studentsIter.hasNext()) {
      System.out.println(studentsIter.next());
    }
  }
}
