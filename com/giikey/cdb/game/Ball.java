package giikey.com.cdb.game;

import giikey.com.cdb.Main;
import giikey.com.cdb.util.ResultsIO;
import giikey.com.cdb.util.Vector;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball {

	private Vector xy;
	private int r;
	private Vector vector;
	private Color c;
	public static final int sin = 30, MAX = 15, ydefault = 4, xdefault = 4, rdefault = 50;
	
	public Ball(Vector xy){
		this.xy = new Vector(xy);
		this.r = rdefault;
		this.vector = new Vector(xdefault, ydefault);
		this.c = Game.playercolor;
	}
	
	public Ball(Vector xy, Color c, int r) {
		this.xy = new Vector(xy);
		this.vector = new Vector();
		this.r = r;
		this.c = c;
	}

	public void loop(){
		xy.add(vector);
	}
	
	public void draw(Graphics g, Color c){
		g.setColor(c);
		g.fillOval(xy.getX()-r / 2, xy.getY()-r / 2, r , r);
		g.setColor(Color.white);
		g.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
		g.drawString("" + (20 * Math.sqrt(vector.getXd() * vector.getXd() + vector.getYd() * vector.getYd())) + "px/s", 0, 40);
	}

	public void draw(Graphics g, double d) {
		g.setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), (int)d));
		g.fillOval(xy.getX()-r / 2, xy.getY()-r / 2, r , r);
	}
	
	protected Color getColor(){
		return c;
	}
	
	public Vector getXY(){
		return xy;
	}
	
	public int getR(){
		return r;
	}
	
	public Vector getVector(){
		return vector;
	}
	
	public void setXY(Vector xy){
		this.xy = new Vector(xy);
	}
	
	public void setR(int r){
		this.r = r;
	}
	
	public void setVector(Vector vector){
		this.vector = new Vector(vector);
	}
	
	public void hit(Player player){
		if(xy.getXd() + r / 2 + r / 5 > player.getXY().getXd() - player.getWidth() / 2 && xy.getXd() - r / 2 - r / 5 < player.getXY().getXd() + player.getWidth() / 2 ){
			if(xy.getYd() - r / 2< player.getXY().getYd() + player.getWidth() && xy.getYd() + r / 2> player.getXY().getYd()){
				if(xy.getY() < player.getXY().getY() && vector.getYd() > 0){
					xy.setY(player.getXY().getYd() - r / 2);
					vector.setY(- Math.abs(vector.getYd()));
					vector.add(new Vector(((xy.getXd() - player.getXY().getXd()) / sin) - player.getVector().getXd(), player.getVector().getYd() / 2));
				}
			}
		}
		if(xy.getY() - r / 2 < 0){
			xy.setY(r / 2);
			vector.setY(Math.abs(vector.getYd()));
		}
		if(xy.getX() - r / 2 < 0){
			xy.setX(r / 2);
			vector.setX(Math.abs(vector.getXd()));
		}
		if(xy.getX() + r / 2 > Main.w){
			xy.setX(Main.w - r / 2);
			vector.setX(- Math.abs(vector.getXd()));
		}
		if(xy.getY() + r / 2 > Main.h){
			xy.setY(Main.h - r / 2);
			Manage.state = Manage.RESULT;
			Manage.res.add(Game.point);
			ResultsIO.writeResult(Manage.res);
			Main.offKeys();
		}
		
		if(vector.getX() < - MAX){
			vector.setX(-MAX);
		}
		
		if(vector.getX() > MAX){
			vector.setX(MAX);
		}
	}

}
