package timestable;

import java.util.Collection;
import java.util.Random;

public class OfComputation extends BaseComputation implements Computation
{
  private Random answerRan = new Random();
  private Random wantRan = new Random();

  public String getQuestion( Collection want, boolean underscore )
  {
    int answer = answerRan.nextInt( 11 );
    int wanted = wantRan.nextInt( want.size() );
    Integer wantInt = getWantedNumber( want, wanted );
    
    StringBuilder buf = new StringBuilder();
    
    if ( underscore )
    {
      buf.append( "__");
    }
    else
    {
      buf.append( "1/");
      buf.append( wantInt );
    }
    buf.append( " of " );    
    buf.append( wantInt.intValue() * answer );
    buf.append( " = ");
    if ( underscore )
    {
      buf.append( answer );
    }
    
    return buf.toString();
  }
}
