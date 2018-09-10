package LightsOutPuzzle;
import javax.swing.*;
//import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

import static LightsOutPuzzle.LightsOutView.*;

/**
 * Implements a Connect 4 game with a GUI interface.
 * 
 * @author Joe Zachary �?需�?move indicator
 */
@SuppressWarnings("serial")
public class LightsOutView extends JFrame implements ActionListener
{
    // Some formatting constants
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;
    public final static int ROWS = 5;
    public final static int COLS = 5;
    public final static Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
   
    public final static Color Light_COLOR = Color.pink;
    public final static Color BOARD_COLOR = Color.black;
   
    public final static int BORDER = 1;
    public final static int FONT_SIZE = 20;

    /** The "smarts" of the game **/
    private LightsOutModel model;
    private JButton newButton;
    private JButton quitButton;
   
    /** The portion of the GUI that contains the playing surface **/
    private Board board;
   

    /**
     * Creates and initializes the game.
     */
    public LightsOutView ()
    {
        // Set the title that appears at the top of the window
        setTitle("CS 1410 LightsOut Puzzle");

        // Cause the application to end when the windows is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Give the window its initial dimensions
        setSize(WIDTH, HEIGHT);

        // The main Panel contains everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        setContentPane(mainPanel);

        // The center portion contains the playing board
        model = new LightsOutModel(ROWS,COLS);
      
        board = new Board(model, this);
        board.setBackground(BACKGROUND_COLOR);
;
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        newButton  = new JButton("New Game");
        newButton.addActionListener(this);
        controlPanel.add(newButton);
         quitButton = new  JButton("Enter Manual Setup");
        quitButton.addActionListener(this);
        controlPanel.add(quitButton);
       
        mainPanel.add(board, "Center"); 
       mainPanel.add(controlPanel, "South");
      
        // Refresh the display and we're ready
      
       board.refresh();
       setVisible(true);
       
    }

    
  
     
  
    @Override
    public void actionPerformed (ActionEvent e)
    {
        JButton source = (JButton) e.getSource();
        
        // Checks if the button clicked was the New Game button or the Manual Setup button
        if (source.getText().contains("Manual Setup"))
        {
            if (source.getText().equals("Enter Manual Setup"))
            {
                model.setManualSetupMode(true);
                source.setText("Exit Manual Setup");
                
            }
            else
            {
                model.setManualSetupMode(false);
                source.setText("Enter Manual Setup");
               
            }
        }
        else if (source.getText().contains("New Game"))
        {
            
            model.newGame();
            board.refresh();
            
        }
    }

           
    }


/**
 * The playing surface of the game.
 */
@SuppressWarnings("serial")
class Board extends JPanel implements MouseListener
{
    /** The "smarts" of the game */
    private LightsOutModel model;

    /** The top level GUI for the game */
    private LightsOutView display;

    /**
     * Creates the board portion of the GUI.
     */
    public Board (LightsOutModel model, LightsOutView display)
    {
        // Record the model and the top-level display
        this.model = model;
        this.display = display;

        // Set the background color and the layout
        setBackground(BACKGROUND_COLOR);
        setLayout(new GridLayout(ROWS, COLS));

        // Create and lay out the grid of squares that make up the game.
        for (int i = ROWS - 1; i >= 0; i--)
        {
            for (int j = 0; j < COLS; j++)
            {
                Square s = new Square(i, j);
                s.addMouseListener(this);
                add(s);
            }
        }
    }


    /**
     * Refreshes the display. This should be called whenever something changes in the model.
     */
    public void refresh ()
    {
        // Iterate through all of the squares that make up the grid
        
        Component[] squares = getComponents();
       
        for (Component c : squares)
        {
            Square s = (Square) c;// 因为c是component， square 继承的是JPane, JPanel也是compon的子类， 代表着我们需要把C这个父类
            //转变为square这个子类， 所以需要cast
           // Square s =  c;
             
            // Set the color of the squares appropriately
            int status = 0;
           status=model.getOccupant(s.getRow(), s.getCol());
            
            if (status==LightsOutModel.TurnOn)
  
            {
   
                s.setColor(Light_COLOR);
               
            }
            
            else
            {
                s.setColor(BACKGROUND_COLOR);
               
            }
            
        }

     
        // Ask that this board be repainted
        repaint();
    }

    /**
     * Called whenever a Square is clicked. Notifies the model that a move has been attempted.
     */
    @Override
    public void mouseClicked (MouseEvent e)
    {
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        
        Square s= (Square) e.getSource();
       
       
        int results = model.MakeLightsOn(s.getRow(), s.getCol());
        
        if (results == 1)
        {
            JOptionPane.showMessageDialog(this, " wins!");
        }  
        refresh();
        
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }
    
    }

/**
 * A single square on the board where a move can be made
 */
@SuppressWarnings("serial")
class Square extends JPanel
{
    /** The row within the board of this Square. Rows are numbered from zero; row zero is at the bottom of the board. */
    private int row;

    /** The column within the board of this Square. Columns are numbered from zero; column zero is at the left */
    private int col;

    /** The current Color of this Square */
    private Color color;

    /**
     * Creates a square and records its row and column
     */
    public Square (int row, int col)
    {
        this.row = row;
        this.col = col;
        this.color = BACKGROUND_COLOR;
    }

    /**
     * Returns the row of this Square
     */
    public int getRow ()
    {
        return row;
    }

    /**
     * Returns the column of this Square
     */
    public int getCol ()
    {
        return col;
    }

    
    public void setColor (Color color)
    {
        this.color = color;
      
    }

    /**
     * Paints this MoveIndicator onto g
     */
    @Override
    public void paintComponent (Graphics g)// make it different like C4 Board
    {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(BOARD_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        
        g.fillRect(BORDER, BORDER, getWidth() - 2 * BORDER, getHeight() - 2 * BORDER);
    }

}


    