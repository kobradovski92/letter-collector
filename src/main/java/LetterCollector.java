import java.awt.Point;

import exceptions.MultiplePathStartsException;
import exceptions.NoPathStartException;

public class LetterCollector {
   public Point findPathStart(char[][] path) throws Exception {
      Point pathStart = new Point(-1,-1);
      for (int i = 0; i < path.length; i++) {
         for (int j = 0; j < path[i].length; j++) {
            if (path[i][j] == '@') {
               if (pathStart.getX() == -1) {
                  pathStart.setLocation(i, j);
               } else {
                  throw new MultiplePathStartsException();
               }
            }
         }
      }
      if (pathStart.getX() != -1) {
         return pathStart;
      } else {
         throw new NoPathStartException();
      }
   }
}
