package com.tiexue.cms.manage.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ImgConfUtil {

	private static Logger logger=Logger.getLogger(ImgConfUtil.class);
	private static String imgUpLoadUrl = null;

	/**
	 * 获取 imgconf.properties中上传图片的服务器地址
	 * @return
	 */
	public static String getImgUpLoadUrl() {
		try {
			if(imgUpLoadUrl!=null)
				return imgUpLoadUrl;
			// 读取db.properties文件
			Properties props = new Properties();
			/**
			 * . 代表java命令运行的目录 在java项目下，. java命令的运行目录从项目的根目录开始 在web项目下， .
			 * java命令的而运行目录从tomcat/bin目录开始 所以不能使用点.
			 */
			// FileInputStream in = new FileInputStream("./src/imgconf.properties");

			/**
			 * 使用类路径的读取方式 / : 斜杠表示classpath的根目录 在java项目下，classpath的根目录从bin目录开始
			 * 在web项目下，classpath的根目录从WEB-INF/classes目录开始
			 */
			InputStream in = ImgConfUtil.class.getResourceAsStream("/imgconf.properties");

			// 加载文件
			props.load(in);
			// 读取信息
			imgUpLoadUrl = props.getProperty("imgUpLoadUrl");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("获取上传图片路径失败"+e);
		}

		return imgUpLoadUrl;
	}

}
