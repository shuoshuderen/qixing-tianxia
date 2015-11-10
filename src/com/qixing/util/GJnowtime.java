package com.qixing.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GJnowtime {
	public static String gettime(){
		Date date=new Date();
		String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return nowtime;
	}
}
