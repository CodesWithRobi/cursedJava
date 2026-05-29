public class Sealed {
   public static void main(String[] args) {
      // create an instance of Manager
      Person manager = new Manager(23, "Robert");

      // get the id
      System.out.println("Id: " + getId(manager));
   }
   public static int getId(Person person) {
      // check if person is employee then return employee id
      if (person instanceof Employee) {
         return ((Employee) person).getEmployeeId();
      } 
      // if person is manager then return manager id
      else if (person instanceof Manager) {
         return ((Manager) person).getManagerId();
      }
      return -1;
   }
}

// a sealed class Person which is to be inherited by Employee
// and Manager classes
abstract sealed class Person permits Employee, Manager {
   String name;
   String getName() {
      return name;
   }
}

// Employee class has to extend Person and should have a modifier
final class Employee extends Person {
   String name;
   int id;
   Employee(int id, String name){
      this.id = id;
      this.name = name;
   }
   int getEmployeeId() {
      return id;
   }
}

// We can mark a sub-class as non-sealed, so that it can be extended if required
non-sealed class Manager extends Person {
   int id;
   Manager(int id, String name){
      this.id = id;
      this.name = name;
   }
   int getManagerId() {
      return id;
   }
}
