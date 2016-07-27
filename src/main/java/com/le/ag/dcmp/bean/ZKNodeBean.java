/** 
 * @Project_Name dcmp 
 * @File_Name ZKNodeBean.java 
 * @Date 2016年7月13日下午11:41:34 
 * @Copyright(c) 2016, liyixiang@le.com All Rights Reserved. 
 * 
 */
package com.le.ag.dcmp.bean;

/**
 * @developer liyixiang
 * @Info
 * * * @company leEco
 * * * @email <liyixiang@le.com>
 * * * @Team smartConnected
 * @date 2016年7月13日
 * @since JDK 1.7
 * @Function zk节点信息
 * @Reason
 */
public class ZKNodeBean {

	//节点创建时间
	private Long ctime;
	//节点修改时间
	private Long mtime;
	//子节点数量
	private int numChildren;
	
	public Long getCtime() {
		return ctime;
	}
	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}
	public Long getMtime() {
		return mtime;
	}
	public void setMtime(Long mtime) {
		this.mtime = mtime;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	
}
