package com.giikey.cdb.util;

public class Vector {

	private double x, y;
	
	public Vector(){
		x = 0.0;
		y = 0.0;
	}
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector xy){
		x = xy.getXd();
		y = xy.getYd();
	}
	
	public int getX(){
		return (int) Math.round(x);
	}
	
	public int getY(){
		return (int) Math.round(y);
	}
	
	public double getXd(){
		return x;
	}
	
	public double getYd(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void turnX(){
		x = -x;
	}
	
	public void turnY(){
		y = -y;
	}
	
	public void turn(){
		x = -x;
		y = -y;
	}
	
	public void add(double x, double y){
		this.x += (double)x;
		this.y += (double)y;
	}
	
	public void add(Vector xy){
		this.x += xy.getXd();
		this.y += xy.getYd();
	}
	
}
