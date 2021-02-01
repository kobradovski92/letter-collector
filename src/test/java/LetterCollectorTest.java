import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.awt.Point;

import org.junit.Test;

import exceptions.MultiplePathEndsException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathEndException;
import exceptions.NoPathStartException;
import exceptions.UnclearDirectionException;

public class LetterCollectorTest {
   @Test
   public void findPathStart_OnePathStartCharacter_StartCharacterCoordinatesReturned() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-',' ','-'},
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
            {'-',LetterCollector.PATH_START_CHARACTER,'-'},
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
            {'-',' ','-'},
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
            {'-',' ','-'},
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
            {'-',LetterCollector.PATH_END_CHARACTER,'-'},
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
            {'-',' ','-'},
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
            {'@','-','-','-','A','-','-','-','+'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
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
            {'@','-','-','-','A','-','-','-','+'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(6,8);
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
            {'@','-','-','-','A','-','-','-','+'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(0,8);
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
            {'|','-','-','-','A','-','-','-','+'},
            {'@',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(1,0);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, null);

      //then
      assertThat(pathDirection, is(Direction.UP));
   }

   @Test(expected = UnclearDirectionException.class)
   public void findPathDirection_CurrentLocationIsOnTCross_UnclearDirectionExceptionIsThrown() throws Exception {
      //given
      char[][] path = {
            {'|','-','-','-','A','-','-','-','+'},
            {'@',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ','-','-','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(3,8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.DOWN);

      //then
   }

   @Test
   public void findPathDirection_LeftAndRightDirectionPossibleWithCurrentDirectionRight_DirectionRightIsReturned() throws Exception {
      //given
      char[][] path = {
            {'|','-','-','-','A','-','-','-','+'},
            {'@',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(0,4);
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
            {'|','-','-','-','A','-','-','-','+'},
            {'@',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','C'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {' ',' ',' ',' ',' ',' ',' ',' ','|'},
            {'x','-','B','-','-','-','-','-','+'},
      };
      Point currentPosition = new Point(3,8);
      LetterCollector letterCollector = new LetterCollector();

      //when
      Direction pathDirection = letterCollector.findPathDirection(path, currentPosition, Direction.DOWN);

      //then
      assertThat(pathDirection, is(Direction.DOWN));
   }
}
