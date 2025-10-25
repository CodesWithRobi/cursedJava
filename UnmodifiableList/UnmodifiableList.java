import java.util.Arrays;
import java.util.stream.Collectors;

public class UnmodifiableList {
  public static void main(String[] args) {

    //error - 
    var list1 = Arrays.asList(1, 2, 3, 4);
    try {
      list1.add(5); 
    } catch (Exception e) {
      System.out.println(e.toString() + "cuz of custom implementation called ArrayList");
    }

    var list2 = list1.stream().toList();
    try {
      list2.add(5); 
    } catch (Exception e) {
      System.out.println(e.toString() + "cuz of custom implementation called ArrayList");
    }

    var list3 = list1.stream().collect(Collectors.toList());
    list3.add(5);
    System.out.println(list3);

  }
}
