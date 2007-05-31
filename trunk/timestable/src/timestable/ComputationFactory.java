package timestable;

public class ComputationFactory
{
  private Computation divideQuestion = new DivideComputation();
  private Computation ofQuestion = new OfComputation();
  private Computation multiplyQuestion = new MultiplyComputation();

  
  Computation getFactory( String compSymbol )
  {
    if ( compSymbol.equals( ComputationSymbol.DIVIDE )  )
    {
      return divideQuestion;
    }
    if ( compSymbol.equals( ComputationSymbol.OF )  )
    {
      return ofQuestion;
    }
    if ( compSymbol.equals( ComputationSymbol.MULTIPLY )  )
    {
      return multiplyQuestion;
    }
    
    return null;
  }
}
