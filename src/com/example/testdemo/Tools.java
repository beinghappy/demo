package com.example.testdemo;

public class Tools {
	/**
	 * add space
	 * @param res
	 * @return
	 */
	public static String formatTo19(String res){
		if(res.length()==16){
			return null;
		}else{
			return res;
		}
	}
	
	public static String formatTo19(char[] tmp){
		String res = new String(tmp);
		if(res.length()==16){
			return null;
		}else{
			return res;
		}
	}
	
	/**
	 * delete space 
	 * @param res
	 * @return
	 */
	public static String formatToTrim(String res){
		if(res.length()==16){
			return null;
		}else{
			return res;
		}
	}
	
	public static String formatToTrim(char[]  tmp){
		String res = new String(tmp);
		if(res.length()==16){
			return null;
		}else{
			return res;
		}
	}

}
