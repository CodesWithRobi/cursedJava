/**
 * THE JAVA STRING LIMITS MASTER-CLASS: From Heap Limits to VM Architecture
 * -----------------------------------------------------------------------
 * This program demonstrates why Java crashes when appending to large strings.
 * It highlights the "Double-Allocation" trap and the limit of 32-bit signed integers.
 */
public static void main(String[] args) {
  // 1. BYPASSING THE CONSTANT POOL (64KB Limit)
  // We use StringBuilder to allocate at runtime, avoiding the static String 
  // limit (65,535 bytes) enforced by the JVM class file specification.
  StringBuilder sb = new StringBuilder();

  // 2. THE BITWISE MATH
  // "1110100011111111111111111110001" in binary is 1,954,545,649.
  // This is ~1.95 GB. It is safely under Integer.MAX_VALUE (2,147,483,647).
  int bigNum = Integer.parseInt("1110100011111111111111111110001", 2);

  try {
    // 3. THE DIRECT ALLOCATION (Single-Alloc Phase)
    // setLength() tells the JVM: "I need exactly this much space."
    // If your -Xmx is 4GB+, this succeeds because 1.95GB < 4GB.
    sb.setLength(bigNum);
    System.out.println("Step 1 Success: Allocated " + sb.length() + " chars.");
    System.out.println("Memory State: ~1.95GB occupied by a single char array.");

    // 4. THE GROWTH STRATEGY "GOTCHA" (Double-Alloc Phase)
    // When we append even ONE char, StringBuilder triggers resizing logic:
    // NEW_CAPACITY = (OLD_CAPACITY * 2) + 2
    // (length * elementSize) + headerSize
    // Calculation: (1.95B * 2) + 2 ≈ 3.9 Billion (Overflows Integer.MAX_VALUE!)
    System.out.println("\nAttempting to append 'a'...");



    sb.append("a");

  } catch (OutOfMemoryError e) {
    System.err.println("\n--- CRASH LOG ---");
    System.err.println("Type: " + e.getClass().getName());
    System.err.println("Message: " + e.getMessage());
    e.printStackTrace();

    /* * INTERPRETING THE ERROR:
             * * A) "Java heap space": 
             * You hit the RAM Wall. During append, the JVM tried to hold the 
             * OLD 1.95GB array AND the NEW 2.1GB array simultaneously (~4.05GB total). 
             * If -Xmx is 4g, you fail here.
             * * B) "Requested array size exceeds VM limit": 
             * You hit the Architecture Wall. You gave it enough RAM (e.g., -Xmx12g), 
             * so it survived the "Double-Alloc," but it tried to create an array 
             * larger than the JVM's maximum (Integer.MAX_VALUE - 8).
             * * C) "NegativeArraySizeException" or "Huge array size":
             * The (2n + 2) math overflowed into a negative number before the 
             * JVM could cap it at the MAX_VALUE.
             */
  }
}
