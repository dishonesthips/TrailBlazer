package trailblazer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class TrailBlazer extends JFrame  
{
    private JPanel cardPanel;
    private JPanel mainMenu, levelSelect, gamePlay;
    private CardLayout cardLayout = new CardLayout();

    public TrailBlazer()
    {
    	super();
    	
    	cardPanel = new JPanel();
    	cardPanel.setLayout(cardLayout);
    	
    	mainMenu = new MainMenu(this);
        cardPanel.add(mainMenu, "main");
        
    	//gamePlay = new GamePlay(1);
    	//cardPanel.add(gamePlay, "2");
    	
        add(cardPanel);
    }
    
    public  void newLevel(int k)
    {
    	gamePlay = new GamePlay(k, this);
    	cardPanel.add(gamePlay, "2");
    }
    
    
    public  void removeLevel()
    {
    	cardLayout.removeLayoutComponent(gamePlay);
    }
    
    
	public  void changeCard(String k)
	{
		cardLayout.show(cardPanel, k);
	}
	
	
    public static void main(String args[])
    {
        TrailBlazer app = new TrailBlazer();
    	
        app.setSize(1030,604);
        app.setLayout(new CardLayout());

        app.setVisible(true);
        app.setResizable(false);
        app.setTitle("TrailBlazer");
        app.setLocationRelativeTo(null); 
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}