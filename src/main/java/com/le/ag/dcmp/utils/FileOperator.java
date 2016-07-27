package com.le.ag.dcmp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @developer liyixiang
 * @Info
 * * * @Company leEco
 * * * @Email <liyixiang@le.com>
 * * * @Team SmartConnected
 * @date 2016年7月25日
 * @since JDK 1.7
 * @Function 文件操作工具类
 * @Reason
 */
public class FileOperator {
	
	private static final transient Logger log = LoggerFactory.getLogger(FileOperator.class);

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月27日 下午2:11:24
	 * @descriptor 把文件中的内容一行行读出放在List中 
	 * @Function
	 * @param fullFileName //带路径的文件名
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readFileLine(String fullFileName) throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new FileReader(fullFileName));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}
			}
		} finally {
			if (infile != null) {
				infile.close();
			}
		}
		return recordList;
	}

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月27日 下午2:10:36
	 * @descriptor 把InputStream中的内容一行行读出放在List中 
	 * @Function
	 * @param stream
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readInputStreamLine(InputStream stream) throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new InputStreamReader(stream));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}
			}
		} finally {
			if (infile != null) {
				infile.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
		return recordList;
	}

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月27日 下午2:12:07
	 * @descriptor List的内容 一行行写入文件中 
	 * @Function
	 * @param fullFileName //带路径的文件名
	 * @param recordList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFileLine(String fullFileName, List<?> recordList) throws FileNotFoundException, IOException {
		BufferedWriter outfile = null;
		try {
			outfile = new BufferedWriter(new FileWriter(fullFileName));
			for (Iterator<?> iter = recordList.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				outfile.write(element);
			}
		} finally {
			if (outfile != null) {
				outfile.close();
			}
		}
	}

	/**
	 * 
	 * @developer liyixiang
	 * @date 2016年7月27日 下午2:12:32
	 * @descriptor yyyy-mm-dd hh:mm:ss 和yyyymmddhhmmss之间的转换
	 * @Function
	 * @param startDayStr
	 * @return
	 */
	public static String switchDateFormat(String startDayStr) {
		String result = "";
		if (startDayStr.length() == 14) {
			result += startDayStr.substring(0, 4);
			result += "-";
			result += startDayStr.substring(4, 6);
			result += "-";
			result += startDayStr.substring(6, 8);
			result += " ";
			result += startDayStr.substring(8, 10);
			result += ":";
			result += startDayStr.substring(10, 12);
			result += ":";
			result += startDayStr.substring(12, 14);
		} else if (startDayStr.length() > 14) {
			String[] item = startDayStr.trim().split(" ");
			String[] item1 = item[0].split("-");
			String[] item2 = item[1].split(":");
			result += item1[0];
			result += item1[1].length() == 2 ? item1[1] : "0" + item1[1];
			result += item1[2].length() == 2 ? item1[2] : "0" + item1[2];
			result += item2[0].length() == 2 ? item2[0] : "0" + item2[0];
			result += item2[1].length() == 2 ? item2[1] : "0" + item2[1];
			result += item2[2].length() == 2 ? item2[2] : "0" + item2[2];
		}
		return result;
	}

	// 下面是一个批量修改文件名的程序

	/***************************************************************************
	 * 
	 */
	public static void clearFileName(String path, String fromName,String toName, String[] deleteName) {
		deleteFile(path, deleteName);
		reNameFile(path, fromName, toName);
	}
	
	public static void deleteFile(String path, String[] deleteName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						int pointPosition = fileList[count].getName().lastIndexOf(".");
						if (pointPosition == -1) {
							continue;
						}
						String fileNameExt = fileList[count].getName().substring(pointPosition + 1);
						for (int i = 0; i < deleteName.length; i++) {
							if (deleteName[i].equalsIgnoreCase(fileNameExt)) {
								log.debug("delete file=" + fileList[count].getName() + " " + (fileList[count].delete()));
								continue;
							}
						}
					} else {
						deleteFile(fileList[count].toString(), deleteName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void reNameFile(String path, String fromName, String toName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						int pointPosition = fileList[count].getName().lastIndexOf(".");
						if (pointPosition == -1) {
							continue;
						}
						String fileNameExt = fileList[count].getName().substring(pointPosition + 1);
						if (fromName.equalsIgnoreCase(fileNameExt)) {

							log.debug(fileList[count].getParent()+ "/"+ fileList[count].getName().replaceAll(fromName, toName));
							File tofile = new File(fileList[count].getParent() + "/" + fileList[count].getName().replaceAll(fromName, toName));
							log.debug("rename file=" + fileList[count].getName() + " " + fileList[count].renameTo(tofile));
							tofile = null;
							fileList[count] = null;
							continue;
						}
					} else {
						reNameFile(fileList[count].toString(), fromName, toName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteall(String path) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isFile()) {
						log.debug("delete file=" + fileList[count].getName() + " " + (fileList[count].delete()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteDirectory(String path, String[] deleteName) {
		File baseDir = new File(path);
		File[] fileList = baseDir.listFiles();
		try {
			if (fileList != null) {
				// Loop through all the files and directory to delete them
				for (int count = 0; count < fileList.length; count++) {
					if (fileList[count].isDirectory()) {
						String directoryName = fileList[count].getName();
						for (int i = 0; i < deleteName.length; i++) {
							if (deleteName[i].equalsIgnoreCase(directoryName)) {
								deleteall(fileList[count].toString());
								log.debug("delete Directory=" + fileList[count].toString() + " " + fileList[count].delete());
								continue;
							} else {
								deleteDirectory(fileList[count].toString(),deleteName);
							}
						}
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFileLine(String fullFileName, String content) throws FileNotFoundException, IOException {	
		BufferedWriter outfile = null;
		try {					
				File file = new File(fullFileName);		
				if(file.exists()){					
					File target = new File(fullFileName+"bak");
					customBufferStreamCopy(file,target);
				}
				outfile = new BufferedWriter(new FileWriter(fullFileName));
				outfile.write(content);
		} finally {
			if (outfile != null) {
				outfile.close();
			}
		}
	}
	
	public static void writerFile(InputStream stream,String target){		
		   InputStream fis = stream;  
		   OutputStream fos = null;  
		   try {        
		        fos = new FileOutputStream(target);  
		        byte[] buf = new byte[4096];  
		        int i;  
		        while ((i = fis.read(buf)) != -1) {  
		            fos.write(buf, 0, i);  
		        }  
		    }  
		    catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  	       
		        try {
					fis.close();
					fos.close();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	       
		    } 		    
	}
	
	private static void customBufferStreamCopy(File source, File target) {  
		   InputStream fis = null;  
		   OutputStream fos = null;  
		   try {  
	        fis = new FileInputStream(source);  
		        fos = new FileOutputStream(target);  
		        byte[] buf = new byte[4096];  
		        int i;  
		        while ((i = fis.read(buf)) != -1) {  
		            fos.write(buf, 0, i);  
		        }  
		    }  
		    catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  	       
		        try {
					fis.close();
					fos.close();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	       
		    }  
		}
	
	/**
	 * 新封装的
	 * @param source
	 * @param target
	 */
	
	public static void customBufferStreamCopy(InputStream fis, File target) {  
		   //InputStream fis = null;  
		   OutputStream fos = null;  
		   try {  
	      //  fis = new FileInputStream(source);  
		        fos = new FileOutputStream(target);  
		        byte[] buf = new byte[4096];  
		        int i;  
		        while ((i = fis.read(buf)) != -1) {  
		            fos.write(buf, 0, i);  
		        }  
		    }  
		    catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  
		       
		        try {
					fis.close();
					fos.close();  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		    }  
		}
	
	 public static String getRootPath(String path){    
	        //因为类名为"Application"，因此" Application.class"一定能找到    
	         //String result = Application.class.getResource("Application.class").toString();    
	        int index = path.indexOf("lib");    
	        if(index == -1){    
	            index = path.indexOf("classes");    
	         }    
	        	path = path.substring(0,index);    
	        if(path.startsWith("jar")){    
	            // 当class文件在jar文件中时，返回"jar:file:/F:/ ..."样的路径     
	        	path = path.substring(9);  
	        	path = path.indexOf(":") > -1?path.substring(1):path;
	         }else if(path.startsWith("file")){    
	            // 当class文件在class文件中时，返回"file:/F:/ ..."样的路径     
	        	path = path.substring(5);
	        	//os系统 "file:/work/ ..."样的路径 
	        	path = path.indexOf(":") > -1?path.substring(1):path;
	         }    
	        //if(path.endsWith("/"))path = path.substring(0,path.length()-1);//不包含最后的"/"    
	        return path + "classes/";    
	     }  


	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		deleteDirectory("D:\\peak_auto\\auto",new String[] { "CVS" });
	}

}
