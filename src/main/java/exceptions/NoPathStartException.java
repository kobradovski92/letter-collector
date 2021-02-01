package exceptions;

public class NoPathStartException extends Exception {
   public NoPathStartException() {
      super("The path contains no path start character!");
   }
}
