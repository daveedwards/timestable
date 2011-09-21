package timestable;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TimesTableModel extends AbstractTableModel
{
  private String[] columnNames;
  private Integer[] numbers; 
  private Boolean[] selectData ;
  
  public TimesTableModel()
  {
    numbers = new Integer[] { new Integer(2),
                              new Integer(3),new Integer(4),new Integer(5),
                              new Integer(6),new Integer(7),new Integer(8),
                              new Integer(9),new Integer(10),new Integer(11),
                              new Integer(12)
                              }; 
    columnNames = new String[] { "Table Number", "Select" };
    selectData = new Boolean[ numbers.length ];
  }
  
  public String getColumnName( int col)
  {
    return columnNames[ col ];
  }

  public boolean isCellEditable( int row, int col )
  {
    if ( col == 1 )
      return true;
      
    return false;
  }

  public int getRowCount()
  {
    if ( numbers != null )
      return numbers.length;
    
    return 0;
  }
  
  public Object getValueAt( int row, int col )
  {
    if ( row > numbers.length )
      return null;
  
    if ( col == 0 )
      return numbers[ row ];
  
    if ( col == 1 )
      return selectData[ row ];
      
    return null;
  }

  public void setValueAt( Object obj, int row, int col )
  {
    if ( col == 1 )
    {
      selectData[ row ] = ( Boolean ) obj;
    }
  }
  
  public int getColumnCount()
  {
    return columnNames.length;
  }
  
  public Class getColumnClass( int col )
  {
    if ( col == 1)
    {
      return Boolean.class;
    }
    
    return Integer.class;
  }

  int numberOfRowsSelected()
  {
    int no = 0;
      
    for ( Boolean selected: selectData )
    {
      if ( selected != null && selected )
        no++;
    }
    
    return no;
  }

  Collection<Integer> getSelectedItems()
  {
    List<Integer> selected = new ArrayList();
    
    for ( int d = 0; d < selectData.length; d++ )
    {
      if ( selectData[d] != null && selectData[d] )
      {
        selected.add( numbers[d] );
      }
    }
    
    return selected;
  }
}
