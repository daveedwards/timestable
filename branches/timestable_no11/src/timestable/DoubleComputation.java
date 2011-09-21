package timestable;

import java.util.Collection;
import java.util.Random;

public class DoubleComputation
  extends BaseComputation implements Computation
{
  private Random wanted = new Random();

  public DoubleComputation()
  {
  }

  public String getQuestion(Collection<Integer> want, boolean underScore)
  {
    int wantNum = wanted.nextInt( want.size() );
    Integer wantInt = getWantedNumber( want, wantNum );
    StringBuilder buf = new StringBuilder();

    buf.append( "Double " );
    buf.append( wantInt );
    buf.append( " = ");
    
    return buf.toString();
  }
}
