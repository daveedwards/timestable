package timestable;

import java.util.Collection;
import java.util.Random;

public class ComputationSymbol
{
  public static String OF = "of";
  public static String DIVIDE = "/";
  public static String MULTIPLY = "*";
  public static String DOUBLE = "Double";

  private Random generator = new Random(); 
  
  String getSymbol( Collection<String> want )
  {    
    while ( true )
    {
    
      int no = generator.nextInt( 4 ) + 1;
      
      switch ( no )
      {
        case 1: 
          if ( want.contains( OF ) )
            return OF;
        case 2:
          if ( want.contains( DIVIDE ) )
            return DIVIDE;
        case 3:
          if ( want.contains( MULTIPLY ) )
            return MULTIPLY;
        case 4:
          if ( want.contains( DOUBLE ) )
            return DOUBLE;
      }
    }
    
  }
}
