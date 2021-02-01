import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.awt.Point;

import org.junit.Test;

import exceptions.MultiplePathEndsException;
import exceptions.MultiplePathStartsException;
import exceptions.NoPathEndException;
import exceptions.NoPathStartException;

public class LetterCollectorTest {
   @Test
   public void findPathStart_OnePathStartCharacter_StartCharacterCoordinatesReturned() throws Exception {
      // given
      LetterCollector letterCollector = new LetterCollector();
      char[][] path = {
            {'-',' ','-'},
            {' ', '@', '-'}
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
            {'-','@','-'},
            {' ', '@', '-'}
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
            {' ', 'x', '-'}
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
            {'-','x','-'},
            {' ', 'x', '-'}
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
}
