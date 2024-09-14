package cn.hello.service;

import cn.hello.bean.Student;

import java.util.List;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/4/18 17:11
 */
public interface IStudentService {

   Student getById(int id);

   List<Student> getAll();

   void save(Student student);

   void update(Student student);

   void del(int id);
}
