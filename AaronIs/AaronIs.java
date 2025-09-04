public class AaronIs {
  public static void main(String[] args) {

    String[] props = {"cool", "awesome", "gethu", "mass", "Aaron"};
    Aaron[] aaron = new Aaron[props.length];

    for(int i = 0; i < props.length; i++) {
      aaron[i] = new Aaron(props[i]);
    }

    for(Aaron myaaron: aaron) {
      System.out.println(myaaron);
    }
  }
  static class Aaron {
    String prop;

    Aaron(String prop) {
      this.prop = prop;
    }

    @Override
    public String toString() {
        return "Aaron is " + prop;
    }
  }
}
