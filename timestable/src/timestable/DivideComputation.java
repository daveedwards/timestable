package timestable;

import java.util.Collection;
import java.util.Random;

public class DivideComputation extends BaseComputation implements Computation
{
  private Random answerRan = new Random();
  private Random wantRan = new Random();
  private static char [] divide = {'\u223B'};
  
  public String getQuestion( Collection want )
  {
    int answer = answerRan.nextInt( 11 );
    int wanted = wantRan.nextInt( want.size() );
    Integer wantInt = getWantedNumber( want, wanted );
    
    StringBuilder buf1 = new StringBuilder();
    buf1.append( wantInt.intValue() * answer );
    buf1.append( ' ' );
    buf1.append( divide );
    buf1.append( ' ' );
    buf1.append( wantInt );
    buf1.append( " = ");
    
    
    return buf1.toString();
  }
}
