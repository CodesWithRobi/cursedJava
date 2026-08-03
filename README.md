# Java Master Notes

> A growing, living cheat sheet of hard-won Java knowledge.

## Table of Contents

1. [The Collections Cheat Sheet (`java.util`)](#1-the-collections-cheat-sheet-javautil)
2. [The "OP" One-Liners (LeetCode Cheat Codes)](#2-the-op-one-liners-leetcode-cheat-codes)
3. [Concurrency and Threads](#3-concurrency-and-threads)
4. [Exceptions](#4-exceptions)
5. [The `volatile` and `transient` Modifiers](#5-the-volatile-and-transient-modifiers)
6. [I/O and Serialization](#6-io-and-serialization)
7. [JVM and Language Internals](#7-jvm-and-language-internals)
8. [Modern Java Features](#8-modern-java-features)
9. [printf / Formatter Cheat Sheet](#9-printf--formatter-cheat-sheet)

---

## 1. The Collections Cheat Sheet (`java.util`)

> The exhaustive "Master Cheat Sheet" of the core `java.util` interfaces and the classes that implement them.

| Interface | What it represents | Primary Implementations (The "Engines") |
| --- | --- | --- |
| **`List`** | Ordered collection (index-based). | `ArrayList`, `LinkedList`, `Vector` (Legacy), `Stack` (Legacy) |
| **`Queue`** | FIFO line (First-In-First-Out). | `LinkedList`, `PriorityQueue`, `ArrayDeque` |
| **`Deque`** | Double-ended line (Stacks & Queues). | `ArrayDeque`, `LinkedList` |
| **`Set`** | Unique elements only (no duplicates). | `HashSet`, `LinkedHashSet`, `TreeSet` |
| **`Map`** | Key-Value pairs (Dictionary). | `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable` (Legacy) |

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

* **`HashMap`**: Your default choice. $O(1)$ key lookups. Unordered keys.
* **`LinkedHashMap`**: Remembers the exact order you `put()` the keys in. Great for building LRU (Least Recently Used) caches.
* **`TreeMap`**: Keeps the keys in **Sorted Order**. $O(\log N)$ lookups.
* **`merge()` is so BASED than `put()`** → full breakdown in [Section 2, item 1](#2-the-op-one-liners-leetcode-cheat-codes).

### Design Principles

* If you are typing `implements` and sighing because you have to write boilerplate for methods you don't care about → You are violating ISP.
* If you are typing extends and writing `if (obj instanceof SpecificChild)` to prevent a crash because that specific child behaves weirdly → You are violating LSP.

---

## 2. The "OP" One-Liners (LeetCode Cheat Codes)

> Methods most people overlook because they learned Java 7 or just translated their C++ logic directly. These kill dozens of lines of boilerplate.

### 1. `Map.merge()` — The Frequency Map King

`merge()` is so BASED than `put()`:

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
That whole thing collapses to ONE line:
```java
map1.merge("Apple", 20, Integer::sum);
```
Key absent → puts `20`. Key present → applies the remapping function to the old value and `20`. If the remapping returns `null`, the key is removed entirely.

The LeetCode frequency-map classic:

```java
map.put(num, map.getOrDefault(num, 0) + 1);   // old
map.merge(num, 1, Integer::sum);              // OP
```

### 2. `computeIfAbsent()` — The Adjacency List King

```java
if (!adjList.containsKey(node)) {             // old (3 lines, 2 lookups)
    adjList.put(node, new ArrayList<>());
}
adjList.get(node).add(neighbor);

adjList.computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor);   // OP (1 lookup)
```
If the list is missing, it creates it, puts it in the map, and returns the *reference* so you can chain `.add()` immediately. Its cousins: `computeIfPresent()` (remap only if key exists), `putIfAbsent()`, and `compute()`.

### 3. `Arrays.binarySearch()` — The Insertion Point Hack

When the target is NOT found it doesn't return `-1` — it returns `-(insertion_point) - 1`. That's Java's `lower_bound`.

```java
int index = Arrays.binarySearch(nums, target);
if (index < 0) {
    int insertHere = -(index) - 1;   // exactly where target belongs, without re-searching
}
```
Massive for *Longest Increasing Subsequence*.

### 4. `TreeMap` / `TreeSet` Navigators — "Closest Element" Cheat Codes

All `O(log N)`.

| Call | Returns |
| --- | --- |
| `map.floorKey(k)` / `set.floor(k)` | greatest key ≤ k |
| `map.ceilingKey(k)` / `set.ceiling(k)` | smallest key ≥ k |
| `map.lowerKey(k)` / `set.lower(k)` | greatest key < k |
| `map.higherKey(k)` / `set.higher(k)` | smallest key > k |

No manual binary search. Dump values in, let these do the work (*My Calendar* problems).

### 5. The `Collections` Toolkit

* **`Collections.swap(list, i, j)`** — no temp variable.
* **`Collections.rotate(list, k)`** — rotates right by `k`, all the modulo math handled.
* **`Collections.frequency(list, x)`** — count occurrences in one line.
* **`Collections.reverse(list)`** — the "swap i with len-1-i" loop in one call.

### 6. `Integer.bitCount()` & friends

* `Integer.bitCount(n)` — Hamming weight, one call (no loop of `n & (n-1)`).
* `Integer.highestOneBit(n)` / `lowestOneBit(n)` — powers of two extraction.
* `Integer.numberOfLeadingZeros(n)` — used in "power of two" / bit tricks.

### 7. `Math.floorMod()` — True Modulo

`%` is a *remainder* (sign follows the dividend): `-3 % 10 == -3`. `Math.floorMod(-3, 10) == 7`. Use it for circular-array wrapping that must stay non-negative. (See also [§7 — Modulo vs Remainder](#7-jvm-and-language-internals).)

### 8. `String.repeat()` & `String.join()`

```java
String bar = "-".repeat(50);                  // no padding loops
String csv = String.join(", ", names);        // no StringBuilder + comma juggling
StringBuilder sb = new StringBuilder(s);
String rev = sb.reverse().toString();         // palindrome check in one line
```

### 9. `Arrays.copyOfRange()` / `Arrays.fill()`

* `Arrays.copyOfRange(arr, l, r)` — clean subarray clone (r exclusive).
* `Arrays.fill(dp, -1)` — reset your entire DP array in one line.

### 10. The `Set.add()` / `Set.remove()` Boolean Trick

They return whether the set *actually changed* — a free XOR-ish filter:

```java
for (int x : nums) if (!set.add(x)) return x;   // first duplicate
for (int x : nums) if (set.remove(x)) return x; // ...and its first-come pair
```

### 11. `Character.digit()` — Char-to-int Without Parsing

```java
int d = Character.digit(c, 10);   // '0'-'9' → 0-9, any other char → -1 (no exceptions)
```

### 12. Immutable Factories — `List.of()` / `Set.of()` / `Map.of()`

```java
List<Integer> l = List.of(1, 2, 3);
Map.of(0, "zero", 1, "one");   // up to 10 pairs, or Map.ofEntries(...) for more
Set.of("a", "b", "c");
```
One-liner literal collections (no `null`s allowed, no more `Arrays.asList` wrappers). Perfect for lookup tables and hardcoded test cases.

---

## 3. Concurrency and Threads

### The #1 Dev Rules

* Almost always use `notifyAll()`. Waking up a few extra threads is slightly slower, but it prevents catastrophic, impossible-to-debug deadlocks where the one thread you needed stays asleep forever.
* If you extend the `Thread` class, that subclass cannot extend any other class — implement `Runnable` instead and you can.
* Rule zero of concurrent programming: never make any assumptions.
* The use of `interrupt()` should be reserved for situations where you want to interrupt a thread to signal it to die gracefully.

> [!TIP] "Thread groups are best viewed as an unsuccessful experiment, and you may simply ignore their existence."
> — Joshua Bloch, Software Architect, Oracle (Sun Microsystems)

### Daemon Threads

* A Daemon thread is a low priority thread.
* A Daemon thread is a service provider thread and should not be used as user thread.
* JVM automatically closes the daemon thread(s) if no active thread is present and revives it if user threads are active again.
* A daemon thread cannot prevent JVM to exit if all user threads are done.

### Inter-thread Communication

* `wait()`, `notify()`, and `notifyAll()` can be called **only from within a `synchronized` context**.
* `sleep()` does **not** release the lock when called, but `wait()` does.
* `Thread.interrupted()` calls `currentThread().getAndClearInterrupt()` — static, and it **clears** the flag.
* `Thread.currentThread().isInterrupted()` — non-static, returns the flag without clearing it.

### Locking (`java.util.concurrent.locks`)

| Method | Behavior if Locked | Blocks Thread? | Return Type | Best Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **`lock()`** | Waits forever. | **Yes** | `void` | When the task *must* be completed, no matter how long it takes. |
| **`tryLock()`** | Gives up instantly. | **No** | `boolean` | Polling, or when you have alternative background work to do. |
| **`tryLock(time)`** | Waits for a specific duration. | **Yes (Temporarily)** | `boolean` | Preventing infinite deadlocks and keeping the system responsive. |

### The "Hidden" Concurrent Collections

If you ever move into multithreaded server environments, `java.util` isn't enough. You will pull from `java.util.concurrent`, which maps to these same interfaces:

* **Concurrent Maps:** `ConcurrentHashMap` (The undisputed king of thread-safe maps).
* **Concurrent Queues:** `ConcurrentLinkedQueue`, `ArrayBlockingQueue` (Useful for Producer-Consumer patterns).
* **Concurrent Lists:** `CopyOnWriteArrayList` (Great when you have 100 threads reading a list, but only 1 thread occasionally updating it).

### `volatile`

See [Section 5](#5-the-volatile-and-transient-modifiers) — the full deep dive lives there with `transient`.

---

## 4. Exceptions

> Throw Early, Catch Late
* The class `Exception` and any subclasses that are not also subclasses of `RuntimeException` are **checked exceptions**.

### Three strict rules when using `throws`

1. **Unchecked Exceptions are Invisible:** You *can* declare `throws NullPointerException`, but you don't have to. The compiler only cares about **Checked Exceptions** (like `IOException`).
2. **The "Subclass" Constraint:** If you override a method, the subclass **cannot** throw a broader exception than the parent. If the parent throws `IOException`, the child can't throw `Exception`. (You can't be more dangerous than your father!)
3. **The Multi-Throw:** You can declare multiple exceptions separated by commas:
   `public void load() throws IOException, SQLException { ... }`

### Rules for Exception Propagation

* Child catch block should have specific exception for better code clarity. Parent catch block can have more generic exception handled so that if child catch block is not able to handle the exception then parent catch block can handle it.
* There is no restriction on exception hierarchy to be used in child vs parent catch block.
* If an exception is handled correctly in child catch block, then in parent, another exception can be raised and handled.

---

## 5. The `volatile` and `transient` Modifiers

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

>⚡ `volatile` Modifier (The Hardware Commander)

**Domain:** Multithreading & CPU Architecture (`java.util.concurrent`)
**Core Purpose:** Forces the CPU to bypass the ultra-fast L1 cache and read/write the variable directly to Main Memory (RAM) so all threads have one visibility.
* **Guarantee 1: Visibility.** If Thread A changes the variable, Thread B instantly sees it. No thread will ever get stuck reading a stale, cached value.
* **Guarantee 2: The Memory Barrier.** `volatile` prevents the JIT Compiler and the physical CPU from playing "Time Travel" tricks. Code written before a `volatile` write cannot be re-ordered to execute after it.
* **The Fatal Trap (NOT Atomic):** `volatile` does **not** lock the variable. It cannot safely handle `counter++` because that is a 3-step process (Read, Add, Write). Two threads will still collide.
* **When to use it:** Only use `volatile` for independent state flags (e.g., `boolean isRunning = false;`) where the new value does not depend on the old value.
* **The Masterpiece Use Case:** The "Double-Checked Locking Singleton." Without `volatile`, instruction reordering could cause another thread to grab a half-constructed object, crashing the server with a `NullPointerException`.

### Quick Comparison Summary

| Feature | `transient` | `volatile` |
| --- | --- | --- |
| **What does it bypass?** | Bypasses the Serialization stream. | Bypasses the CPU's L1/L2 Cache. |
| **Primary Domain** | I/O, Networking, Data Storage | Concurrency, Memory Architecture |
| **Biggest Trap** | Forgetting to re-initialize them (Constructor bypass) | Using them for Math (Lack of Atomicity) |
| **Applicable to** | Variables only | Variables only |

---

## 6. I/O and Serialization

* The elite way to write a file. It creates a `BufferedWriter` natively and safely defaults to UTF-8:
```java
try (BufferedWriter writer = Files.newBufferedWriter(Path.of("file.txt"))) {
    writer.write("I will be #1 Java Dev.");
} 
```
* `FileInputStream` & `FileOutputStream` perform input and output of 8-bit **bytes**.
* `FileReader` & `FileWriter` perform input and output of 16-bit **Unicode** (2 bytes).

| I/O Tool | Thread Safety | Speed | Best Use Case |
| --- | --- | --- | --- |
| **`BufferedWriter`** | **Unsafe** (Requires external locks) | Fast | Simple text files, single-threaded scripts. |
| **`BlockingQueue` + `BufferedWriter`** | **Safe** (By architectural design) | Very Fast | High-concurrency worker threads logging text data. |
| **`FileChannel`** | **Safe** (OS manages the lock) | Extremely Fast | Binary data, heavy system-level data writing. |
| **`MappedByteBuffer`** | **Safe** (If managed carefully) | **Instantaneous** | Gigabyte-sized files, custom databases, Kafka-style streaming |

---

## 7. JVM and Language Internals

* The JVM doesn't have a "byte stack." It uses a 32-bit stack. When you load a byte from an array, the JVM instruction `baload` automatically sign-extends it to a 32-bit int as it pushes it onto the stack.
* **Compressed Oops:** 64-bit systems use 8-byte pointers, which wastes space. Most JVMs compress these into 4 bytes if your heap is under 32GB. The moment you go from a 31.9GB heap to a 32.1GB heap, your memory usage might actually jump by 40% because the JVM has to switch back to 8-byte pointers!

### Modulo vs Remainder

Most developers use the term "Modulo" for the `%` operator, but in Java, it is strictly a **Remainder** operator.
* In Mathematics (True Modulo): The result of `a mod b` should always have the same sign as the divisor (`b`). If you are modding by 10, the result should always be 0 to 9.
* In Java (Remainder): The result of `a % b` always has the same sign as the dividend (`a`).

Want true modulo? Use `Math.floorMod()` — see [§2, item 7](#2-the-op-one-liners-leetcode-cheat-codes).

### OOP Odds & Ends

* Why are Constructors not members of a Class? Because if they were members then they would have been inherited on extending!
* Class Methods are polymorphic but Class Attributes are not polymorphic!!
* `new String()` creates an object in the regular heap, not the constant pool — you can `.intern()` it!
* `default` (No modifier): Access is limited strictly to classes within the same package. It is often referred to as **package-private**.

---

## 8. Modern Java Features

### Lambda Expressions

* A lambda expression provides an implementation of the functional interface method.
* Optional type declaration − No need to declare the type of a parameter. The compiler can infer it from the value of the parameter.
* Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.
* Optional curly braces − No need to use curly braces in expression body if the body contains a single statement.
* Optional return keyword − The compiler automatically returns the value if the body has a single expression. Curly braces are required to indicate that expression returns a value.
* A lambda expression throws a compilation error if a variable is assigned a value the second time (not effectively final). But why?
* Java does not give the Lambda the real variable — it secretly creates a hidden, identical copy of the variable's value and puts it inside the Lambda object on the Heap. This is called **Variable Capture**.

### Method References & Generics

* Point Constructors using the `new` operator in Method Referencing (e.g., `TreeSet::new`).
* [MoreFunWithGenerics](https://docs.oracle.com/javase/tutorial/extra/generics/morefun.html)
* Check out the Unresolvable Logical Problem that might in future make you debug for hours.. check [DefaultMethod.java](./DefaultMethod/DefaultMethod.java)

### Hidden Classes

* Frameworks should be able to define classes as non-discoverable implementation details of the framework. These classes can neither be linked to other classes nor discoverable using reflection.
* Extend Access Control Nest with non-discoverable classes.
* Aggressive unloading of hidden classes will help frameworks to define as many hidden classes as required without degrading the performance.
* Deprecate the non-standard API, `misc.Unsafe::defineAnonymousClass`, to be removed in future releases.

### Pattern Matching

* Predicate − It is a Boolean function with one argument, which checks if the target object is an instance of the specified type.
* Pattern Variable − Also known as a binding variable, if the predicate is true, the pattern variable is automatically cast to the specified type.

---

## 9. printf / Formatter Cheat Sheet

[Formatter](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)

`%[argument_index$][flags][width][.precision]conversion`

### Conversion Characters

| Character | Data Type | Output Example |
| --- | --- | --- |
| **`%s`** | String / Any Object | Prints `String.valueOf(obj)`. Handles `null` safely. |
| **`%S`** | String (Uppercase) | Converts the entire string to UPPERCASE natively. |
| **`%d`** | Integer (byte, short, int, long) | Standard decimal integer format. |
| **`%f`** | Floating-point (float, double) | Decimal fraction format. Defaults to `6` decimal places. |
| **`%c`** | Character (char) | Prints a single Unicode character. |
| **`%b`** | Boolean | Prints `true` or `false`. **Trap:** Any non-null object prints as `true`! |
| **`%h`** | Hashcode | Prints the object's hashcode in hexadecimal format. |
| **`%x`** / **`%X`** | Hexadecimal Integer | Prints integer value in base-16 notation (lowercased/uppercased). |
| **`%e`** / **`%E`** | Scientific Notation | Prints floating point numbers in computer scientific notation ($1.23e+04$). |
| **`%n`** | Platform-independent Newline | **Always use this instead of `\n`** to ensure cross-platform compatibility. |
| **`%%`** | Literal Percent Sign | Escapes the percent sign so you can print a literal "%". |

### Flags

| Flag | Meaning | Behavior & Example |
| --- | --- | --- |
| **`-`** | Left Justified | Pads with spaces on the right side of the value instead of the left. |
| **`+`** | Explicit Sign | Forces the output to explicitly display `+` for positive numbers and `-` for negatives. |
| **`0`** | Zero-Padding | Pads the minimum width constraint with leading zeros instead of blank spaces. |
| **`,`** | Grouping Separator | Automatically inserts locale-specific comma separators (e.g., `1,000,000`). |
| **` `** (space) | Positive Cushion | Leaves a blank space in front of positive numbers to align perfectly with negative signs. |
| **`#`** | Alternate Form | Forces radix prefixes onto octal (`0`) and hexadecimal (`0x` / `0X`) outputs. |
