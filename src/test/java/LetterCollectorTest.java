import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.awt.Point;

import org.junit.Test;

import exceptions.MultiplePathStartsException;
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
}
