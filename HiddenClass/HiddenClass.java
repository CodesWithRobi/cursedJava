import java.lang.invoke.MethodHandles.Lookup.ClassOption;

void main() throws Exception{
  MethodHandles.Lookup lookup = MethodHandles.lookup();
  Class<?> hiddenClass = lookup.defineHiddenClass(getByteArray(), true, ClassOption.NESTMATE).lookupClass();
  Object hiddenClassObj = hiddenClass.getConstructor().newInstance();
  Method method = hiddenClassObj.getClass().getDeclaredMethod("square", Integer.class);
  Object result = method.invoke(hiddenClassObj, Integer.parseInt(IO.readln("Square of the number: ")));
  IO.println(result);
  IO.println(hiddenClass.isHidden());
  IO.println(hiddenClass.getCanonicalName());
}

public static byte[] getByteArray() throws IOException {
  InputStream is = Util.class.getClassLoader().getResourceAsStream("Util.class");
  byte[] bytes = is.readAllBytes();
  return bytes;
}
