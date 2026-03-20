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

int height;
int width;

void clear() {
  try {
    new ProcessBuilder("clear").inheritIO().start().waitFor();
  } catch(Exception e) {
    e.printStackTrace();
  }
}

public record Point(double x, double y) {}

List<Point> getPoints(String str) {
  List<Point> points = new ArrayList<>();
  String[] art = str.lines().toArray(String[]::new);
  height = art.length;
  width = Arrays.stream(art).mapToInt(i -> i.length()).max().getAsInt();

  for(int r = 0; r < height; r++) {
    String line = art[r];
    for(int c = 0; c < line.length(); c++) {
      if(line.charAt(c) == '*') {
        double px = ((double) c / (width-1))*2 - 1;
        double py = ((double) r / (height-1))*2 - 1;
      }
    }
  }
  return null;
}

void main() {
  getPoints(obj);
  // int time = 60;
  // int FPS = 60;
  // while(time-- > 0) {
  //   try {
  //     Thread.sleep(1000/FPS);
  //   } catch(InterruptedException e) {
  //     e.printStackTrace();
  //   }
  //   clear();
  //   IO.print(time);
  // }
}
