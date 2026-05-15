import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Teeing {

   public static void main(String args[]) {
      // list of students
      List<Student> students = Arrays.asList(
         new Student(1, "Robert", 390),
         new Student(2, "Julie", 410),
         new Student(3, "John", 440),
         new Student(4, "Michael", 420));

      Map<String, Student> result = students.stream().collect(
      Collectors.teeing(
         // high mark, return type is Optional
         Collectors.maxBy(Comparator.comparing(Student::getMarks)),
         // low mark, return type is Optional
         Collectors.minBy(Comparator.comparing(Student::getMarks)),
         // put both student entries in the map using merger
         (s1, s2) -> Map.of("Highest", s1.get(), "Lowest", s2.get())
      ));
      System.out.println(result);
   }
}

class Student {
   int rollNo;
   String name;
   int marks;

   public Student(int rollNo, String name, int marks) {
      this.rollNo = rollNo;
      this.name = name;
      this.marks = marks;
   }

   @Override
   public String toString() {
      return "Student [RollNo=" + rollNo + ", Name=" + name + ", Marks=" + marks + "]";
   }

   public int getRollNo() {
      return rollNo;
   }

   public void setRollNo(int rollNo) {
      this.rollNo = rollNo;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getMarks() {
      return marks;
   }

   public void setMarks(int marks) {
      this.marks = marks;
   }
}
