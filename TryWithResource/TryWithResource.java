import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

void main() {
  IO.print("Output file name:");
  try(Scanner sc = new Scanner(System.in);
      FileWriter fw = new FileWriter(sc.next());
      PrintWriter out = new PrintWriter(fw)
  ) {
    out.println("Bro all these classes implemented AutoClosables and this try-with-resource call em automatically");
  } catch (IOException e) {
    e.printStackTrace();
    e.getSuppressed(); // To retrieve the suppressed expression as the primary one is shown
  }
}
