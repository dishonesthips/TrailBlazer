package trailblazer;

import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener
{
	private Timer time;
	private TrailBlazer tb;
	private ArrayList<ArrayList<Character>> charMap;
	private Player louis;
		
	public GamePlay(int k, TrailBlazer tb)
	{
		this.tb = tb;
		
		loadMap(1);
		louis = new Player(100,100);
		
		setFocusable(true);
		addKeyListener(this);
		
		
		time = new Timer(17, this);
		time.start();
		
		
	    this.addComponentListener( new ComponentAdapter() {
	        public void componentShown( ComponentEvent e ) {
	            GamePlay.this.requestFocusInWindow();
	        }
	    });
	    
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (louis.right)
		{
			louis.moveR();
		}
		if (louis.left)
		{
			louis.moveL();
		}
		if (!louis.left && !louis.right) //if the player is not actively trying to move, apply friction
			louis.friction();
		
		louis.gravity();
		
		louis.checkYCol(charMap);
		louis.checkXCol(charMap);
		
		
		louis.tick();
		repaint();
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		for (int i = 0; i < charMap.size(); i++)
		{
			for (int j = 0; j < charMap.get(i).size(); j++)
			{
				if (charMap.get(i).get(j) != '0')
					g.fillRect(j * 48, i * 48, 48, 48);
			}
		}
		louis.draw(g);
		
	}
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			time.stop();
			tb.removeLevel();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			louis.setLeft(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			louis.setRight(true);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (!louis.inAir)
				louis.jump();
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			louis.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			louis.setRight(false);
		}
	}
	public void keyTyped(KeyEvent e){}
	
	
	public void loadMap(int k)
	{
		charMap = new ArrayList<ArrayList<Character>>();
		try{
			File file = new File("C:/Users/borna/Documents/New folder/TrailBlazer/resources/test.txt");//temporary
			System.out.println(file);
			Scanner input = new Scanner(file);
			while (input.hasNext())
			{
				String l = input.nextLine();
				ArrayList<Character> e = new ArrayList<Character>();
				
				for (int i = 0; i < l.length(); i++)
					e.add(l.charAt(i));

				charMap.add(e);
			}
			input.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(charMap);
	}
}
