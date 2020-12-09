package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AJAXUtils {
  /**
   * 输出响应到客户端
   * **/
	public static void printString(HttpServletResponse response,String s){
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
