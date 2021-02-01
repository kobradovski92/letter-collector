import java.awt.Point;

import exceptions.CharacterNotFoundException;
import exceptions.CharacterNotUniqueException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathStartException;

public class LetterCollector {
   public Point findPathStart(char[][] path) throws NoPathStartException, MultiplePathStartsException {
      try {
         return findUniquePathCharacter(path, '@');
      } catch (CharacterNotFoundException characterNotFoundException) {
         throw new NoPathStartException();
      } catch (CharacterNotUniqueException characterNotUniqueException) {
         throw new MultiplePathStartsException();
      }
   }

   private Point findUniquePathCharacter(char[][] path, char character) throws CharacterNotUniqueException, CharacterNotFoundException {
      Point pathStart = new Point(-1,-1);
      for (int i = 0; i < path.length; i++) {
         for (int j = 0; j < path[i].length; j++) {
            if (path[i][j] == character) {
               if (pathStart.getX() == -1) {
                  pathStart.setLocation(i, j);
               } else {
                  throw new CharacterNotUniqueException();
               }
            }
         }
      }
      if (pathStart.getX() != -1) {
         return pathStart;
      } else {
         throw new CharacterNotFoundException();
      }
   }
}
