import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
  public static void main(String[] args) {
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy G");
    System.out.println("Java be like: " + ft.format(date));
  }
}
