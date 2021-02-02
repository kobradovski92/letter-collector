import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.awt.Point;

import org.junit.Test;

import enums.Direction;
import exceptions.MultiplePathEndsException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathEndException;
import exceptions.NoPathStartException;
import exceptions.UnclearDirectionException;
import model.ResolvedPath;

public class LetterCollectorTest {
   @Test
   public void findPathStart_OnePathStartCharacter_StartCharacterCoordinatesReturned() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', ' ', '-'},
            {' ', LetterCollector.PATH_START_CHARACTER, '-'}
      };

      // when
      Point pathStart = letterCollector.findPathStart(path);

      // then
      assertThat(pathStart.x, equalTo(1));
      assertThat(pathStart.y, equalTo(1));
   }

   @Test(expected = MultiplePathStartsException.class)
   public void findPathStart_MultiplePathStartCharacters_MultiplePathStartsExceptionIsThrown() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', LetterCollector.PATH_START_CHARACTER, '-'},
            {' ', LetterCollector.PATH_START_CHARACTER, '-'}
      };

      // when
      letterCollector.findPathStart(path);

      // then
   }

   @Test(expected = NoPathStartException.class)
   public void findPathStart_NoPathStartCharacter_NoPathStartExceptionIsThrown() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', ' ', '-'},
            {' ', '-'}
      };

      // when
      letterCollector.findPathStart(path);

      // then
   }

   @Test
   public void findPathEnd_OnePathEndCharacter_EndCharacterCoordinatesReturned() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', ' ', '-'},
            {' ', LetterCollector.PATH_END_CHARACTER, '-'}
      };

      // when
      Point pathStart = letterCollector.findPathEnd(path);

      // then
      assertThat(pathStart.x, equalTo(1));
      assertThat(pathStart.y, equalTo(1));
   }

   @Test(expected = MultiplePathEndsException.class)
   public void findPathEnd_MultiplePathEndCharacters_MultiplePathEndsExceptionIsThrown() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', LetterCollector.PATH_END_CHARACTER, '-'},
            {' ', LetterCollector.PATH_END_CHARACTER, '-'}
      };

      // when
      letterCollector.findPathEnd(path);

      // then
   }

   @Test(expected = NoPathEndException.class)
   public void findPathEnd_NoPathEndCharacter_NoPathEndExceptionIsThrown() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-', ' ', '-'},
            {' ', '-'}
      };

      // when
      letterCollector.findPathEnd(path);

      // then
   }

   @Test
   public void findPathDirection_DirectionRightOnlyPossible_DirectionRightIsReturned() throws Exception {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(0, 0);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, null);

      //then
      assertThat(pathDirection, is(Direction.RIGHT));
   }

   @Test
   public void findPathDirection_DirectionLeftOnlyPossible_DirectionLeftIsReturned() throws Exception {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(6, 8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.DOWN);

      //then
      assertThat(pathDirection, is(Direction.LEFT));
   }

   @Test
   public void findPathDirection_DirectionDownOnlyPossible_DirectionDownIsReturned() throws Exception {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(0, 8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.RIGHT);

      //then
      assertThat(pathDirection, is(Direction.DOWN));
   }

   @Test
   public void findPathDirection_DirectionUpOnlyPossible_DirectionUpIsReturned() throws Exception {
      //given
      char[][] path = {
            {'+', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(2, 0);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, null);

      //then
      assertThat(pathDirection, is(Direction.UP));
   }

   @Test(expected = UnclearDirectionException.class)
   public void findPathDirection_currentPositionIsOnTCross_UnclearDirectionExceptionIsThrown() throws Exception {
      //given
      char[][] path = {
            {'+', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', '-', '-', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(3, 8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.DOWN);

      //then
   }

   @Test
   public void findPathDirection_LeftAndRightDirectionPossibleWithCurrentDirectionRight_DirectionRightIsReturned() throws Exception {
      //given
      char[][] path = {
            {'+', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(0, 4);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.RIGHT);

      //then
      assertThat(pathDirection, is(Direction.RIGHT));
   }

   @Test
   public void findPathDirection_UpAndDownDirectionPossibleWithCurrentDirectionDown_DirectionDownIsReturned() throws Exception {
      //given
      char[][] path = {
            {'+', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      Point currentPosition = new Point(3, 8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.DOWN);

      //then
      assertThat(pathDirection, is(Direction.DOWN));
   }

   @Test
   public void findPathDirection_TightSpace_KeepTheCurrentDirection() throws Exception {
      //given
      char[][] path = {
            {' ', '+', '-', 'B', '-', '+', ' ', ' ', ' '},
            {' ', '|', ' ', ' ', '+', 'C', '-', '+', ' '},
            {'@', 'A', '+', ' ', '+', '+', ' ', 'D', ' '},
            {' ', '+', '+', ' ', ' ', ' ', ' ', 'x', ' '},
      };
      Point currentPosition = new Point(2, 0);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.RIGHT);

      //then
      assertThat(pathDirection, is(Direction.RIGHT));
   }

   @Test
   public void findPathDirection_Intersection_KeepTheCurrentDirection() throws Exception {
      //given
      char[][] path = {
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'|', ' ', '+', '-', 'C', '-', '-', '+', ' ', ' '},
            {'A', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' '},
            {'+', '-', '-', '-', 'B', '-', '-', '+', ' ', ' '},
            {' ', ' ', '|', ' ', ' ', '-', '-', '-', ' ', 'x'},
            {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', '+', '-', '-', '-', 'D', '-', '-', '+'},
      };
      Point currentPosition = new Point(3, 2);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.RIGHT);

      //then
      assertThat(pathDirection, is(Direction.RIGHT));
   }

   @Test
   public void isObstacle_GivenCharacterIsNotAnObstacle_ReturnsFalse() {
      //given
      char nonObstacleChar = '-';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(nonObstacleChar);

      //then
      assertThat(obstacle, is(false));
   }

   @Test
   public void isObstacle_GivenCharacterIsLetter_ReturnsTrue() {
      //given
      char obstacleChar = 'C';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(obstacleChar);

      //then
      assertThat(obstacle, is(true));
   }

   @Test
   public void isObstacle_GivenCharacterIsFirstPossibleLetter_ReturnsTrue() {
      //given
      char obstacleChar = 'A';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(obstacleChar);

      //then
      assertThat(obstacle, is(true));
   }

   @Test
   public void isObstacle_GivenCharacterIsLastPossibleLetter_ReturnsTrue() {
      //given
      char obstacleChar = 'Z';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(obstacleChar);

      //then
      assertThat(obstacle, is(true));
   }

   @Test
   public void isObstacle_GivenCharacterIsPathEnd_ReturnsTrue() {
      //given
      char obstacleChar = 'x';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(obstacleChar);

      //then
      assertThat(obstacle, is(true));
   }

   @Test
   public void isObstacle_GivenCharacterIsCornerSymbol_ReturnsTrue() {
      //given
      char obstacleChar = '+';
      LetterCollector letterCollector = new LetterCollector();

      //when
      boolean obstacle = letterCollector.isObstacle(obstacleChar);

      //then
      assertThat(obstacle, is(true));
   }

   @Test
   public void moveAndCollectUntilObstacle_MovingRight_StoppedAtObstacleAndCollectedPathPiecesUntilObstacle() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      Point currentPosition = new Point(0, 0);
      Direction currentDirection = Direction.RIGHT;
      StringBuilder pathAsCharacters = new StringBuilder();

      Point firstObstacleCoordinates = new Point(0, 4);

      //when
      letterCollector.moveAndCollectUntilObstacle(path, currentPosition, currentDirection, pathAsCharacters);

      //then
      assertThat(currentPosition.x, is(firstObstacleCoordinates.x));
      assertThat(currentPosition.y, is(firstObstacleCoordinates.y));
      assertThat(pathAsCharacters.toString(), equalTo("---"));
   }

   @Test
   public void moveAndCollectUntilObstacle_MovingLeft_StoppedAtObstacleAndCollectedPathPiecesUntilObstacle() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      Point currentPosition = new Point(6, 8);
      Direction currentDirection = Direction.LEFT;
      StringBuilder pathAsCharacters = new StringBuilder("@---A---+||C||+");

      Point firstObstacleCoordinates = new Point(6, 2);

      //when
      letterCollector.moveAndCollectUntilObstacle(path, currentPosition, currentDirection, pathAsCharacters);

      //then
      assertThat(currentPosition.x, is(firstObstacleCoordinates.x));
      assertThat(currentPosition.y, is(firstObstacleCoordinates.y));
      assertThat(pathAsCharacters.toString(), equalTo("@---A---+||C||+-----"));
   }

   @Test
   public void moveAndCollectUntilObstacle_MovingUp_StoppedAtObstacleAndCollectedPathPiecesUntilObstacle() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      Point currentPosition = new Point(3, 8);
      Direction currentDirection = Direction.UP;
      StringBuilder pathAsCharacters = new StringBuilder();

      Point firstObstacleCoordinates = new Point(0, 8);

      //when
      letterCollector.moveAndCollectUntilObstacle(path, currentPosition, currentDirection, pathAsCharacters);

      //then
      assertThat(currentPosition.x, is(firstObstacleCoordinates.x));
      assertThat(currentPosition.y, is(firstObstacleCoordinates.y));
      assertThat(pathAsCharacters.toString(), equalTo("||"));
   }

   @Test
   public void moveAndCollectUntilObstacle_MovingDown_StoppedAtObstacleAndCollectedPathPiecesUntilObstacle() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      Point currentPosition = new Point(3, 8);
      Direction currentDirection = Direction.DOWN;
      StringBuilder pathAsCharacters = new StringBuilder("@---A---+");

      Point firstObstacleCoordinates = new Point(6, 8);

      //when
      letterCollector.moveAndCollectUntilObstacle(path, currentPosition, currentDirection, pathAsCharacters);

      //then
      assertThat(currentPosition.x, is(firstObstacleCoordinates.x));
      assertThat(currentPosition.y, is(firstObstacleCoordinates.y));
      assertThat(pathAsCharacters.toString(), equalTo("@---A---+||"));
   }


   @Test
   public void collectIfLetterAndUnique_NoLetterAtCurrentPosition_NothingIsCollected() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String previouslyCollectedLetters = "AC";
      StringBuilder collectedLetters = new StringBuilder(previouslyCollectedLetters);
      Point currentPosition = new Point(6, 8);

      //when
      letterCollector.collectIfLetterAndUnique(path, currentPosition, collectedLetters);

      //then
      assertThat(collectedLetters.toString(), equalTo(previouslyCollectedLetters));
   }

   @Test
   public void collectIfLetterAndUnique_NotAlreadyCollectedLetterAtCurrentPosition_CorrectLetterIsCollected() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String previouslyCollectedLetters = "AC";
      StringBuilder collectedLetters = new StringBuilder(previouslyCollectedLetters);
      Point currentPosition = new Point(6, 2);

      //when
      letterCollector.collectIfLetterAndUnique(path, currentPosition, collectedLetters);

      //then
      assertThat(collectedLetters.toString(), equalTo(previouslyCollectedLetters + 'B'));
   }

   @Test
   public void collectIfLetterAndUnique_AlreadyCollectedLetterAtCurrentPosition_NothingIsCollected() {
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '-', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String previouslyCollectedLetters = "AC";
      StringBuilder collectedLetters = new StringBuilder(previouslyCollectedLetters);
      Point currentPosition = new Point(6, 2);

      //when
      letterCollector.collectIfLetterAndUnique(path, currentPosition, collectedLetters);

      //then
      assertThat(collectedLetters.toString(), equalTo(previouslyCollectedLetters + 'B'));
   }

   // **********EXAMPLES FROM GIT!*********** //

   //VALID MAPS//
   //EXAMPLE1
   @Test
   public void resolvePath_PathExampleOne_RightLettersAreCollectedAndRightPathTraveled() throws Exception{
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String expectedPathAsCharactersResult = "@---A---+|C|+---+|+-B-x";
      String expectedCollectedLettersResult = "ACB";


      //when
      ResolvedPath resolvedPath = letterCollector.resolvePath(path);

      //then
      assertThat(resolvedPath.getPathAsCharacters(), equalTo(expectedPathAsCharactersResult));
      assertThat(resolvedPath.getCollectedLetters(), equalTo(expectedCollectedLettersResult));
   }

   //EXAMPLE2
   @Test
   public void resolvePath_PathExampleTwo_RightLettersAreCollectedAndRightPathTraveled() throws Exception{
      //given
      char[][] path = {
            {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'|', ' ', '+', '-', 'C', '-', '-', '+', ' ', ' '},
            {'A', ' ', '|', ' ', ' ', ' ', ' ', '|', ' ', ' '},
            {'+', '-', '-', '-', 'B', '-', '-', '+', ' ', ' '},
            {' ', ' ', '|', ' ', ' ', '-', '-', '-', ' ', 'x'},
            {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', '+', '-', '-', '-', 'D', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String expectedPathAsCharactersResult = "@|A+---B--+|+--C-+|-||+---D--+|x";
      String expectedCollectedLettersResult = "ABCD";


      //when
      ResolvedPath resolvedPath = letterCollector.resolvePath(path);

      //then
      assertThat(resolvedPath.getPathAsCharacters(), equalTo(expectedPathAsCharactersResult));
      assertThat(resolvedPath.getCollectedLetters(), equalTo(expectedCollectedLettersResult));
   }

   //EXAMPLE3
   @Test
   public void resolvePath_PathExampleThree_RightLettersAreCollectedAndRightPathTraveled() throws Exception{
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '+', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', 'C'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String expectedPathAsCharactersResult = "@---A---+|||C---+|+-B-x";
      String expectedCollectedLettersResult = "ACB";


      //when
      ResolvedPath resolvedPath = letterCollector.resolvePath(path);

      //then
      assertThat(resolvedPath.getPathAsCharacters(), equalTo(expectedPathAsCharactersResult));
      assertThat(resolvedPath.getCollectedLetters(), equalTo(expectedCollectedLettersResult));
   }

   //EXAMPLE4
   @Test
   public void resolvePath_PathExampleFour_RightLettersAreCollectedAndRightPathTraveled() throws Exception{
      //given
      char[][] path = {
            {' ', ' ', ' ', '+', '-', '-', 'B', '-', '-', '+', ' ', ' '},
            {' ', ' ', ' ', '|', ' ', ' ', ' ', '+', '-', 'C', '-', '+'},
            {'@', '-', '-', 'A', '-', '+', ' ', '|', ' ', '|', ' ', '|'},
            {' ', ' ', ' ', '|', ' ', '|', ' ', '+', '-', '+', ' ', 'D'},
            {' ', ' ', ' ', '+', '-', '+', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x'},
      };
      LetterCollector letterCollector = new LetterCollector();
      String expectedPathAsCharactersResult = "@--A-+|+-+|A|+--B--+C|+-+|+-C-+|D|x";
      String expectedCollectedLettersResult = "ABCD";


      //when
      ResolvedPath resolvedPath = letterCollector.resolvePath(path);

      //then
      assertThat(resolvedPath.getPathAsCharacters(), equalTo(expectedPathAsCharactersResult));
      assertThat(resolvedPath.getCollectedLetters(), equalTo(expectedCollectedLettersResult));
   }
   //EXAMPLE5
   @Test
   public void resolvePath_PathExampleFive_RightLettersAreCollectedAndRightPathTraveled() throws Exception{
      //given
      char[][] path = {
            {' ', '+', '-', 'B', '-', '+', ' ', ' ', ' '},
            {' ', '|', ' ', ' ', '+', 'C', '-', '+', ' '},
            {'@', 'A', '+', ' ', '+', '+', ' ', 'D', ' '},
            {' ', '+', '+', ' ', ' ', ' ', ' ', 'x', ' '},
      };
      LetterCollector letterCollector = new LetterCollector();
      String expectedPathAsCharactersResult = "@A+++A|+-B-+C+++C-+Dx";
      String expectedCollectedLettersResult = "ABCD";


      //when
      ResolvedPath resolvedPath = letterCollector.resolvePath(path);

      //then
      assertThat(resolvedPath.getPathAsCharacters(), equalTo(expectedPathAsCharactersResult));
      assertThat(resolvedPath.getCollectedLetters(), equalTo(expectedCollectedLettersResult));
   }

   // INVALID MAPS //

   //EXAMPLE6
   @Test(expected = NoPathStartException.class)
   public void resolvePath_PathExampleSix_NoPathStartExceptionIsThrown() throws Exception{
      //given
      char[][] path = {
            {' ', ' ', ' ', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();


      //when
      letterCollector.resolvePath(path);

      //then
   }

   //EXAMPLE7
   @Test(expected = NoPathEndException.class)
   public void resolvePath_PathExampleSeven_NoPathEndExceptionIsThrown() throws Exception{
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', 'B', '-', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();


      //when
      letterCollector.resolvePath(path);

      //then
   }

   //EXAMPLE8
   @Test(expected = MultiplePathStartsException.class)
   public void resolvePath_PathExampleEight_MultiplePathStartsExceptionIsThrown() throws Exception{
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '@', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();


      //when
      letterCollector.resolvePath(path);

      //then
   }

   //EXAMPLE9
   @Test(expected = MultiplePathEndsException.class)
   public void resolvePath_PathExampleNine_MultiplePathEndsExceptionIsThrown() throws Exception{
      //given
      char[][] path = {
            {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'x', '-', 'B', 'x', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();


      //when
      letterCollector.resolvePath(path);

      //then
   }

   //EXAMPLE10
   @Test(expected = UnclearDirectionException.class)
   public void resolvePath_PathExampleTen_UnclearDirectionExceptionIsThrown() throws Exception{
      //given
      char[][] path = {
            {' ', ' ', ' ', ' ', ' ', 'x', '-', 'B'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'@', '-', '-', 'A', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', ' ', '+', '+', ' ', ' ', ' ', 'C'},
            {' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', '+', '-', '-', '-', '+'},
      };
      LetterCollector letterCollector = new LetterCollector();


      //when
      letterCollector.resolvePath(path);

      //then
   }
}
