import java.util.function.BiFunction;

class User {
    private String name;
    private int age;
    public User() { this.name = "Guest"; this.age = 0; }
    public User(String name) { this.name = name; this.age = 0; }
    public User(String name, int age) { this.name = name; this.age = age; }
    @Override
    public String toString() { return name + " (" + age + ")"; }
}

void main() {
  Supplier<User> guestFactory = User::new;
  Function<String, User> namedFactory = User::new;
  BiFunction<String, Integer, User> fullFactory = User::new;

  Stream.of(guestFactory.get(), namedFactory.apply("ReggaShark"), fullFactory.apply("Fabrio", -67))
    .forEach(IO::println);
}

//If you want more just make more..
@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
