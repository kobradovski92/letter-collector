package exceptions;

public class UnclearDirectionException extends Exception {
   public UnclearDirectionException() {
      super("T forks or unclear path directions are not allowed!");
   }
}
