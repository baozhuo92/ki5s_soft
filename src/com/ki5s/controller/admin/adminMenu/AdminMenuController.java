package com.ki5s.controller.admin.adminMenu;

import com.ki5s.base.util.UtilExportExcel;
import com.ki5s.entity.adminMenu.AdminMenu;
import com.ki5s.service.adminMenu.AdminMenuService;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping
public class AdminMenuController {
	private Log log = LogFactory.getLog(AdminMenuController.class);
	
	@Resource
	AdminMenuService AdminMenuService;
	
	@ResponseBody
	@RequestMapping(value="/admin/loadMenu.json",method=RequestMethod.POST)
	public Map<String, Object> loadMenu(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<AdminMenu> adminMenus = AdminMenuService.listAdminMenuAll();
			map.put("menu", adminMenus);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询所有后台菜单时出错:"+e.getMessage());
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/admin/loadTable.json",method=RequestMethod.POST)
	public Map<String, Object> loadTable(){
		Map<String, Object> map =new HashMap<>();
		try {
			List<AdminMenu> adminMenus = AdminMenuService.listAdminMenuAll();
			map.put("total", 100);
			map.put("rows", adminMenus);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询所有后台菜单时出错:"+e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="/admin_menu.html",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView view){
		view.setViewName("/content");
		return view;
	}
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportExcel.html",method=RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,HttpServletResponse response){
		try {
		    	String [] titleStrs = {"编号","名称","链接","父级编号"};
			    List<String[]> contentList = new ArrayList<String[]>();
			    List<AdminMenu> adminMenus = AdminMenuService.listAdminMenuAll();
			    for (AdminMenu a : adminMenus) {
			    	String [] contents1 = {a.getId(),a.getName(),a.getMenuUrl(),a.getParentId()};
			    	contentList.add(contents1);
				}
			    //设置格式化时间的格式
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
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
			    String filename	 = sdf.format(new Date());
			    path+="/";
			    //调用写入Excel方法
		    	UtilExportExcel we =new UtilExportExcel();
		    	we.writeExcel07(titleStrs, contentList, filename,path);
		    	//拼接绝对路径的全文件名
		    	String newFileName = path+filename+".xlsx";
		    	//新建一个文件
		    	File f = new File(newFileName);
		    	//读取文件地址
		    	Path p = Paths.get(newFileName);  
		    	//设置文件类型
            	response.setContentType(Files.probeContentType(p));
            	//设置文件头
                response.setHeader("Content-Disposition", "attachment;fileName="+filename+".xlsx");
                //复制文件到临时输出流
                FileCopyUtils.copy(new FileInputStream(f), response.getOutputStream());
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
	}

	
	/**
	 * 导出Excel(CSV格式)
	 * @param response
	 */
	@RequestMapping(value="/exportCSV.html",method=RequestMethod.GET)
	public void exportCSV(HttpServletResponse response){
		try {
		    	StringBuffer contents = new StringBuffer("\"编号\",\"名称\",\"链接\",\"父级编号\"");
			    List<AdminMenu> adminMenus = AdminMenuService.listAdminMenuAll();
			    for (AdminMenu a : adminMenus) {
			    	contents.append("\n");
			    	stringAppend(contents,a.getId());
			    	stringAppend(contents,a.getName());
			    	stringAppend(contents,a.getMenuUrl());
			    	contents.append("\"").append(a.getParentId()==null?"":a.getParentId()).append("\"");
				}
			    //设置格式化时间的格式
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		        String fileName = sdf.format(new Date())+".csv";
		    	//设置文件类型
            	response.setContentType("applation/octet-stream; charset=utf-8");
            	//设置文件头
                response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
                //复制文件到临时输出流
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(contents.toString().getBytes());
                outputStream.flush();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
	}
	private StringBuffer stringAppend(StringBuffer sb,String str){
		if(!StringUtils.isNullOrEmpty(str)){
			str=str.replaceAll("\"", "'");
		}
		return sb.append("\"").append(str==null?"":str).append("\",");
	}
}
