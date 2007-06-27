package timestable;

import java.util.Collection;
import java.util.Random;

public class OfComputation extends BaseComputation implements Computation
{
  private Random answerRan = new Random();
  private Random wantRan = new Random();

  public String getQuestion( Collection want )
  {
    int answer = answerRan.nextInt( 11 );
    int wanted = wantRan.nextInt( want.size() );
    Integer wantInt = getWantedNumber( want, wanted );
    
    StringBuilder buf = new StringBuilder();
    
    buf.append( "1/");
    buf.append( wantInt );
    buf.append( " of " );    
    buf.append( wantInt.intValue() * answer );
    buf.append( " = ");
    
    return buf.toString();
  }
}
