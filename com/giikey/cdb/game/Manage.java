package giikey.com.cdb.game;

import giikey.com.cdb.Main;
import giikey.com.cdb.util.Key;
import giikey.com.cdb.util.Results;
import giikey.com.cdb.util.ResultsIO;
import giikey.com.cdb.util.Vector;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Manage {

	public static int state;
	private Game manage;
	public static final int START = 0, GAME = 1, RESULT = 2;
	public static Results res;
	public boolean resb;
	private int rainbowh;
	private static final float rainbowcycle = 100.0f;
	
	public Manage(){
		state = START;
		manage = new Game();
		res = ResultsIO.readResult();
	}
	
	public void loop(Graphics g, Key k){
		switch(state){
		case START:
			rainbowh++;
			if(rainbowh > rainbowcycle){
				rainbowh = 0;
			}
			g.setColor(Color.getHSBColor(rainbowh / rainbowcycle, 1.0f, 1.0f));
			g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
			drawString(g, Main.name, 50);
			g.setColor(Color.white);
			g.setFont(new Font(Font.SERIF, Font.BOLD, 25));
			drawString(g, "Press <Space> Key to Start", 100);
			drawString(g, "Use <Left> and <Right> Key", 150);
			drawString(g, "Toggle RAINBOW MODE by <R> Key", 200);
			drawString(g, "Toggle LOCUS MODE by <Q> Key", 250);
			drawString(g, "Press <Esc> Key to Pause", 300);
			if(k.space){
				k.offAll();
				state = GAME;
				resb = false;
				manage.init();
				
			}
			break;
		case GAME:
			manage.loop(g, k);
			break;
		case RESULT:
			if(!resb){
				res = ResultsIO.readResult();
				resb = true;
			}
			res.draw(new Vector(10, 30), g);
			if(k.space){
				k.offAll();
				state = GAME;
				resb = false;
				manage.init();
			}
			break;
		}
	}
	
	public void drawString(Graphics g, String s, int y){
		g.drawString(s, (int) (Main.w / 2 - g.getFontMetrics().getStringBounds(s, g).getWidth() / 2), y);
	}
	
}
