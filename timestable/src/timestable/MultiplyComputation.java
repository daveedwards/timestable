package timestable;

import java.util.Collection;
import java.util.Random;

public class MultiplyComputation extends BaseComputation
  implements Computation
{
  private Random position = new Random();
  private Random selection = new Random();
  private Random wanted = new Random();
  
  public String getQuestion( Collection want )
  {
    int place = position.nextInt( 2 ) + 1;
    int select = selection.nextInt( 11 );
    int wantNum = wanted.nextInt( want.size() );
    Integer wantInt = getWantedNumber( want, wantNum );
    StringBuilder buf = new StringBuilder();
    
    if ( place == 1 )
    {
      buf.append( wantInt );
      buf.append( " x ");
      buf.append( select );
      buf.append( " = ");
    }
    else if ( place == 2 )
    {
      buf.append( select );
      buf.append( " x ");
      buf.append( wantInt );
      buf.append( " = ");
    }
    
    return buf.toString();
  }

}
