record Student(int id, String name, String className) {} //final by default even fields are final

// class Student{
//   private int id;
//   private String name;
//   private String className;
//
//   Student(int id, String name, String className){
//     this.id = id;
//     this.name = name;
//     this.className = className;
//   }
//
//   public int getId() {
//     return id;
//   }
//   public void setId(int id) {
//     this.id = id;
//   }
//   public String getName() {
//     return name;
//   }
//   public void setName(String name) {
//     this.name = name;
//   }
//   public String getClassName() {
//     return className;
//   }
//   public void setClassName(String className) {
//     this.className = className;
//   }
//
//   @Override
//   public String toString() {
//     return "Student[id: " + id + ", name: " + name 
//       + ", class: " + className + "]";
//   }
//
//   @Override
//   public boolean equals(Object obj) {
//     if(obj == null || !(obj instanceof Student) ) {
//       return false;
//     }
//     Student s = (Student)obj;
//
//     return this.name.equals(s.name) 
//       && this.id == s.id 
//       && this.className.equals(s.className);
//   }
//
//   @Override
//   public int hashCode() {
//     int prime = 19;
//     int result = 1;
//     result = prime * result + ((name == null) ? 0 : name.hashCode());
//     result = prime * result + ((className == null) ? 0 : className.hashCode());
//     result = prime * result + id;
//     return result;
//   }  
// }
