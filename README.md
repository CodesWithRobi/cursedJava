> [!TIP] #1 DEV RULES:
* Almost always use `notifyAll()`. Waking up a few extra threads is slightly slower, but it prevents catastrophic, impossible-to-debug deadlocks where the one thread you needed stays asleep forever
* The elite way to write a file. It creates a BufferedWriter natively and safely defaults to UTF-8.
```java
try (BufferedWriter writer = Files.newBufferedWriter(Path.of("file.txt"))) {
    writer.write("I will be #1 Java Dev.");
} 
```
* If you extend the Thread Class, that means that subclass cannot extend any other Class, but if you implement Runnable interface then you can do this. 
* Rule zero of concurrent programming: never make any assumptions.
* The use of interrupt() should be reserved for situations where you want to interrupt a thread to signal it to die gracefully. 

> [!TIP] “Thread groups are best viewed as an unsuccessful experiment, and you may simply ignore their existence.”
>                                                                                         - Joshua Bloch
>                                                                                           Software Architect
>                                                                                           Oracle (Sun Microsystems)


* [MoreFunWithGenerics](https://docs.oracle.com/javase/tutorial/extra/generics/morefun.html)
* Point Constructors using New operator in Method Referencing (TreeSet::new)
* Check out the Unresolvable Logical Problem that might in future make u debug for hours.. check [DefaultMethod.java](./DefaultMethod/DefaultMethod.java)
* default (No modifier): Access is limited strictly to classes within the same package. It is often referred to as package-private
---
>Here is the exhaustive "Master Cheat Sheet" of the core `java.util` interfaces and the classes that implement them.


| Interface | What it represents | Primary Implementations (The "Engines") |
| --- | --- | --- |
| **`List`** | Ordered collection (index-based). | `ArrayList`, `LinkedList`, `Vector` (Legacy), `Stack` (Legacy) |
| **`Queue`** | FIFO line (First-In-First-Out). | `LinkedList`, `PriorityQueue`, `ArrayDeque` |
| **`Deque`** | Double-ended line (Stacks & Queues). | `ArrayDeque`, `LinkedList` |
| **`Set`** | Unique elements only (no duplicates). | `HashSet`, `LinkedHashSet`, `TreeSet` |
| **`Map`** | Key-Value pairs (Dictionary). | `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable` (Legacy) |

---

### A. The `List` Family

* **`ArrayList`**: Your default choice 95% of the time. Backed by a continuous array. Fast reads, slow middle-insertions.
* **`LinkedList`**: Backed by node pointers. Fast middle-insertions (if you already have the iterator there), but terrible memory overhead and cache locality.

### B. The `Queue` & `Deque` Family

* **`ArrayDeque`**: **The #1 Dev's choice for Stacks and Queues.** It uses a circular array internally. It is significantly faster than `LinkedList` because arrays are CPU-cache friendly, whereas `LinkedList` nodes are scattered all over the heap.
* **`LinkedList`**: Valid to use as a `Queue`, but usually outperformed by `ArrayDeque`.
* **`PriorityQueue`**: A specialized queue. Instead of FIFO, elements are ordered by their "Priority" (either natural ordering or a custom `Comparator`). It is backed by a Binary Min-Heap array.

### C. The `Set` Family

* **`HashSet`**: Your default choice. $O(1)$ lookups. The elements are completely unordered.
* **`LinkedHashSet`**: Maintains the order in which you inserted the elements. Slightly more memory overhead than `HashSet`.
* **`TreeSet`**: Maintains elements in **Sorted Order** (e.g., alphabetical or numerical). $O(\log N)$ lookups. Backed by a Red-Black Tree.

### D. The `Map` Family (Technically separate from Collections)

> `merge()` is so BASED than `put()` 
```java
// Check if "Apple" is already in map1
if (map1.containsKey("Apple")) {
    // If it exists, get the old value and add the new value
    int oldValue = map1.get("Apple");
    map1.put("Apple", oldValue + 20); 
} else {
    // If it does not exist, just insert it normally
    map1.put("Apple", 20);
}
```

* **`HashMap`**: Your default choice. $O(1)$ key lookups. Unordered keys.
* **`LinkedHashMap`**: Remembers the exact order you `put()` the keys in. Great for building LRU (Least Recently Used) caches.
* **`TreeMap`**: Keeps the keys in **Sorted Order**. $O(\log N)$ lookups.
---
>Design Principles:
* If you are typing `implements` and sighing because you have to write boilerplate for methods you don't care about → You are violating ISP.
* If you are typing extends and writing `if (obj instanceof SpecificChild)` to prevent a crash because that specific child behaves weirdly → You are violating LSP.

---

## 3. The "Hidden" Concurrent Classes

If you ever move into multithreaded server environments, `java.util` isn't enough. You will pull from `java.util.concurrent`, which maps to these same interfaces:

* **Concurrent Maps:** `ConcurrentHashMap` (The undisputed king of thread-safe maps).
* **Concurrent Queues:** `ConcurrentLinkedQueue`, `ArrayBlockingQueue` (Useful for Producer-Consumer patterns).
* **Concurrent Lists:** `CopyOnWriteArrayList` (Great when you have 100 threads reading a list, but only 1 thread occasionally updating it).

---
>🛡️ `transient` Modifier (The Teleportation Filter)

**Domain:** Java Native Serialization (`java.io.Serializable`)
**Core Purpose:** Tells the JVM to ignore a variable when converting an object into a raw byte stream for saving or network transfer.
* **The Big 3 Use Cases:** 1. **Security:** Never serialize passwords, API keys, or PII.
2. **Hardware Pointers:** Sockets, Threads, and DB Connections cannot be teleported to other machines.
3. **Heavy Caches:** Skip 50MB cached files if they can easily be re-downloaded later using a 10KB URL string.
* **The Deserialization Trap:** When an object wakes up, the JVM **bypasses the constructor**. A `transient` variable will wake up as dead (`null`, `0`, or `false`).
* **The Magic Fix:** Use a `private void readObject(ObjectInputStream in)` method to manually restore `transient` variables after the object wakes up.
* **The Paradox:** A `transient final` variable is permanently broken. It won't serialize, the constructor won't run to set it, and you can't re-assign it because it is `final`.
* **Modern Reality:** Native Java serialization is largely obsolete. If you are using JSON REST APIs (like Spring Boot with Jackson), use `@JsonIgnore` instead.

---
>⚡ `volatile` Modifier (The Hardware Commander)

**Domain:** Multithreading & CPU Architecture (`java.util.concurrent`)
**Core Purpose:** Forces the CPU to bypass the ultra-fast L1 cache and read/write the variable directly to Main Memory (RAM) so all threads have one visibility.
* **Guarantee 1: Visibility.** If Thread A changes the variable, Thread B instantly sees it. No thread will ever get stuck reading a stale, cached value.
* **Guarantee 2: The Memory Barrier.** `volatile` prevents the JIT Compiler and the physical CPU from playing "Time Travel" tricks. Code written before a `volatile` write cannot be re-ordered to execute after it.
* **The Fatal Trap (NOT Atomic):** `volatile` does **not** lock the variable. It cannot safely handle `counter++` because that is a 3-step process (Read, Add, Write). Two threads will still collide.
* **When to use it:** Only use `volatile` for independent state flags (e.g., `boolean isRunning = false;`) where the new value does not depend on the old value.
* **The Masterpiece Use Case:** The "Double-Checked Locking Singleton." Without `volatile`, instruction reordering could cause another thread to grab a half-constructed object, crashing the server with a `NullPointerException`.

---

### Quick Comparison Summary

| Feature | `transient` | `volatile` |
| --- | --- | --- |
| **What does it bypass?** | Bypasses the Serialization stream. | Bypasses the CPU's L1/L2 Cache. |
| **Primary Domain** | I/O, Networking, Data Storage | Concurrency, Memory Architecture |
| **Biggest Trap** | Forgetting to re-initialize them (Constructor bypass) | Using them for Math (Lack of Atomicity) |
| **Applicable to** | Variables only | Variables only |
---

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

>Rules for Exception Propagation in Java
* Child catch block should have specific exception for better code clarity. Parent catch block can have more generic exception handled so that if child catch block is not able to handle the exception then parent catch block can handle it.
* There in no restriction on exception hiearchy to be used in child vs parent catch block.
* If a exception is handled correctly in child catch block, then in parent, another exception can be raised and handled.

---

>Characteristics of a Daemon Thread in Java
* A Daemon thread is a low priority thread.
* A Daemon thread is a service provider thread and should not be used as user thread.
* JVM automatically closes the daemon thread(s) if no active thread is present and revives it if user threads are active again.
* A daemon thread cannot prevent JVM to exit if all user threads are done.

---

>Methods used for Inter-thread Communication
* All three methods can be called only from within a synchronized context
1.public void wait()
2.public void notify()
3.public void notifyAll()
* All three methods can be called only from within a `synchronized` context.

* `Thread.interrupted()` calls `currentThread().getAndClearInterrupt()`
* `Thread.currentThread().isInterrupted()`(non-static Thread method) returns `interrupted`


| Method | Behavior if Locked | Blocks Thread? | Return Type | Best Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **`lock()`** | Waits forever. | **Yes** | `void` | When the task *must* be completed, no matter how long it takes. |
| **`tryLock()`** | Gives up instantly. | **No** | `boolean` | Polling, or when you have alternative background work to do. |
| **`tryLock(time)`** | Waits for a specific duration. | **Yes (Temporarily)** | `boolean` | Preventing infinite deadlocks and keeping the system responsive. |


* sleep() does not release the lock when it is called but method wait() does release the lock.
* The only place you can call wait( ), notify( ) or notifyAll( ) is within a synchronized method.

---
* FileInputStream & FileOutputStream used to perform input and output of 8-bit bytes.
* FileReader & FileWriter used to perform input and output for 16-bit unicode(2 bytes).


| I/O Tool | Thread Safety | Speed | Best Use Case |
| --- | --- | --- | --- |
| **`BufferedWriter`** | **Unsafe** (Requires external locks) | Fast | Simple text files, single-threaded scripts. |
| **`BlockingQueue` + `BufferedWriter`** | **Safe** (By architectural design) | Very Fast | High-concurrency worker threads logging text data. |
| **`FileChannel`** | **Safe** (OS manages the lock) | Extremely Fast | Binary data, heavy system-level data writing. |
| **`MappedByteBuffer`** | **Safe** (If managed carefully) | **Instantaneous** | Gigabyte-sized files,custom databases,Kafka-style streaming|

---
>Lambda Expressions
* A lambda expression provides an implementation of the functional interface method
* Optional type declaration − No need to declare the type of a parameter. The compiler can inference the same from the value of the parameter.
* Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.
* Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.
Optional return keyword − The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value.
* Lambda expression throws a compilation error, if a variable is assigned a value the second time(Not effectively final). But why?
* Java does not give the Lambda the real variable, it secretly creates a hidden, identical copy of the variable's value and puts it inside the Lambda object on the Heap. This is called `Variable Capture`.
---
>Hidden Classes
* Frameworks should be able to define classes as non-discoverable implementation details of the framework, These classes can neither be linked to other classes nor discoverable using reflection.
* Extend Access Control Nest with non-discoverable classes.
* Aggressive unloading of hidden classes will help frameworks to define as many hidden classes as required without degrading the performance.
* Deprecate the non-standard API, `misc.Unsafe::defineAnonymousClass`, to be removed in future releases.
---
>Pattern Matching
* Predicate − It is a Boolean function with one argument, which checks if the target object is an instance of the specified type.
* Pattern Variable − Also known as a binding variable, if the predicate is true, the pattern variable is automatically cast to the specified type.
---
>printf
[Formatter](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)

`%[argument_index$][flags][width][.precision]conversion`


| Character | Data Type | Output Example |
| --- | --- | --- |
| **`%s`** | String / Any Object | Prints `String.valueOf(obj)`. Handles `null` safely. |
| **`%S`** | String (Uppercase) | Converts the entire string string to UPPERCASE natively. |
| **`%d`** | Integer (byte, short, int, long) | Standard decimal integer format. |
| **`%f`** | Floating-point (float, double) | Decimal fraction format. Defaults to `6` decimal places. |
| **`%c`** | Character (char) | Prints a single Unicode character. |
| **`%b`** | Boolean | Prints `true` or `false`. **Trap:** Any non-null object prints as `true`! |
| **`%h`** | Hashcode | Prints the object's hashcode in hexadecimal format. |
| **`%x`** / **`%X`** | Hexadecimal Integer | Prints integer value in base-16 notation (lowercased/uppercased). |
| **`%e`** / **`%E`** | Scientific Notation | Prints floating point numbers in computer scientific notation ($1.23e+04$). |
| **`%n`** | Platform-independent Newline | **Always use this instead of `\n**` to ensure cross-platform compatibility. |
| **`%%`** | Literal Percent Sign | Escapes the percent sign so you can print a literal "%". |


| Flag | Meaning | Behavior & Example |
| --- | --- | --- |
| **`-`** | Left Justified | Pads with spaces on the right side of the value instead of the left. |
| **`+`** | Explicit Sign | Forces the output to explicitly display `+` for positive numbers and `-` for negatives. |
| **`0`** | Zero-Padding | Pads the minimum width constraint with leading zeros instead of blank spaces. |
| **`,`** | Grouping Separator | Automatically inserts locale-specific comma separators (e.g., `1,000,000`). |
| **` `** (space) | Positive Cushion | Leaves a blank space in front of positive numbers to align perfectly with negative signs. |
| **`#`** | Alternate Form | Forces radix prefixes onto octal (`0`) and hexadecimal (`0x` / `0X`) outputs. |
 ---
