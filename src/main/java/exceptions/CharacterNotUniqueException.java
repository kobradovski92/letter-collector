package exceptions;

public class CharacterNotUniqueException extends Exception {
   public CharacterNotUniqueException() {
      super("Character is not unique, more than one occurrence found!");
   }
}
