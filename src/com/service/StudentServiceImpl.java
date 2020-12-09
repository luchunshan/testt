package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.IStudentMapper;
import com.po.Clazz;
import com.po.Student;
@Service("StudentServiceImpl")
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Resource(name="IStudentMapper")
	private IStudentMapper sdao;
    
	public IStudentMapper getSdao() {
		return sdao;
	}

	public void setSdao(IStudentMapper sdao) {
		this.sdao = sdao;
	}

	@Override
	public int save(Student st) {
		// TODO Auto-generated method stub
		return sdao.save(st);
	}

	@Override
	public List<Student> findPageAll(int page, int rows) {
		// TODO Auto-generated method stub
		return sdao.findPageAll(page, rows);
	}

	@Override
	public List<Clazz> doinit() {
		// TODO Auto-generated method stub
		return sdao.doinit();
	}

	@Override
	public int maxRows() {
		// TODO Auto-generated method stub
		return sdao.maxRows();
	}

	@Override
	public Student findById(int sid) {
		// TODO Auto-generated method stub
		return sdao.findById(sid);
	}

}
