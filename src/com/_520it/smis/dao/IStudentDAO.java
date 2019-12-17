package com._520it.smis.dao;

import java.util.List;

import com._520it.smis.domain.Student;

public interface IStudentDAO {
	/**
	 * 保存学生
	 * @param stu
	 */
	void save(Student stu);
	/**
	 * 删除学生
	 * @param id
	 */
	void delete(Long id);
	/**
	 *更新信息
	 * @param id
	 * @param stu
	 */
	void update(Long id,Student stu);
	/**
	 * 获得学生
	 * @param id
	 * @return
	 */
	Student get(Long id);
	/**
	 * 返回所有stu对象放在list
	 * @return
	 */
	List<Student>  listall();
 }
