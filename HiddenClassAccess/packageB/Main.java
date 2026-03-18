package packageB;
import packageA.*;

public class Main {
    public void main() {
        Shouter s = ShouterFactory.getShouter();
        s.shout(); // SUCCESS!
    }
}
