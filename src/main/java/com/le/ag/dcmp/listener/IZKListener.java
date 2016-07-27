/** 
 * @Project_Name dcmp 
 * @File_Name IZKListener.java 
 * @Date 2016年7月13日下午11:55:03 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.listener;

import java.util.List;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月13日
 * @since JDK 1.7
 * @Function zk监听器
 * @Reason
 */
public interface IZKListener {

	List<String> getPath();
    
    void setValue(final String path, byte[] data);
}
