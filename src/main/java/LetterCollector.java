import java.awt.Point;

import enums.Direction;
import exceptions.CharacterNotFoundException;
import exceptions.CharacterNotUniqueException;
import exceptions.MultiplePathEndsException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathEndException;
import exceptions.NoPathStartException;
import exceptions.UnclearDirectionException;
import model.ResolvedPath;

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

      if (currentDirection != Direction.LEFT && (firstCharToRight == HORIZONTAL_PATH_CHARACTER || (isLetter(firstCharToRight)) || firstCharToRight == '+' || firstCharToLeft == '@')) {
         direction = Direction.RIGHT;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.RIGHT && (firstCharToLeft == HORIZONTAL_PATH_CHARACTER || (isLetter(firstCharToLeft)) || firstCharToLeft == '+' || firstCharToLeft == '@')) {
         direction = Direction.LEFT;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.UP && (firstCharToDown == VERTICAL_PATH_CHARACTER || (isLetter(firstCharToDown)) || firstCharToDown == '+' || firstCharToLeft == '@')) {
         direction = Direction.DOWN;
         numberOfPossibleDirections++;
      }
      if (currentDirection != Direction.DOWN && (firstCharToUp == VERTICAL_PATH_CHARACTER || (isLetter(firstCharToUp)) || firstCharToUp == '+' || firstCharToLeft == '@')) {
         direction = Direction.UP;
         numberOfPossibleDirections++;
      }

      if (numberOfPossibleDirections == 2) {
         throw new UnclearDirectionException();
      } else if (numberOfPossibleDirections > 2 || numberOfPossibleDirections == 0) {
         return currentDirection;
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
      return (
            character == '+'
            || (isLetter(character))
            || character == 'x'
      );
   }


   public void moveAndCollectUntilObstacle(char[][] path, Point currentPosition, Direction currentDirection, StringBuilder pathAsCharacters) {
      Point startingPosition = new Point(currentPosition.x, currentPosition.y);
      do {
         if (currentDirection == Direction.RIGHT) {
            currentPosition.y++;
         } else if (currentDirection == Direction.LEFT) {
            currentPosition.y--;
         } else if (currentDirection == Direction.DOWN) {
            currentPosition.x++;
         } else if (currentDirection == Direction.UP) {
            currentPosition.x--;
         }
         if (!currentPosition.equals(startingPosition)) {
            pathAsCharacters.append(path[currentPosition.x][currentPosition.y]);
         }
      } while (!isObstacle(path[currentPosition.x][currentPosition.y]));
      pathAsCharacters.deleteCharAt(pathAsCharacters.length() - 1);
   }

   public boolean isLetter(char character) {
      return character >= 'A' && character <= 'Z';
   }

   public void collectIfLetterAndUnique(char[][] path, Point currentPosition, StringBuilder collectedLetters) {
      char character = path[currentPosition.x][currentPosition.y];
      if (isLetter(character) && collectedLetters.indexOf(Character.toString(character)) == -1) {
         collectedLetters.append(character);
      }
   }

   public ResolvedPath resolvePath(char[][] path) throws Exception {
      Point currentPosition = findPathStart(path);
      Point pathEnd = findPathEnd(path);

      StringBuilder pathAsCharactersBuilder = new StringBuilder(Character.toString(path[currentPosition.x][currentPosition.y]));
      StringBuilder collectedLettersBuilder = new StringBuilder();

      Direction direction = null;

     do {
         direction = findPathDirection(path, currentPosition, direction);
         moveAndCollectUntilObstacle(path, currentPosition, direction, pathAsCharactersBuilder);
         pathAsCharactersBuilder.append(path[currentPosition.x][currentPosition.y]);
         collectIfLetterAndUnique(path, currentPosition, collectedLettersBuilder);
      } while (!currentPosition.equals(pathEnd));

      return new ResolvedPath(pathAsCharactersBuilder.toString(), collectedLettersBuilder.toString());
   }
}
