package exceptions;

public class NoPathEndException extends Exception {
   public NoPathEndException() {
      super("The path contains no path end character!");
   }
}
