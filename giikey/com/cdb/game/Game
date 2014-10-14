package giikey.com.cdb.game;

import giikey.com.cdb.Main;
import giikey.com.cdb.util.Key;
import giikey.com.cdb.util.Vector;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Game {

	private Ball ball;
	private Player player;
	private ArrayList<Item> items;
	private ArrayList<Ball> balls;
	private static final int countparball = 2 , ballmax = 60;
	public static int count;
	private boolean locus = true;
	private boolean rainbow = true;
	private int rainbowh;
	public static int point;
	private static final float rainbowcycle = 100.0f;
	public static final Color playercolor = new Color(250, 250, 255, 150);
	private static final int pointcycle = 100, superpointcycle = 1000;
	
	public Game(){
		init();
	}
	
	public void init(){
		player = new Player(new Vector(Main.w / 2, Main.h - 40), new Vector());
		ball = new Ball(new Vector(Main.w / 2, 10));
		items = new ArrayList<Item>();
		balls = new ArrayList<Ball>();
		count = 0;
		point = 0;
	}
	
	public void loop(Graphics g, Key k){
		count ++ ;
		
		if(count % superpointcycle == 0){
			items.add(Item.superpoint(ball.getXY(), ball.getR()));
		}else if(count % pointcycle == 0){
			items.add(Item.point(ball.getXY(), ball.getR()));
		}
		
		if(Item.rand(100) == 0){
			items.add(Item.rand(ball.getXY(), ball.getR()));
		}
		
		ball.loop();
		player.loop(k);
		for(int i = 0;i < items.size();i++){
			if(items.get(i).hit(ball, player)){
				items.remove(i);
				i--;
			}
		}
		ball.hit(player);
		
		if(k.q){
			locus = !locus;
			k.q = Key.OFF;
		}
		if(k.r){
			rainbow = !rainbow;
			k.r = Key.OFF;
		}
		if(rainbow){
			rainbowh++;
			if(rainbowh > rainbowcycle){
				rainbowh = 0;
			}
		}
		
		if(count % countparball == 0){
			if(locus){
				balls.add(new Ball(ball.getXY(), rainbow ? Color.getHSBColor(rainbowh / rainbowcycle, 1.0f, 1.0f) : ball.getColor(), ball.getR()));
				if(balls.size() > ballmax){
					balls.remove(0);
				}
			}else{
				if(balls.size() > 0){
					balls.remove(0);
				}
			}
		}
		
		for(int i = 0;i < items.size();i++){
			items.get(i).draw(g);
		}
		
		for(int i = 0;i < balls.size();i++){
			balls.get(i).draw(g, (i) * (200.0 / ballmax));
		}
		
		player.draw(g);
		ball.draw(g, rainbow ? Color.getHSBColor(rainbowh / rainbowcycle, 1.0f, 1.0f) : ball.getColor());

		g.drawString("" + point + "point", 0, 20);
	}
}
