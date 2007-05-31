package timestable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;


public class TimesTableFrame
  extends JFrame
{
  // 25 rows * 2 columns per printed page
  private static int QUSTION_PAGE = 50;
  
  private BorderLayout layoutMain = new BorderLayout();

  private JPanel panelCenter = new JPanel();

  private JMenuBar menuBar = new JMenuBar();

  private JMenu menuFile = new JMenu();

  private JMenuItem menuFileExit = new JMenuItem();
  private JMenuItem menuFilePrint = new JMenuItem();
  
  private JLabel statusBar = new JLabel();

  private GridBagLayout gridBagLayout1 = new GridBagLayout();

  private JCheckBox chbOf = new JCheckBox();

  private JCheckBox chbDivide = new JCheckBox();

  private JCheckBox chbMultiply = new JCheckBox();

  private JScrollPane jScrollPane1 = new JScrollPane();

  private JTable tblTimes = new JTable( new TimesTableModel() );

  private JSpinner spNoRows = new JSpinner( );

  private JLabel lblRows = new JLabel();

  private JScrollPane jScrollPane2 = new JScrollPane();

  private JTable tblQuestions = new JTable( new QuestionTableModel() );

  private boolean computation = false;
  private int previousNo = 0;
  private ComputationSymbol compSymbol = new ComputationSymbol();
  
  private Settings prefs = new Settings();
  
  public TimesTableFrame()
  {
    try
    {

      jbInit();
      addCheckBoxListeners();
    
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
  }

  private void jbInit()
    throws Exception
  {
    this.setJMenuBar( menuBar );
    this.getContentPane().setLayout( layoutMain );
    this.setSize( new Dimension(450, 600) );
    panelCenter.setLayout( gridBagLayout1 );
    menuFile.setText( "File" );
    menuFileExit.setText( "Exit" );
    menuFileExit.addActionListener( 
      new ActionListener()
      { 
        public void actionPerformed( ActionEvent ae ) 
        {
          fileExit_ActionPerformed( ae );
        }
      });
    menuFilePrint.setText( "Print" );
    menuFilePrint.addActionListener(
      new ActionListener()
      {
        public void actionPerformed( ActionEvent actionEvent )
        {
          filePrint_ActionPerformed( actionEvent );
        }
      });
    
    spNoRows.setModel( new SpinnerNumberModel( prefs.getNoQuestions().intValue(), 0, 1000, 1));
    
    statusBar.setText( "" );

    chbOf.setText( "Of" );
    
    chbDivide.setText( "Divide" );
    chbMultiply.setText( "Multiply" );
    lblRows.setText( "Rows:" );
    menuFile.add( menuFilePrint );
    menuFile.add( menuFileExit );
    menuBar.add( menuFile );
    
    tblTimes.setEnabled( computation );
    
    panelCenter.add( chbOf,
                     new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER, GridBagConstraints.NONE,
                                             new Insets( 5, 5, 5, 5 ), 0,
                                             0 ) );
    panelCenter.add( chbDivide,
                     new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER, GridBagConstraints.NONE,
                                             new Insets( 5, 5, 5, 5 ), 0,
                                             0 ) );
    panelCenter.add( chbMultiply,
                     new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER, GridBagConstraints.NONE,
                                             new Insets( 5, 5, 5, 5 ), 0,
                                             0 ) );
    panelCenter.add( lblRows,
                     new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                                             GridBagConstraints.NONE,
                                             new Insets( 5, 5, 5, 5 ), 0,
                                             0 ) );
    panelCenter.add( spNoRows,
                     new GridBagConstraints( 4, 0, 1, 1, 1.0, 0.0,
                                             GridBagConstraints.CENTER,
                                             GridBagConstraints.HORIZONTAL,
                                             new Insets( 5, 5, 5, 5 ), 0,
                                             0 ) );

    jScrollPane1.getViewport().add( tblTimes, null );
    panelCenter.add( jScrollPane1,
                     new GridBagConstraints( 0, 1, 5, 1, 1.0, 1.0,
                                             GridBagConstraints.CENTER,
                                             GridBagConstraints.BOTH,
                                             new Insets( 0, 0, 0, 0 ), 0,
                                             0 ) );
    jScrollPane2.getViewport().add( tblQuestions, null );
    panelCenter.add( jScrollPane2,
                     new GridBagConstraints( 0, 3, 5, 1, 1.0, 2.0, GridBagConstraints.CENTER,
                                             GridBagConstraints.BOTH,
                                             new Insets( 0, 0, 0, 0 ), 0,
                                             0 ) );                                         

    this.getContentPane().add( panelCenter, BorderLayout.CENTER );
    this.getContentPane().add( statusBar, BorderLayout.SOUTH );

    tblTimes.addPropertyChangeListener( new PropertyChangeListener() {
      public void propertyChange( PropertyChangeEvent propertyChangeEvent )
      {
        if ( previousNo != ((TimesTableModel)tblTimes.getModel()).numberOfRowsSelected() )
        {
          displayQuestions();
          previousNo = ((TimesTableModel)tblTimes.getModel()).numberOfRowsSelected();
        }
      }
      });
  }

  /**
   * Add action listeners for computation check boxes
   */
  private void addCheckBoxListeners()
  {
    chbOf.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        checkBoxActionPerformed( e );
      }

    } );
    
    chbDivide.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        checkBoxActionPerformed( e );
      }

    } );

    chbMultiply.addActionListener( new ActionListener()
    {
      public void actionPerformed( ActionEvent e )
      {
        checkBoxActionPerformed( e );
      }

    } );
    
  }

  private void checkBoxActionPerformed( ActionEvent e )
  {
    if ( !chbOf.isSelected() && !chbDivide.isSelected() && !chbMultiply.isSelected() )
    {
      computation = false;
    }
    else
    {
      computation = true;
    }
    
    tblTimes.setEnabled( computation );
  }

  void fileExit_ActionPerformed( ActionEvent e )
  {
    // persist the number of questions
    prefs.setNoQuestions( ( Integer ) spNoRows.getModel().getValue() );
    
    System.exit(0);
  }

  void filePrint_ActionPerformed( ActionEvent ae )
  {
    QuestionTableModel model = ((QuestionTableModel)tblQuestions.getModel());
    
    PrinterJob job = PrinterJob.getPrinterJob();
    
    job.setPrintable( ( Printable ) new  TimesQuestionsPrint( model.getData() ) );
    
    boolean doPrint = job.printDialog();
    
    if (doPrint) 
    {
      try
      {
        job.print();
      } 
      catch (PrinterException e) 
      {
       /* The job did not successfully complete */
      }
    }
  }

  /**
   * Display the times table questions after generating.
   */
  private void displayQuestions()
  {
    Collection<String> want = new ArrayList( 3 );
    QuestionTableModel model = ((QuestionTableModel)tblQuestions.getModel());
    TimesTableModel timesModel = (TimesTableModel)tblTimes.getModel();
    Collection<Integer> numbers = timesModel.getSelectedItems();
    

    model.removeAll();
    
    if ( numbers.size() < 1 )
    {
      model.fireTableDataChanged(); 
      return; // only one number selected not ask the questions
    }
    
    getSymbolWants(want);

    int nrows = ((Integer)spNoRows.getModel().getValue()).intValue();
    
    ComputationFactory cpFactory = new ComputationFactory();
  
    int row=0;
    int trys = 0;
    while ( row < nrows && trys < 2000 )
    {
      String symbol = compSymbol.getSymbol( want );
      Computation comp = cpFactory.getFactory( symbol );
      String question = comp.getQuestion( numbers );

      if ( !model.contains( question ) )
      {
        model.setValueAt( question, row, 0 );
        row++;
      }
      trys++;
    }
    
    model.fireTableDataChanged();
  }

  private void getSymbolWants( Collection<String> want )
  {
    if ( chbOf.isSelected() )
    {
      want.add( ComputationSymbol.OF );
    }
    if ( chbDivide.isSelected() )
    {
      want.add( ComputationSymbol.DIVIDE );
    }
    if ( chbMultiply.isSelected() )
    {
      want.add( ComputationSymbol.MULTIPLY );
    }
  }


  private class TimesQuestionsPrint implements Printable
  {
    private Collection<String> questions;
    
    TimesQuestionsPrint( Collection<String> data )
    {
      questions = data;  
    }
    
    public int print( Graphics graphics, PageFormat pf, int page )
    {
      // what is the number of pages we need
      int maxPageNo = getMaxPageNumber( );
      
      if (page >= maxPageNo) 
      { 
        /* We have only one page, and 'page' is zero-based */
        return NO_SUCH_PAGE;
      }

      /* User (0,0) is typically outside the imageable area, so we must
       * translate by the X and Y values in the PageFormat to avoid clipping
       */
      Graphics2D g2d = (Graphics2D)graphics;
      g2d.translate(pf.getImageableX(), pf.getImageableY());

      /* Now we perform our rendering */
      String question[] = questions.toArray( new String[0] );
      int ypos = 50;
      int yinterval = 30;
      int xinterval = 200;
      int xpos = 10;
      int st = page * QUSTION_PAGE;
      int en = ( page + 1 ) * QUSTION_PAGE;
      en = ( en > question.length ) ? question.length : en;
      
      for ( int e = st; e < en; e++ )
      {        
        graphics.drawString(question[e] , xpos, ypos );

        if ( xpos == 10 )
        {
          xpos += xinterval;
        }
        else
        {
          xpos = 10;
          ypos += yinterval;          
        }
      }

      /* tell the caller that this page is part of the printed document */
      return PAGE_EXISTS;
    }

    /**
     * Calculate the number of pages we require to print on
     * @return
     */
    private int getMaxPageNumber( )
    {
      float num = questions.size() / QUSTION_PAGE ;
      
      // add 0.5 so we round up
      return ( int ) Math.round( num + 0.5 );
      
    }
    
    
  }
  
}
