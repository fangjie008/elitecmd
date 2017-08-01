package com.tiexue.cms.manage.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.tiexue.cms.base.config.CustomizedPropertyConfigurer;
import com.tiexue.cms.manage.entity.ImgFormat;
import com.tiexue.cms.manage.entity.ImgUploadRet;

public class ImgUtils {

	// 上传网络图片
	private static final String picUploadUrl_Net ="";
	/**
	 * 上传网络图片
	 * @return
	 */
	public static String getPicuploadurlNet() {
		String url="";
		if(ImgConfUtil.getImgUpLoadUrl()==null||ImgConfUtil.getImgUpLoadUrl().isEmpty())
			url="http://pic.junqingtv.com";
		else
			url=ImgConfUtil.getImgUpLoadUrl();
		return url+"/home/UploadNetPicWithSize";
	}

	

	// 上传本地图片
	private static final String picUploadUrl_File="";
	/**
	 * 上传本地图片路径
	 * @return
	 */
	public static String getPicuploadurlFile() {
		String url="";
		if(ImgConfUtil.getImgUpLoadUrl()==null||ImgConfUtil.getImgUpLoadUrl().isEmpty())
			url="http://pic.junqingtv.com";
		else
			url=ImgConfUtil.getImgUpLoadUrl();
		return url+"/home/UploadImgWithSize";
	}
	

	
	public static String updateImage(InputStream is, String fileName, long maxSize) {
		String urlStr = getPicuploadurlFile();
		String res = "";
		HttpURLConnection conn = null;
		// boundary就是request头和上传文件内容的分隔符
		String BOUNDARY = "****************fD4fH3gL0hK7aI6";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// name
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"name\"\r\n\r\n");
			strBuf.append(fileName);
			out.write(strBuf.toString().getBytes());
			// file
			strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\file\"; filename=\"" + fileName + "\"\r\n");
			strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(strBuf.toString().getBytes());
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = is.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			is.close();
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	public static ImgUploadRet updateImage(MultipartFile file, ImgFormat... imgFormatList) {
		String urlStr =getPicuploadurlFile();
		String res = "";
		String fileName = file.getOriginalFilename();
		ImgUploadRet imgUploadRet=null;
		HttpURLConnection conn = null;
		// boundary就是request头和上传文件内容的分隔符
		String BOUNDARY = "****************fD4fH3gL0hK7aI6";
		try {
			InputStream is = file.getInputStream();
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// name
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"name\"\r\n\r\n");
			strBuf.append(fileName);
			out.write(strBuf.toString().getBytes());
			
			
			// imageSize
			String imageSizes=getImgSize(imgFormatList);
		    strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"imageSize\"\r\n\r\n");
			strBuf.append(imageSizes+"\r\n");
			out.write(strBuf.toString().getBytes());
			// file
			strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\file\"; filename=\"" + fileName + "\"\r\n");
			strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(strBuf.toString().getBytes());
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = is.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			is.close();
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			if(res!=null){
				imgUploadRet=JSON.parseObject(res,ImgUploadRet.class);
			}
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return imgUploadRet;
	}
	
	/**
	 * 拼接要上传图片的格式
	 * @param imgFormatList
	 * @return
	 */
	private static String getImgSize(ImgFormat... imgFormatList){
		//组合imageSizes
        String imageSizes = "";
        String imageSize = "";
        for (ImgFormat img : imgFormatList) {
        	 imageSize =img.getId()+","+img.getFormatType()+","+img.getWidth()+","+img.getHigh();
             if (!imageSizes.isEmpty())
             {
                 imageSizes +="|"+ imageSize;
             }
             else
             {
                 imageSizes = imageSize;
             }
		}
        return imageSizes;

	}
	

}
