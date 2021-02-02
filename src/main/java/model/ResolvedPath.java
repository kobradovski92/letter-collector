package model;

public class ResolvedPath {
   private String pathAsCharacters;
   private String collectedLetters;

   public ResolvedPath(String pathAsCharacters, String collectedLetters) {
      this.pathAsCharacters = pathAsCharacters;
      this.collectedLetters = collectedLetters;
   }

   public String getPathAsCharacters() {
      return pathAsCharacters;
   }

   public String getCollectedLetters() {
      return collectedLetters;
   }
}
