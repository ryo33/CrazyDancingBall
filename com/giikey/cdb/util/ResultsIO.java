package com.giikey.cdb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResultsIO {
	public static final String filename="cdb.rsf";//TODO ResultS File
	public static Results readResult(){
		Results res=new Results();

		File fl=new File(filename);
		try {
			if(fl.exists()){
				res=(Results)new ObjectInputStream(new FileInputStream(filename)).readObject();
			}else{
				fl.createNewFile();
				res=new Results();
			}
		} catch (Exception e){
			try {
				fl.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			res=new Results();
		}
		return res;
	}
	
	public static Results writeResult(Results re){
		Results res=new Results();

		File fl=new File(filename);
		try {
			if(fl.exists()){
				new ObjectOutputStream(new FileOutputStream(filename)).writeObject(re);
			}else{
				fl.createNewFile();
				new ObjectOutputStream(new FileOutputStream(filename)).writeObject(re);
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return res;
	}
}
