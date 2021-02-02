import java.awt.Point;

import exceptions.CharacterNotFoundException;
import exceptions.CharacterNotUniqueException;
import exceptions.MultiplePathEndsException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathEndException;
import exceptions.NoPathStartException;
import exceptions.UnclearDirectionException;

public class LetterCollector {
   public static final char PATH_START_CHARACTER = '@';
   public static final char PATH_END_CHARACTER = 'x';
   public static final char HORIZONTAL_PATH_CHARACTER = '-';
   public static final char VERTICAL_PATH_CHARACTER = '|';

   public Direction findPathDirection(char[][] path, Point currentPosition, Direction currentDirection) throws UnclearDirectionException {
      char firstCharToRight = (currentPosition.y < (path[currentPosition.x].length - 1)) ? path[currentPosition.x][currentPosition.y + 1] : ' ';
      char firstCharToLeft = (currentPosition.y > 0) ? path[currentPosition.x][currentPosition.y - 1] : ' ';
      char firstCharToDown = (currentPosition.x < (path.length - 1)) ? path[currentPosition.x + 1][currentPosition.y] : ' ';
      char firstCharToUp = (currentPosition.x > 0) ? path[currentPosition.x - 1][currentPosition.y] : ' ';

      Direction direction = null;
      int numberOfPossibleDirections = 0;

      if (currentDirection != Direction.LEFT && (firstCharToRight == HORIZONTAL_PATH_CHARACTER || (firstCharToRight >= 'A' && firstCharToRight <= 'Z'))) {
         direction = Direction.RIGHT;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.RIGHT && (firstCharToLeft == HORIZONTAL_PATH_CHARACTER || (firstCharToLeft >= 'A' && firstCharToLeft <= 'Z'))) {
         direction = Direction.LEFT;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.UP && (firstCharToDown == VERTICAL_PATH_CHARACTER || (firstCharToDown >= 'A' && firstCharToDown <= 'Z'))) {
         direction = Direction.DOWN;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.DOWN && (firstCharToUp == VERTICAL_PATH_CHARACTER || (firstCharToUp >= 'A' && firstCharToUp <= 'Z'))) {
         direction = Direction.UP;
         numberOfPossibleDirections++;
      }

      if (numberOfPossibleDirections > 1) {
         throw new UnclearDirectionException();
      } else {
         return direction;
      }
   }

   public Point findPathStart(char[][] path) throws NoPathStartException, MultiplePathStartsException {
      try {
         return findUniquePathCharacter(path, PATH_START_CHARACTER);
      } catch (CharacterNotFoundException characterNotFoundException) {
         throw new NoPathStartException();
      } catch (CharacterNotUniqueException characterNotUniqueException) {
         throw new MultiplePathStartsException();
      }
   }

   public Point findPathEnd(char[][] path) throws NoPathEndException, MultiplePathEndsException {
      try {
         return findUniquePathCharacter(path, PATH_END_CHARACTER);
      } catch (CharacterNotFoundException characterNotFoundException) {
         throw new NoPathEndException();
      } catch (CharacterNotUniqueException characterNotUniqueException) {
         throw new MultiplePathEndsException();
      }
   }

   private Point findUniquePathCharacter(char[][] path, char character) throws CharacterNotUniqueException, CharacterNotFoundException {
      Point pathStart = new Point(-1, -1);
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

   public boolean isObstacle(char character) {
      return (character == '+'
            || (character >= 'A' && character <= 'Z')
            || character == 'x');
   }
}
