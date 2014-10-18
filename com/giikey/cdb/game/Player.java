package giikey.com.cdb.game;

import giikey.com.cdb.util.Key;
import giikey.com.cdb.util.Vector;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private Vector xy;
	private int width;
	private Vector vector;
	public static final int HEIGHT = 10, SPEED = 2, M = 1, MAX = 6, YMAX = 3, defaultwidth = 150;
	private double speed = SPEED;
	private double ymax, ymin;
	
	public Player(Vector xy, Vector vector){
		this.xy = new Vector(xy);
		this.width = defaultwidth;
		this.vector = new Vector(vector);
		this.ymax = xy.getYd();
		this.ymin = this.ymax - 200;
	}
	
	public void loop(Key k){
		if(k.a){
			this.moveLeft();
		}
		if(k.d){
			this.moveRight();
		}
		if(k.w){
			this.moveUp();
		}
		if(k.s){
			this.moveDown();
		}
		
		if(vector.getX() > MAX){
			vector = new Vector(MAX, 0);
		}
		if(vector.getX() < -MAX){
			vector = new Vector(-MAX, 0);
		}
		if(vector.getY() > YMAX){
			vector = new Vector(0, YMAX);
		}
		if(vector.getY() < -YMAX){
			vector = new Vector(0, -YMAX);
		}
		
		xy.add(vector);
		
		if(xy.getYd() > ymax){
			xy.setY(ymax);
		}
		if(xy.getYd() < ymin){
			xy.setY(ymin);
		}
		
		if(vector.getXd() != 0){
			vector.add(new Vector(-(vector.getXd() / Math.abs(vector.getXd())) * M, 0));
		}
		if(vector.getYd() != 0){
			vector.add(new Vector(0, -(vector.getYd() / Math.abs(vector.getYd())) * M));
		}
		
	}
	
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect(xy.getX() - width / 2, xy.getY(), width, HEIGHT);
		g.setColor(Color.white);
		g.fillRect(xy.getX() - width / 2, xy.getY(), width, HEIGHT / 5);
	}
	
	protected Color getColor(){
		return Game.playercolor;
	}
	
	public Vector getXY(){
		return xy;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Vector getVector(){
		return vector;
	}
	
	public void setXY(Vector xy){
		this.xy = new Vector(xy);
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setVector(Vector vector){
		this.vector = new Vector(vector);
	}
	
	public void moveRight(){
		vector.add(new Vector(speed, 0));
	}
	
	public void moveLeft(){
		vector.add(new Vector(-speed, 0));
	}
	
	public void moveDown(){
		vector.add(new Vector(0, speed));
	}
	
	public void moveUp(){
		vector.add(new Vector(0, -speed));
	}

}
