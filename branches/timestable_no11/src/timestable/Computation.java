package timestable;

import java.util.Collection;

public interface Computation
{
  String getQuestion( Collection<Integer> want, boolean underScore );
}
