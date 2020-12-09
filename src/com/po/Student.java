package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Student implements Serializable {
	private Integer stuid;
	private String sname;
	private String sex;
	private String address;
	private Date birthday;
	private Integer classid;
	private String fname="default.jpg";
	private String cname;
	//临时属性
	//日期字符串类型转换
	private String sdate;
	//Spring中的文件上传组件
	private MultipartFile pic;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Integer stuid, String sname, String sex, String address, Date birthday, Integer classid,
			String fname, String cname, String sdate, MultipartFile pic) {
		super();
		this.stuid = stuid;
		this.sname = sname;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.classid = classid;
		this.fname = fname;
		this.cname = cname;
		this.sdate = sdate;
		this.pic = pic;
	}
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSdate() {
		sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		return sdate;
	}
	public void setSdate(String sdate) {
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sdate = sdate;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	

}
