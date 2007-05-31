package timestable;

import java.util.Collection;

public class BaseComputation
{
  protected Integer getWantedNumber( Collection want, int wantNum )
  {
    Integer[] wantList = ( Integer[] ) want.toArray( new Integer[0] );
    return wantList[wantNum];
  }
}
