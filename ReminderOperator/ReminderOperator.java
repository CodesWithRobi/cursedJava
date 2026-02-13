void main() {
  int a = -1 % 10; // Output:-1 % is not modulus its reminder operator
  int A = Math.ceilMod(-1, 10); // Output:-1 same..
  int b = ((-1 % 10) + 10) % 10; // Output: 9
  int B = Math.floorMod(-1, 10); // Output: 9
  System.out.printf("%d %d %d %d\n",  a, A, b, B);
  // All because -1/10 truncates to 0(-0.1) and not -1(like python)
  // Remainder=Dividend−(Quotient×Divisor)
}
