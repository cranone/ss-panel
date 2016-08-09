package com.dep.sspanel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * 文件操作工具
 * @author Maclaine
 *
 */
public class FileUtil {
	/**
	 * 写文件
	 * @param filePath
	 * @param sb
	 * @throws IOException
	 */
	public static void writeByStringBuffer(String filePath, StringBuffer sb) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		out.write(sb.toString().getBytes(ServerUtil.encoding));
		out.close();
	}

	/**
	 * 读文件
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readByStringBuffer(String filePath) throws FileNotFoundException, IOException {
		File file = new File(filePath);
		if ((!file.exists()) || (file.isDirectory())) {
			throw new FileNotFoundException();
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();
		while (temp != null) {
			sb.append(temp + "\n");
			temp = br.readLine();
		}
		br.close();

		return sb.toString();
	}

	/**
	 * 封装写入命令
	 * @throws IOException
	 */
	public static void writeOrder() throws IOException {
		String path = ServerUtil.loadProperty("writefilepath");
		String fileName = ServerUtil.loadProperty("filename");
		if ("".equals(fileName)) {
			Calendar cal = Calendar.getInstance();
			fileName = cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE) + ".txt";
		}
		String filePath = path + fileName;
		StringBuffer sb = new StringBuffer();
		sb.append(ServerUtil.loadProperty("filecontent"));
		writeByStringBuffer(filePath, sb);
	}

	/**
	 * 封装读取命令
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readOrder() throws FileNotFoundException, IOException {
		String path = ServerUtil.loadProperty("readfilepath");
		String fileName = ServerUtil.loadProperty("filename");
		if ("".equals(fileName)) {
			Calendar cal = Calendar.getInstance();
			fileName = cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE) + ".txt";
		}
		String filePath = path + fileName;
		return readByStringBuffer(filePath);
	}
}
