package giikey.com.cdb.util;

import giikey.com.cdb.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Results implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1609761650300323526L;
	private String[] names;
	private int[] points;
	private int plays;
	private int sum;
	private String today;
	private int todayplays;
	private int todaysum;
	
	private static final String ANONYMOUS = "Player";
	
	public Results(){
		names=new String[10];
		for(int i=0;i<names.length;i++){
			names[i]=new String(ANONYMOUS);
		}
		points=new int[10];
		today = new String();
	}
	@SuppressWarnings("unused")
	public void add(int point){
		JOptionPane.showMessageDialog(null, "Your Score: " + point, "SCORE", JOptionPane.PLAIN_MESSAGE);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(cal.getTime());
		if(!this.today.equals(today)){
			this.today = today;
			todayplays = 0;
			todaysum = 0;
		}
		plays++;
		sum += point;
		todayplays++;
		todaysum += point;
		for(int p=0;p<9;p++){
			if(point>=points[p] && point != 0){
				String name;
				if((name=(String)JOptionPane.showInputDialog(null,"Enter Your Name","High Score",JOptionPane.PLAIN_MESSAGE,null,null,"Player")) == null || name.length() == 0){
					name=new String(ANONYMOUS);
				}
				if(name==null){
					name=new String(ANONYMOUS);
				}
				for(int i=9;i>p;i--){
					names[i]=new String(names[i-1]);
					points[i]=points[i-1];
				}
				names[p]=new String(name);
				points[p]=point;
				return;
			}
		}
		if(point>=points[9] && point != 0){
			String name = "";
			if((name=(String) JOptionPane.showInputDialog(null,"Enter Your Name","High Score",JOptionPane.PLAIN_MESSAGE,null,null,"Player")) == null || name.length() == 0){
				name=new String(ANONYMOUS);
			}
			names[9]=new String(name);
			points[9]=point;
		}
	}
	public void draw(Vector xy,Graphics g){
		int y = 0;
		g.setFont(new Font("Dialog", Font.PLAIN, Main.FONTSIZE));
		g.setColor(Color.white);
		for(int i=0;i<points.length;i++){
			g.drawString(names[i], xy.getX(), xy.getY() + y);
			g.drawString(Integer.toString(points[i]), (int) (xy.getX()+200-g.getFontMetrics().getStringBounds(Integer.toString(points[i]), g).getWidth()), xy.getY()+y);
			y+=25;
		}
		y+=25;
		g.drawString("PLAY TIMES", xy.getX(), xy.getY() + y);
		g.drawString(Integer.toString(plays), (int) (xy.getX()+200-g.getFontMetrics().getStringBounds(Integer.toString(plays), g).getWidth()), xy.getY()+y);
		y+=25;
		g.drawString("AVERAGE", xy.getX(), xy.getY() + y);
		g.drawString(Integer.toString(sum / plays), (int) (xy.getX()+200-g.getFontMetrics().getStringBounds(Integer.toString(sum / plays), g).getWidth()), xy.getY()+y);
		y+=25;
		g.drawString("PLAY TIMES(TODAY)", xy.getX(), xy.getY() + y);
		g.drawString(Integer.toString(todayplays), (int) (xy.getX()+200-g.getFontMetrics().getStringBounds(Integer.toString(todayplays), g).getWidth()), xy.getY()+y);
		y+=25;
		g.drawString("AVERAGE(TODAY)", xy.getX(), xy.getY() + y);
		g.drawString(Integer.toString(todaysum / todayplays), (int) (xy.getX()+200-g.getFontMetrics().getStringBounds(Integer.toString(todaysum / todayplays), g).getWidth()), xy.getY()+y);
	}
}
