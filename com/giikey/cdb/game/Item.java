package com.giikey.cdb.game;

import java.awt.Color;
import java.awt.Graphics;

import com.giikey.cdb.Main;
import com.giikey.cdb.util.Vector;

public class Item {

	private Vector xy;
	private int r;
	private int type;
	private final int pointspeed = 10;
	private final int superpointspeed = 8;
	private final int pointring = 20;
	private final Color ring = Color.gray, superring = Color.yellow;
	private final static int itemcount = 7;
	
	public static Item rand(Vector xy, int r){
		Vector re;
		int rre = rand(90) + 10;
		int xx, yy, rr;
		do{
			re = new Vector(rand(Main.w), rand(Main.h-50));
			xx = xy.getX() - re.getX();
			yy = xy.getY() - re.getY();
			rr = r / 2 + rre / 2 + 150;
		}while((xx * xx + yy * yy) <= rr * rr);
		return new Item(re, rre, rand(itemcount));
	}
	
	public static Item point(Vector xy, int r){
		Vector re;
		int rre = rand(90) + 10;
		int xx, yy, rr;
		do{
			re = new Vector(rand(Main.w), rand(Main.h - 100));
			xx = xy.getX() - re.getX();
			yy = xy.getY() - re.getY();
			rr = r + rre + 100;
		}while((xx * xx + yy * yy) <= rr * rr);
		return new Item(re, rre, 100);
	}
	
	public static Item superpoint(Vector xy, int r){
		Vector re;
		int rre = rand(70) + 10;
		int xx, yy, rr;
		do{
			re = new Vector(rand(Main.w), rand(Main.h-50));
			xx = xy.getX() - re.getX();
			yy = xy.getY() - re.getY();
			rr = r + rre + 100;
		}while((xx * xx + yy * yy) <= rr * rr);
		return new Item(re, rre, 101);
	}
	
	public Item(Vector xy, int r, int type){
		this.xy = new Vector(xy);
		this.r = r;
		this.type = type;
	}
	
	public final boolean hit(Ball ball, Player player){
		int xx = ball.getXY().getX() - xy.getX();
		int yy = ball.getXY().getY() - xy.getY();
		int rr = r / 2 + ball.getR() / 2;
		if((xx * xx + yy * yy) <= rr * rr){
			return power(ball, player);
		}else if(type == 100){
			if(Game.count % pointspeed == 0){
				r -= 1;
			}
			if(r == 0){
				r = rand(90) + 10;
				type = rand(itemcount);
			}
		}else if(type == 101){
			if(Game.count % superpointspeed == 0){
				r -= 1;
			}
			if(r == 0){
				r = rand(90) + 10;
				type = rand(itemcount);
			}
		}
		return false;
	}
	
	public final boolean draw(Graphics g){
		int r = this.r;
		if(type == 100){
			g.setColor(new Color(ring.getRed(), ring.getGreen(), ring.getBlue(), 200));
			g.fillOval(xy.getX() - r / 2, xy.getY() - r / 2, r, r);
			r -= pointring;
		}else if(type == 101){
			g.setColor(new Color(superring.getRed(), superring.getGreen(), superring.getBlue(), 200));
			g.fillOval(xy.getX() - r / 2, xy.getY() - r / 2, r, r);
			r -= pointring;
		}
		g.setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), 160));
		g.fillOval(xy.getX() - r / 2, xy.getY() - r / 2, r, r);
		return false;
	}
	
	protected Color getColor(){
		switch(type){
		case 0:
			return Color.yellow;
		case 1:
			return Color.blue;
		case 2:
			return Color.red;
		case 3:
			return Color.green;
		case 4:
			return new Color(255, 173, 255);
		case 5:
			return Color.cyan;
		case 6:
			return Color.magenta;
		case 7:
			return new Color(255, 150, 0);
		case 100:
			return Color.white;
		case 101:
			return Color.white;
		default:
			return Color.white;
		}
	}
	
	protected boolean power(Ball ball, Player player){
		switch(type){
		case 0:
			ball.setVector(new Vector(ball.getVector().getXd(), (ball.getVector().getYd()<0 ? -Ball.ydefault + rand(6) - 3 : +Ball.ydefault + rand(6) - 3)));
			return true;
		case 1:
			ball.setVector(new Vector(ball.getVector().getXd(), -ball.getVector().getYd()));
			break;
		case 2:
			ball.setVector(new Vector(-ball.getVector().getXd(), -ball.getVector().getYd()));
			break;
		case 3:
			ball.setVector(new Vector(0, Ball.ydefault));
			break;
		case 4:
			ball.setR(Ball.rdefault + rand(60) - 30);
			break;
		case 5:
			ball.setVector(new Vector(-ball.getVector().getXd(), ball.getVector().getYd()));
			break;
		case 6:
			player.setWidth(Player.defaultwidth + rand(200)-100);
			break;
		case 7:
			ball.setXY(new Vector(xy.getXd(), xy.getYd())) ;
			break;
		case 100:
			Game.point += 1;
			if(r < pointring){
				Game.point += 2;
			}
/*			Main.clips[3].stop();
			Main.clips[3].setFramePosition(0);
			Main.clips[3].start();*/
			break;
		case 101:
			Game.point += 10;
			if(r < pointring){
				Game.point += 5;
			}
/*			Main.clips[3].stop();
			Main.clips[3].setFramePosition(0);
			Main.clips[3].start();*/
			break;
		default:
			return true;
		}
		return true;
	}
	
	public static int rand(int r){
		return (int)(Math.random() * (r + 1));
	}
	
}
