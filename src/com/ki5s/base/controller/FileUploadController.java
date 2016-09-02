package com.ki5s.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 本地上传
 * @author baozhuo 
 *
 */
@Controller
@RequestMapping
public class FileUploadController{
	private static final Log logger = LogFactory.getLog(FileUploadController.class); 
	
	/** 上传文件名 */
	private static final String UPLOAD_FILE_NAME = "uploadFile";
	
	/**
	 * 前端获取服务器绝对路径图片
	 * @param fileName
	 * @param response
	 */
	@RequestMapping(value = "/update/getImg.html", method = RequestMethod.GET)
	public void getImg(String fileName ,HttpServletResponse response){
        File f = new File(fileName);
        try {
        	String file = fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
            Image image = ImageIO.read(f);
            if(image == null){
            	Path path = Paths.get(fileName);  
            	response.setContentType(Files.probeContentType(path));
                response.setHeader("Content-Disposition", "attachment;fileName="+file);
            }
			FileCopyUtils.copy(new FileInputStream(f), response.getOutputStream());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/ajaxFileUpload.html", method = RequestMethod.POST)
	public Map<String,Object> ajaxFileUpload(HttpServletRequest request, HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
	    MultipartFile uploadFile = multipartRequest.getFile(UPLOAD_FILE_NAME);
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (uploadFile == null) {
	    	map.put("state", "error");
	    	map.put("message", "上传失败：文件为空");
            return map; 
        }else if(uploadFile.getSize()>204800){
        	map.put("state", "error");
	    	map.put("message", "上传失败：文件过大");
            return map;
        }
	    try {
	    	//获得上传原文件名
			String fileName = uploadFile.getOriginalFilename();
			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
	        String fileExt = fileName.substring(fileName.lastIndexOf("."),fileName.length()).toLowerCase();  
			//设置格式化时间的格式
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        //获取当前时间作为新文件名
	        String newName = sdf.format(new Date());
	        //获取当前项目根目录
	        String path = request.getSession().getServletContext().getRealPath("");
            File filePath=new File(path);
            //获取上级目录的上级目录,并加上/upload
            path = filePath.getParentFile().getParent()+"/upload";
            //获取文件夹位置（用作判断文件夹是否存在）
            File file =new File(path);
            //如果文件夹不存在则创建
            if  (!file .exists()  && !file .isDirectory()){
                //创建upload目录
                file.mkdir();
            }
            //把图片放在项目根目录的上一级目录(防止系统更新导致文件丢失)
            path+= "/"+newName+fileExt;
	        //创建文件
	        File f = new File(path); 
	        //保存文件到指定目录
	    	uploadFile.transferTo(f);
	        //前端获取绝对路径图片地址
	        String frontImg = "/update/getImg.html?fileName="+path;
	    	//返回成功状态
	    	map.put("state", "success");
	    	//返回成功消息
	    	map.put("message","上传成功" );
	    	//返回给kindEditor
	    	map.put("url",frontImg );
	    	return map;
		} catch (IOException e) {
			String msg   = "上传文件失败："+e.getMessage();
			logger.info("=========="+msg);
			map.put("state", "error");
	    	map.put("message",msg );
            return map;
		}
	}
}
