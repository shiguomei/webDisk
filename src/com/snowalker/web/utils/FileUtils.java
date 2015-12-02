package com.snowalker.web.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class FileUtils {
	
	public static File[] fileList(String path) {
		File file = new File(path);//实现得到路径下对应的所有文件及文件夹
		return file.listFiles();
	}
	//实现获取得到路径下符合条件的文件和文件夹
	public static File[] fileList(String path, final String cond) {
		File file = new File(path);
		return file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.indexOf(cond) >= 0;
			}
		});
	}
	//读取文件
	public static String readFile(String path) throws IOException {
		File file = new File(path);
		StringBuffer strBuffer = new StringBuffer();
		Reader reader = null;
		BufferedReader bReader = null;
		try {
			reader = new FileReader(file);
			bReader = new BufferedReader(reader);
			String strLine = null;
			while ((strLine = bReader.readLine()) != null) {
				strBuffer.append(strLine + "\n");
			}
		} catch (FileNotFoundException e) {
			
			throw new FileNotFoundException("找不到文件!");
		} catch (IOException e) {
			
			throw new IOException("文件读取异常", e);
		} finally {
			if (bReader != null) {
				bReader.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		return strBuffer.toString();
	}
	//写文件
	public static void writeFile(String path, String content) throws IOException {
		File file = new File(path);
		Writer writer = null;
		BufferedWriter bWriter = null;
		PrintWriter out = null;
		try {
			writer = new FileWriter(file, true);
			bWriter = new BufferedWriter(writer);
			out = new PrintWriter(bWriter);
			out.println(content);
		} catch (Exception e) {
			// TODO: handle exception
			throw new IOException("写文件出现异常", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (bWriter != null) {
				bWriter.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}
	//创建文件功能
	public static boolean createFile(String path) throws IOException {
		File file = new File(path);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			throw new IOException("文件创建失败", e);
		}
	}
	//创建多个文件夹功能
	public static boolean mkdirs(String path) {
		File file = new File(path);
		return file.mkdirs();
	}
	//实现删除文件和文件夹的功能
	public static boolean delete(String path) {
		File file = new File(path);
		return file.delete();
	}
}
