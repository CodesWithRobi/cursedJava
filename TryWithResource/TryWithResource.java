import java.io.*;

void main() {
  IO.print("Output file name:");
  Scanner sc = new Scanner(System.in);
  try(sc; // Java 9 feature no need to declare a new variable
      FileWriter fw = new FileWriter(sc.next());
      PrintWriter out = new PrintWriter(fw)
  ) {
    out.println("Bro all these classes implemented AutoClosables and this try-with-resource call em automatically");
  } catch (IOException e) {
    e.printStackTrace();
    e.getSuppressed(); // To get Returns an array containing all of the exceptions that were suppressed
  }
}
