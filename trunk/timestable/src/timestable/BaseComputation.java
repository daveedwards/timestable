package timestable;

import java.util.Collection;

 abstract class BaseComputation
{
  protected final static int MAXNUM = 13;
  protected Integer getWantedNumber( Collection<Integer> want, int wantNum )
  {
    Integer[] wantList = ( Integer[] ) want.toArray( new Integer[0] );
    return wantList[wantNum];
  }
}
