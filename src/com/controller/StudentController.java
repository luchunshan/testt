package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.po.Clazz;
import com.po.PageBean;
import com.po.Student;
import com.service.IStudentService;
import com.util.AJAXUtils;

@Controller
public class StudentController {
	@Resource(name="StudentServiceImpl")
	private IStudentService sbiz;

	public IStudentService getSbiz() {
		return sbiz;
	}

	public void setSbiz(IStudentService sbiz) {
		this.sbiz = sbiz;
	}
	@RequestMapping(value="save_Student.do")
    public String save(HttpServletRequest request,HttpServletResponse response,Student st){
		st.setFname("default.jpg");
	   int flag=sbiz.save(st);
	   if(flag>0){
		    String jsonstr=JSONObject.toJSONString(1);
	 		 AJAXUtils.printString(response, jsonstr);
	   }else{
		   String jsonstr=JSONObject.toJSONString(0);
	 		 AJAXUtils.printString(response, jsonstr);
	   }
	return null; 
    }
	@RequestMapping(value="findPageAll_Student.do")
	 public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		 HttpSession session=request.getSession();
		 PageBean pb=(PageBean)session.getAttribute("pb");
		 pb=pb==null?new PageBean():pb;
		 page=page==null||page<1?pb.getPage():page;
		 rows=rows==null||rows<1?pb.getRows():rows;
		 int maxRows=sbiz.maxRows();
		 int maxPage=1;
		 if(maxRows!=0){
			 maxPage=maxRows%rows==0?maxRows/rows:maxRows/rows+1;
		 }
		 if(page>maxPage)page=maxPage;
		 pb.setPage(page);
         pb.setRows(rows);
         pb.setMaxpage(maxPage);
         List<Student> lsst=sbiz.findPageAll(page, rows);
         pb.setPagelist(lsst);
         String jsonstr=JSONObject.toJSONString(pb);
 		 AJAXUtils.printString(response, jsonstr);
         return null; 
	 }
	@RequestMapping(value="findById_Student.do")
	 public String findById(HttpServletRequest request,HttpServletResponse response,Integer sid){
		Student oldst=sbiz.findById(sid);
		String jsonstr=JSONObject.toJSONString(oldst);
		AJAXUtils.printString(response, jsonstr);
		return null; 
	 }
	@RequestMapping(value="doinit_Student.do")
	 public String doinit(HttpServletRequest request,HttpServletResponse response){
		List<Clazz> lsca=sbiz.doinit();
		String jsonstr=JSONObject.toJSONString(lsca);
		AJAXUtils.printString(response, jsonstr);
		return null; 
	 }

}
