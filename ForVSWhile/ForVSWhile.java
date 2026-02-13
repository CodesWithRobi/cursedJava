public class ForVSWhile {
  public static void main(String[] args) {
    int n = 10;
    for(int i = 0; i < n; i++) {
      System.out.print(i);
      continue;
    }
    
    int i = 0;
    while (i < n) {
      System.out.print(i);
      //Useful for testing lol  
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if(i != -9999) continue; //🤣🤣🤣 breaking java 101
      i++;
    }

    //while (i < n) {
    //  System.out.print(i);
    //  continue;
    //  i++;                   //bro stop complaining
    //}
  }
}

