package giikey.com.cdb.util;

public class Key {
	public boolean w, a, s, d, q, r, space, esc;
	public static final boolean OFF = false, ON = true;
	public Key(){
		offAll();
	}
	public void offAll(){
		w = a = s = d = q = r = esc = OFF;
	}
}
