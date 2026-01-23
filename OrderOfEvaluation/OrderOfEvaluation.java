/**
 * The Java programming language guarantees that every operand
 * of an operator (except the conditional operators &&, ||, and ? :)
 * appears to be fully evaluated before any part of any operand to
 * its right is evaluated."
*/

void main() {
  int a = 5;
  a = a++; // a is 6 internally but returning 5 its overwitten
  IO.println(a); // In Java it's 5 and in C it's 6
}
