import java.io.IOException;
// https://gemini.google.com/app/00dab22ac6f2f6b1
String obj = """
         ******
        *      *
        *      *
        ******
        *    
   *    *    * 
    *   *   *
     *  *  *
       ***
       ***
     *  *  *
    *   *   *
   *    *    * 
""";

void clear() {
  try {
    new ProcessBuilder("clear").inheritIO().start().waitFor();
  } catch(Exception e) {
    e.printStackTrace();
  }
}

public record Point(double x, double y) {}
float[][] getDiagram(String str) {
  String[] art = str.lines().toArray(String[]::new);
  int height = art.length;
  int width = Arrays.stream(art).mapToInt(art::length).max();
}

void main() {
  int time = 60;
  int FPS = 60;
  while(time-- > 0) {
    try {
      Thread.sleep(1000/FPS);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    clear();
    IO.print(time);
  }
}
