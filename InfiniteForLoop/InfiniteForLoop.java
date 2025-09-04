public class InfiniteForLoop {
  public static void main(String[] args) {
    // for(int i = 0;;i++) {
    //   System.out.println(i);
    //   continue;
    // }
    // int i = 0;
    // while( true ) {
    //   continue;
    //   System.out.println(i);
    //   i++;
    // }
    int[] arr = {1, 2, 3, 4, 5};
    for (int i : arr) {
      System.out.println("This is ");
      if(i == 3) 
        continue;
      System.out.println(i);
    }
  }
}
