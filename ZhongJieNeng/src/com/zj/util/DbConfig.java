package com.zj.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author xueliang
 * 读取配置文件
 * 这里配置文件位置固定了
 * 暂时有一个问题
 *
 */
public class DbConfig {
	
	private String path="";//"D://apache-tomcat-7.0.47//webapps//SpeedRacing//WEB-INF//classes//ices//sr//dzt//util//dbconfig.property";
	private String driver;
	private String url;
	private String userName;
	private String password;
	
	public DbConfig(){
		path=DbConfig.class.getResource("").toString().substring(6)+"dbconfig.property";
		File f=new File(path);
		FileReader reader=null;
		try {
			reader=new FileReader(f);
		} catch (FileNotFoundException e1) {
			System.out.println("数据库配置文件加载失败");
			e1.printStackTrace();
		}
		Properties p=new Properties();
		try {
		    p.load(reader);
			this.driver=p.getProperty("driver");
			this.url=p.getProperty("url");
			this.userName=p.getProperty("username");
			this.password=p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}
