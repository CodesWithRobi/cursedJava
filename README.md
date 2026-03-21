The JVM doesn't have a "byte stack." It uses a 32-bit stack. When you load a byte from an array, the JVM instruction baload automatically sign-extends it to a 32-bit int as it pushes it onto the stack.

One thing they almost all share is Compressed Oops (Ordinary Object Pointers). Since you have an RHCSA, you know 64-bit systems use 8-byte pointers. This wastes space. Most JVMs, by default, compress these into 4 bytes if your heap is under 32GB. The moment you go to a 31.9GB heap to a 32.1GB heap, your memory usage might actually jump by 40% because the JVM has to switch back to 8-byte pointers!

Most developers use the term "Modulo" for the % operator, but in Java, it is strictly a Remainder operator.
* In Mathematics (True Modulo): The result of a(modb) should always have the same sign as the divisor (b). If you are modding by 10, the result should always be 0 to 9.
* In Java (Remainder): The result of a % b always has the same sign as the dividend (a).

> Why Constructors are not memebers of a Class?
Because if they were memebers then they would have been inherited on extending!


Class Methods are polymorphic but Class Attributes are not polymorphic!!


new String() create object in regular heap and not in constant pool, you can .intern() this!


>Throw Early Catch Late
* The class Exception and any subclasses that are not also subclasses of RuntimeException are **checked exceptions**


>Three strict rules when using `throws`:

1.  **Unchecked Exceptions are Invisible:** You *can* declare `throws NullPointerException`, but you don't have to. The compiler only cares about **Checked Exceptions** (like `IOException`).
2.  **The "Subclass" Constraint:** If you override a method, the subclass **cannot** throw a broader exception than the parent. If the parent throws `IOException`, the child can't throw `Exception`. (You can't be more dangerous than your father!)
3.  **The Multi-Throw:** You can declare multiple exceptions separated by commas: 
    `public void load() throws IOException, SQLException { ... }`

---
