void main() {
  int meters = 1000;
  int minutes = 10;

  double hours = minutes/60.0;
  double seconds = minutes*60.0;

  //Intermediate Rounding Errors!!

  IO.println("1.Mph: " + meters/(1609.0*hours));                    //1.Mph: 3.729024238657552  
  IO.println("2.Mph: " + (meters * (1.0/hours)) * (1 / 1609.0));    //2.Mph: 3.729024238657551  
  IO.println("3.Mph: " + (meters / hours) * (1 / 1609.0));          //3.Mph: 3.729024238657551  
  IO.println("4.Mph: " + (1/ 1609.0) * (1/hours) / (1.0/meters) );  //4.Mph: 3.729024238657551  
  IO.println("5.Mph: " + (meters / 1609.0 ) * (1 / hours));         //5.Mph: 3.7290242386575514 
  IO.println("6.Mph: " + meters/hours / 1609.0 );                   //6.Mph: 3.7290242386575514 
  IO.println("7.Mph: " + meters/1609.0 / hours );                   //7.Mph: 3.7290242386575514 
  IO.println("8.Mph: " + (meters/1609.0) / (seconds/3600.0));       //8.Mph: 3.7290242386575514 
  IO.println("9.Mph: " + (1/(1609.0 * hours)) / (1.0/meters));      //9.Mph: 3.7290242386575514 
  IO.println("10.Mph: " + (meters * (1.0/hours)) / 1609.0);         //10.Mph: 3.7290242386575514


  double value = 0.1;

  // 1. What we think it is
  System.out.println("Standard output: " + value);

  // 2. What it ACTUALLY is in memory (using BigDecimal to bypass formatting)
  System.out.println("Exact memory value: " + new BigDecimal(value));

  // 3. The Math Error
  // 0.1 + 0.1 + 0.1 should be 0.3. Let's see...
  double sum = 0.1 + 0.1 + 0.1; // or 3 * 0.1
  System.out.println("\nIs 0.1 + 0.1 + 0.1 equal to 0.3?");
  System.out.println("Sum result: " + sum);
  System.out.println("Are they equal? " + (sum == 0.3));

  // 4. Seeing the difference
  System.out.println("Actual 0.3 in memory: " + new BigDecimal(0.3)); //Thats why we must use BigDecimal("0.3") note the ""

}
