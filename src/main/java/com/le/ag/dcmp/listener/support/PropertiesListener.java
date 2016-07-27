/** 
 * @Project_Name dcmp 
 * @File_Name PropertiesListener.java 
 * @Date 2016年7月15日下午2:02:21 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.listener.support;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.le.ag.dcmp.config.PropertiesConfigManager;
import com.le.ag.dcmp.listener.IZKListener;
import com.le.ag.dcmp.utils.FileOperator;


/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月15日
 * @since JDK 1.7
 * @Function properties文件监听器
 * @Reason
 */
public class PropertiesListener implements IZKListener {

	private static Logger log = LoggerFactory.getLogger(PropertiesListener.class);

	private List<String> path;
	
	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}
	
	public PropertiesListener(List<String> path){	
		this.path = path;
	}

	@Override
	public void setValue(String path, byte[] data) {
		// TODO Auto-generated method stub
		InputStream in_nocode = new ByteArrayInputStream(data);
		PropertiesConfigManager.getInstance().setProps(in_nocode,path);
		//再从各自应用本地生成一份
		InputStream stream = new ByteArrayInputStream(data);
		String ph = path;
		if(ph.indexOf("/") > 0){
			ph = ph.substring(ph.lastIndexOf("/")+1,ph.length());
		}	
		String s[] = ph.split("_");
		log.info("the save file name :"+s[0]+"."+s[1]);
		String zkPath = "";
		try {
			zkPath = "" + this.getClass().getResource("/zookeeper.properties").toURI();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zkPath = FileOperator.getRootPath(zkPath);		
		zkPath = zkPath + s[0] + "." + s[1];		         		
		log.info("the zkPath:"+zkPath);	         		
		FileOperator.writerFile(stream,path);
	}

}
