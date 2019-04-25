package com.tianliangedu.job001.ui;

import java.util.ArrayList;
import java.util.List;

import com.tianliangedu.job001.pojos.UrlTaskPojo;
import com.tianliangedu.job001.schedule.TaskScheduleManager;
import com.tianliangedu.job001.utils.IOUtil;
import com.tianliangedu.job001.utils.StaticValue;

/**
 * 该类为用户接口管理类，包括种子添加接口、种子添加的不同方式和来源
 * @author Lyq
 * @company 
 *
 */
public class UIManager {
	
	//直接给
	public static UrlTaskPojo getRootUrlByDirect() {
		return new UrlTaskPojo(StaticValue.title,"http://news.youth.cn/gn/");
	}
	//通过静态变量获得
	public static UrlTaskPojo getRootUrlByStaticValue() {
		return new UrlTaskPojo(StaticValue.title,StaticValue.rootUrlString);
	}
	
	//通过配置文件获得
	public static List<UrlTaskPojo> getRootUrlBySeedFileFromClassPath(String dataFilepath) throws Exception {
		
		List<UrlTaskPojo> resultTaskPojo = new ArrayList<UrlTaskPojo>();
		
		List<String> lineList = IOUtil.readFileToList(dataFilepath, true, StaticValue.DEFAULT_ENCODING);
		for(String line:lineList){
			line.trim();
			if(line.length()>0 && !line.startsWith("#")){//用#注释
			 String[] colunmnArrayStrings =	line.split("\\s");
			 	if(colunmnArrayStrings.length==2){
			 		UrlTaskPojo tempPojo = new UrlTaskPojo(colunmnArrayStrings[0].trim(),colunmnArrayStrings[1].trim());
			 		resultTaskPojo.add(tempPojo);
			 	}else{
			 		System.err.println("错误行："+line);
			 		throw new Exception("存在不规范，请检查!");
			 	}
			}
		}	
		return resultTaskPojo;
	}
	
	public static List<UrlTaskPojo> getRootUrlBySeedFile(String dataFilepath,boolean isClasspath) throws Exception {
		List<UrlTaskPojo> resultTaskPojo = new ArrayList<UrlTaskPojo>();
		
		List<String> lineList = IOUtil.readFileToList(dataFilepath, isClasspath, StaticValue.DEFAULT_ENCODING);
		for(String line:lineList){
			line.trim();
			if(line.length()>0 && !line.startsWith("#")){//用#注释
			 String[] colunmnArrayStrings =	line.split("\\s");
			 	if(colunmnArrayStrings.length==2){
			 		UrlTaskPojo tempPojo = new UrlTaskPojo(colunmnArrayStrings[0].trim(),colunmnArrayStrings[1].trim());
			 		resultTaskPojo.add(tempPojo);
			 	}else{
			 		System.err.println("错误行："+line);
			 		throw new Exception("存在不规范，请检查!");
			 	}
			}
		}	
		return resultTaskPojo;
	}
	
	public static void addSeedUrlToTaskSchedule() throws Exception {
		String configPath = "seeds.txt";
		List<UrlTaskPojo> seedUrlpojoList= getRootUrlBySeedFile(configPath, false);
		TaskScheduleManager.addUrlTaskPojoList(seedUrlpojoList);
	}
	public static void main(String[] args) throws Exception {
		//配置文件路径
		String configPath = "seeds.txt";
		//配置文件的读取模式，是classpath路径还是系统路径
		boolean isClasspath =false;
		List<UrlTaskPojo> pojoList= UIManager.getRootUrlBySeedFile(configPath,isClasspath);
		
		for(UrlTaskPojo list: pojoList){
			System.out.println(list);
		}		
	}
}
