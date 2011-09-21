package timestable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class QuestionTableModel extends AbstractTableModel
{
  private List<String> data = new ArrayList<String>();
  private String[] columnNames;

  public QuestionTableModel()
  {
    columnNames = new String[] { "Times Table Questions" };
  }
  
  public int getRowCount()
  {
    if ( data == null )
      return 0;
    
    return data.size();
  }

  public String getColumnName( int col)
  {
    return columnNames[ col ];
  }
    
  public int getColumnCount()
  {
    return 1;
  }

  public Object getValueAt( int i, int i1 )
  {
    if ( data != null && i < data.size() )
    {
      String value = ( String ) data.get( i );
      if ( value == null )
      {
        return "";
      }
      
      return value;
    }
    
    return "";
  }
  
  public void setValueAt( Object obj, int row, int col )
  {
    data.add( row, (String)obj );
  }
  
  boolean contains( Object obj )
  {
    return data.contains( obj );
  }

  void removeAll()
  {
    data = new ArrayList();
  }
  
  Collection<String> getData()
  {
    return data;
  }
}
