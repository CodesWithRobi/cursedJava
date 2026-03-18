package packageA;

// Class is hidden, but it implements a visible interface
class HiddenClass implements Shouter {
    @Override
    public void shout() {
        System.out.println("I am public inside a hidden class!");
    }
}

public class ShouterFactory {
    public static Shouter getShouter() {
        return new HiddenClass(); // We return it as the Interface type!
    }
}
