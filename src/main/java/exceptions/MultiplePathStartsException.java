package exceptions;

public class MultiplePathStartsException extends Exception {
   public MultiplePathStartsException() {
      super("The path contains more than one path start character!");
   }
}
