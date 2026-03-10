package packageB;
import packageA.Parent;

public class Child extends Parent {
    public void testAccess() {
        // 1. ACCESS GRANTED: 
        // We are inside Child, and 'secret' is inherited.
        System.out.println(this.secret); 

        // 2. ACCESS GRANTED:
        // We are accessing it through another Child instance.
        Child otherChild = new Child();
        System.out.println(otherChild.secret);

        // 3. ACCESS DENIED (The Quirk!):
        // Even though we are a subclass, we CANNOT access it 
        // through a direct Parent reference from another package.
        Parent p = new Child();
        System.out.println(p.secret); // COMPILE ERROR!
    }
}
