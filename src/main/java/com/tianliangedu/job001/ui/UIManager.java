package com.tianliangedu.job001.ui;

import com.tianliangedu.job001.utils.StaticValue;

/**
 * 该类为用户接口管理类，包括种子添加接口、种子添加的不同方式和来源
 * @author Lyq
 * @company 
 *
 */
public class UIManager {
	//直接给
	public String getRootUrlByDirect() {
		return "http://news.youth.cn/gn/";
	}
	//通过静态变量获得
	public String getRootUrlByStaticValue() {
		return StaticValue.rootUrlString;
	}
	//通过配置文件获得
	public String getRootUrlBySeedFileFromClassPath() {
		
		return StaticValue.rootUrlString;
	}
}
