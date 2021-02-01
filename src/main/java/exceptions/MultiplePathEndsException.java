package exceptions;

public class MultiplePathEndsException extends Exception {
   public MultiplePathEndsException() {
      super("The path contains more than one path end character!");
   }
}
