package LightsOutPuzzle;

 import javax.swing.SwingUtilities;


    /**
     * Launches a game of Connect 4.
     * 
     * @author XI ZHENG copy from Connect4
     */
    public class LightsOut
    {
        public static void main (String[] args)
        {
            SwingUtilities.invokeLater( () -> new LightsOutView());
        }
    }

