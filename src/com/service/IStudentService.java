package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.po.Clazz;
import com.po.Student;

public interface IStudentService {
	public int save(Student st);
	public List<Student> findPageAll(int page,int rows);
	public List<Clazz> doinit();
	public int maxRows();
	public Student findById(int sid);
}
