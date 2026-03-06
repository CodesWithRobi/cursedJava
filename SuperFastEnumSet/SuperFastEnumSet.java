enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

//EnumSet is an abstract class so use its static factory methods to initialize

//Java is so obsessed with performance that
//If RegularEnumSet if length of enum is <= 64 -- uses long
//Else JumboEnumSet if length of enum is > 64 -- uses long[]

//Best use case for UserPermissions RWE UGO

void main() {
  // 1. Create a set with specific elements
  EnumSet<Day> workDays = EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY);

  // 2. Create a set with a range
  EnumSet<Day> weekDays = EnumSet.range(Day.MONDAY, Day.FRIDAY);

  // 3. Create a set with ALL elements
  EnumSet<Day> allDays = EnumSet.allOf(Day.class);

  // 4. Create an empty set (You must pass the class)
  EnumSet<Day> empty = EnumSet.noneOf(Day.class);

  // 5. Complement (Everything EXCEPT these)
  EnumSet<Day> weekend = EnumSet.complementOf(workDays);
}
