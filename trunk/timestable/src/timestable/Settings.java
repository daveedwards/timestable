package timestable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Settings
{
  private static String FILENAME = "TimesTable.txt";
  private static Integer DEFAULTNO = Integer.valueOf( 30 );

  private Integer noQuestions = null;
  
  Integer getNoQuestions()
  {
    if ( noQuestions != null )
      return noQuestions;
      
    File file = getFile();
    
    if ( file == null || !file.canRead() )
      return DEFAULTNO;
      
    FileReader fis = null;
    try
    {
      fis = new FileReader( file );
      
      char [] buf = new char[64];
      
      int pos = fis.read( buf );
      
      try
      {
        String str = new String( buf );
        noQuestions = Integer.valueOf( str.substring( 0, pos ) );
        return noQuestions;
      }
      catch (Exception e)
      {
        e.printStackTrace();
        
        return DEFAULTNO;
      }
      
    }
    catch ( FileNotFoundException e )
    {
      e.printStackTrace();
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    finally
    {

      try
      {
        fis.close();
      }
      catch ( IOException e )
      {
        // nothing
      }
    }

    return DEFAULTNO;
  }

  void setNoQuestions( Integer no )
  {
    File file = getFile();
    
    if ( !file.exists() )
    {
      try
      {
        file.createNewFile();
      }
      catch ( IOException e )
      {
        e.printStackTrace();
        
        return;
      }
    }
    
    
    FileWriter fw = null;
    try
    {
      fw = new FileWriter( file );
      
      String sno = no.toString();
      fw.write( sno, 0, sno.length() );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        fw.close();
      }
      catch ( IOException e )
      {
        e.printStackTrace();
      }
    }
  }
  
  private File getFile()
  {
    StringBuilder path = new StringBuilder( System.getProperty( "user.home" ) );
    
    path.append( File.separator );
    
    path.append( FILENAME );
    
    File file = new File( path.toString() );
        
    return file;
  }
}
