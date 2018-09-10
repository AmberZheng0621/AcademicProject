 package LightsOutPuzzle;

import java.util.Random;


public class LightsOutModel
{

     private int[][] board;  
     public static final int TurnOn = 1;// lights turn on
     public static final int TurnOff = 0;// lights turn off
     private int ROW =5;
     private int COL =5;
     private boolean ManualSetupButton;// check the game's status,
     private boolean isActive;// check the game whether begins or cancel
    

        /**
         * Creates a LightsOutModel with the specified number of rows and columns. 
         * However, if either rows or columns is less than five, throws an IllegalArgumentException.
         */
        public LightsOutModel (int rows, int cols)
        {
            // Make sure parameters are sensible
            if (rows >5|| rows<=0 || cols >5 || cols<=0)
            {
                throw new IllegalArgumentException();
            }

            // Create the board (it will contain all zeroes), initialize the statistics,
           this.ROW=rows;
           this.COL=cols;
            board = new int[rows][cols];
            ManualSetupButton = false;//check the status of the setup Button
            isActive= false;// check whether the game can be played , 
            newGame();
        }

       
        /**
         * Sets up the board to play a new game,
         * "New Game" that randomly creates an initial game position. 
         *  It must be possible to win the game from this position. 
         */
        public void newGame ()//刚开始的时候是赢得状�?，然�?�任�?几个数字，点亮他们�?�?�左�?�的�?�， 确�?这�?情况是会赢的
        {
            int num=0;
            
            Random Ran=new Random();//random number
            isActive = true;
           while(num <3) {
              int ranr= Ran.nextInt(ROW);
              int ranc=Ran.nextInt(COL);
            
             MakeLightsOn(ranr,ranc); 
               num++; 
              }
          ManualSetupButton= false;
              
           }// crate three lights which status are TurnOn
  /**
   * this method is to make sure which button is clicked, if the manual setup, it will enter into the "create mode"
   * else the mode will be the random new game 
   * @param row
   * @param col
   * @return
   */
        public int MakeLightsOn (int row, int col)
        {
            {
                
                if (row < 0 || col < 0 || row >= ROW || col >= COL)
                {
                    throw new IllegalArgumentException("Invalid row or col");
                }
                // Toggles a single light if the game is in manual setup mode
                if (ManualSetupButton)
                {
                    if(board[row][col]==TurnOn) {
                        board[row][col]=TurnOff;
                    }
                    else {
                        board[row][col]=TurnOn;
                    }
                   
                    return 0;
                }
                // Ensures that the game is active
                
                if (!isActive)
                {
                   
                    return 0;
                }
                
                     Toggle(row , col );
                     
                       
                
                // Runs if all of the lights are turned off and if it isn't in manual setup mode
                if (checkWin() && !ManualSetupButton)
                {
                    isActive = false;
                  
                    return 1;
                }
                return 0;
            }
            }

        /**
         * check the status of the button, enter button it will return true, else return false
         */
        public boolean isManualSetupMode ()
        {
            return ManualSetupButton;
        }
        
        public void setManualSetupMode (boolean mode)
        {
            ManualSetupButton = mode;
            isActive = !mode;
        }
/**
 * toggle method is to change the clicked light and nearby lights colors
 * @param row
 * @param col
 */


  public void Toggle(int row, int col)
  {
      if (row < 0 || col < 0 || row >= ROW || col >= COL)
      {
          throw new IllegalArgumentException();
      }

    
    if(row!=0&&row!=4&&col!=0&&col!=4) {
        board[row][col]=ChangeMode(row,col);
        board[row-1][col]= ChangeMode(row-1,col);
        board[row+1][col]=ChangeMode(row+1,col);
        board[row][col+1]=ChangeMode(row,col+1);
        board[row][col-1]=ChangeMode(row,col-1);
    }
    else if(row==0&&col!=0&&col!=4) {
        board[row][col]= ChangeMode(row,col);
        board[row+1][col]=ChangeMode(row+1,col);
        board[row][col+1]=ChangeMode(row,col+1);
        board[row][col-1]=ChangeMode(row,col-1);
    }
    else if(row==4&&col!=0&&col!=4) {
        board[row][col]=ChangeMode(row,col);
        board[row-1][col]=ChangeMode(row-1,col);
        board[row][col+1]=ChangeMode(row,col+1);
        board[row][col-1]=ChangeMode(row,col-1);
    }
    else if(row!=4&&row!=0&&col==0) {
        board[row][col]= ChangeMode(row,col);
        board[row-1][col]=ChangeMode(row-1,col);
        board[row+1][col]=ChangeMode(row+1,col);
        board[row][col+1]=ChangeMode(row,col+1);
    }
    else if(row!=4&&row!=0&&col==4) {
        board[row][col]=ChangeMode(row,col);
        board[row-1][col]=ChangeMode(row-1,col);
        board[row+1][col]=ChangeMode(row+1,col);
        board[row][col-1]=ChangeMode(row,col-1);
    }
    else if(row==0&&col==0){
        
            board[row][col]=ChangeMode(row,col);
            board[row+1][col]=ChangeMode(row+1,col);
            board[row][col+1]=ChangeMode(row,col+1);
        
    }
    else if(row==0&&col==4) {
        board[row][col]=ChangeMode(row,col);
        board[row+1][col]=ChangeMode(row+1,col);
        board[row][col-1]=ChangeMode(row,col-1);
    }
    else if(row==4&&col==0) {
        board[row][col]=ChangeMode(row,col);
        board[row-1][col]=ChangeMode(row-1,col);
        board[row][col+1]=ChangeMode(row,col+1);
    }
    else {
        board[row][col]=ChangeMode(row,col);
        board[row-1][col]=ChangeMode(row-1,col);
        board[row][col-1]=ChangeMode(row,col-1);  
    }
        
}
/**
 * this helper method used in the toggle method
 * @param row1
 * @param col1
 * @return
 */
        public int ChangeMode(int row1, int col1) {
            if(board[row1][col1]==TurnOn) {
                return TurnOff;
            }
            else {
                return TurnOn;
            }
        }
      

   /**
    * check when the user win this game
    * @param row
    * @param col
    */
        
     public boolean checkWin() {
         int total =0;
         for(int i=0;i<ROW;++i) {
             for(int j=0;j<COL;j++) {
                 if(board[i][j]==TurnOff)
                 total++;
             }
         }
         if(total==25) {
            return true;// if all the grids are full, it means win
         }
         return false;
     }

       

        /**
         * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
         * throws an IllegalArgumentException. 
         * else, return the status of the specific board
         */
    public int getOccupant (int row, int col)
     {
         if (row < 0 || col < 0 || row >= 5 || col >= 5)
         {
             throw new IllegalArgumentException();
         }
         
         return board[row][col];
     
     }

}
       
  
    

