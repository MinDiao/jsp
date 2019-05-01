package com.zw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * @author Administrator
 *
 */
public class ToolUtil {
	public static String getCurrentTime(){
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	public final static String LOGINUSER = "loginUser";
}
