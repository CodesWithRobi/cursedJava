void main() {
  NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.LONG);
  IO.println(formatter.format(1000));
  IO.println(formatter.format(1000 * 1000));
  IO.println(formatter.format(1000 * 1000 * 1000));

  formatter = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
  formatter.setMinimumFractionDigits(2);
  IO.println(formatter.format(1000));
  IO.println(formatter.format(1000 * 1022));
  IO.println(formatter.format(1000 * 1003 * 1004));
}
