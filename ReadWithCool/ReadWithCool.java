import java.io.BufferedReader;

void main() throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  System.out.println("I will talk back:");
  String str;
  do {
    str = br.readLine();
    System.out.write(str.getBytes());
    System.out.write('\n');
  } while(!str.equals("stop"));
}
