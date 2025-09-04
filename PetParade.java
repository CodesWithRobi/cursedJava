import java.util.ArrayList;
import java.util.List;

// Superclass
interface Animal {
    String makeSoun();
}

// Subclass
class Dog implements Animal {
    private String name;

    Dog(String name) {
        this.name = name;
    }

    public String makeSound() {
        return name + " says: Woof!";
    }

    public String makeSoun() {
        return name + " says: Woof!";
    }

    @Override
    public String toString() {
        return "Dog named " + name;
    }
}

public class PetParade {
    public static void main(String[] args) {
        // Create a List<Dog>
        List<Dog> petList = new ArrayList<>();
        petList.add(new Dog("Buddy"));
        petList.add(new Dog("Luna"));
        petList.add(new Dog("Max"));

        // For-each loop with Animal (supertype), not Dog
        System.out.println("Pet Parade Begins!");
        for (Animal pet : petList) { // Type is Animal, not Dog
            System.out.println(pet + " performs: " + pet.makeSound());
        }
        System.out.println("Parade Over!");
    }
}
