package com.tianliangedu.job001.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {
	public static List<String> readFileToList(String filepath,boolean isClasspath,String charset) throws Exception{
		
		InputStream is =null;
		//文件读取
		if(isClasspath){
			is = ReadConfigUtil.class.getClassLoader().getResourceAsStream(filepath);
		}else{
			
		}
		InputStreamReader isr = new InputStreamReader(is,charset);
		BufferedReader br = new BufferedReader(isr);
		String line = null;	
		List<String> lineList = new ArrayList<String>();
		while ((line = br.readLine())!=null) {
			lineList.add(line);			
		}
		br.close();
		return lineList;
		
	}
	
	public static void main(String[] args) throws Exception {
		//配置文件路径
		String configPath = "seeds.txt";
		//配置文件的读取模式，是classpath路径还是系统路径
		boolean isClasspath =true;
		
		List<String> lineList = readFileToList(configPath, isClasspath, StaticValue.DEFAULT_ENCODING);
		System.out.println(lineList);
		
	}


}
