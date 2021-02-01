package exceptions;

public class CharacterNotFoundException extends Exception {
   public CharacterNotFoundException() {
      super("Character not found!");
   }
}
