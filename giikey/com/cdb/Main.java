package giikey.com.cdb;

import giikey.com.cdb.game.Manage;
import giikey.com.cdb.util.Key;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {
	public JFrame f;
	public BufferStrategy bs;
	private Manage manage;
	public static final int h = 480, w = 640, FONTSIZE = 15;
	public static Key k;
	private boolean stop = false;
	public static final String name = "Crazy Dancing Ball";
//	public static Clip[] clips;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		f = new JFrame();
		manage = new Manage();
		k = new Key();
		
/*		try {
			clips = new Clip[3];
			clips[0] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("1.wav")).getFormat()));
			clips[1] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("2.wav")).getFormat()));
			clips[2] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("3.wav")).getFormat()));
			clips[0].open(AudioSystem.getAudioInputStream(getClass().getResource("1.wav")));
			clips[1].open(AudioSystem.getAudioInputStream(getClass().getResource("2.wav")));
			clips[2].open(AudioSystem.getAudioInputStream(getClass().getResource("3.wav")));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		f.setBackground(new Color(30, 35, 30));
		f.addKeyListener(new KL());
		f.setResizable(false);
		f.setVisible(true);
		Insets insets = f.getInsets();
		f.setSize(w + insets.left + insets.right, h + insets.top + insets.bottom);
		f.setLocationRelativeTo(null);
		f.setIgnoreRepaint(true);
		
		f.createBufferStrategy(2);
		bs=f.getBufferStrategy();
		
		Timer t=new Timer();
		t.schedule(new TT(), 5, 10);
	}
	
	public class TT extends TimerTask{

		@Override
		public void run() {
			Graphics g = bs.getDrawGraphics();
			Insets in = f.getInsets();
			g.translate(in.left, in.top);
			g.setColor(new Color(30, 35, 30));
			g.fillRect(0, 0, w, h);
			if(k.esc){
				if(!stop)
					stop=true;
				else
					stop=false;
				k.offAll();
			}
			if(!stop){
				manage.loop(g, k);
			}
			
			bs.show();
			g.dispose();
		}
		
	}
	
	public class KL extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_W){
				k.w = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_A){
				k.a = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_S){
				k.s = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D){
				k.d = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_UP){
				k.w = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_LEFT){
				k.a = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_DOWN){
				k.s = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
				k.d = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SPACE){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ENTER){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Z){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Q){
				k.q = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_R){
				k.r = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
				k.esc = Key.ON;
			}
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_W){
				k.w = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_A){
				k.a = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_S){
				k.s = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D){
				k.d = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_UP){
				k.w = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_LEFT){
				k.a = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_DOWN){
				k.s = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
				k.d = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SPACE){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ENTER){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Z){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Q){
				k.q = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_R){
				k.r = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
				k.esc = Key.OFF;
			}
		}
		
	}
	
	public static void offKeys(){
		k.offAll();
	}

}
package giikey.com.cdb;

import giikey.com.cdb.game.Manage;
import giikey.com.cdb.util.Key;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {
	public JFrame f;
	public BufferStrategy bs;
	private Manage manage;
	public static final int h = 480, w = 640, FONTSIZE = 15;
	public static Key k;
	private boolean stop = false;
	public static final String name = "Crazy Dancing Ball";
//	public static Clip[] clips;

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		f = new JFrame();
		manage = new Manage();
		k = new Key();
		
/*		try {
			clips = new Clip[3];
			clips[0] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("1.wav")).getFormat()));
			clips[1] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("2.wav")).getFormat()));
			clips[2] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class,AudioSystem.getAudioInputStream(getClass().getResource("3.wav")).getFormat()));
			clips[0].open(AudioSystem.getAudioInputStream(getClass().getResource("1.wav")));
			clips[1].open(AudioSystem.getAudioInputStream(getClass().getResource("2.wav")));
			clips[2].open(AudioSystem.getAudioInputStream(getClass().getResource("3.wav")));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		f.setBackground(new Color(30, 35, 30));
		f.addKeyListener(new KL());
		f.setResizable(false);
		f.setVisible(true);
		Insets insets = f.getInsets();
		f.setSize(w + insets.left + insets.right, h + insets.top + insets.bottom);
		f.setLocationRelativeTo(null);
		f.setIgnoreRepaint(true);
		
		f.createBufferStrategy(2);
		bs=f.getBufferStrategy();
		
		Timer t=new Timer();
		t.schedule(new TT(), 5, 10);
	}
	
	public class TT extends TimerTask{

		@Override
		public void run() {
			Graphics g = bs.getDrawGraphics();
			Insets in = f.getInsets();
			g.translate(in.left, in.top);
			g.setColor(new Color(30, 35, 30));
			g.fillRect(0, 0, w, h);
			if(k.esc){
				if(!stop)
					stop=true;
				else
					stop=false;
				k.offAll();
			}
			if(!stop){
				manage.loop(g, k);
			}
			
			bs.show();
			g.dispose();
		}
		
	}
	
	public class KL extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_W){
				k.w = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_A){
				k.a = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_S){
				k.s = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D){
				k.d = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_UP){
				k.w = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_LEFT){
				k.a = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_DOWN){
				k.s = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
				k.d = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SPACE){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ENTER){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Z){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
				k.space = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Q){
				k.q = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_R){
				k.r = Key.ON;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
				k.esc = Key.ON;
			}
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_W){
				k.w = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_A){
				k.a = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_S){
				k.s = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_D){
				k.d = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_UP){
				k.w = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_LEFT){
				k.a = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_DOWN){
				k.s = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
				k.d = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SPACE){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ENTER){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Z){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT){
				k.space = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_Q){
				k.q = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_R){
				k.r = Key.OFF;
			}
			if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
				k.esc = Key.OFF;
			}
		}
		
	}
	
	public static void offKeys(){
		k.offAll();
	}

}
