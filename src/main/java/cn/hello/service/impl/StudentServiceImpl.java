package cn.hello.service.impl;

import cn.hello.bean.Student;
import cn.hello.bean.StudentExample;
import cn.hello.mapper.StudentMapper;
import cn.hello.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/4/18 17:11
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student getById(int id) {

        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> getAll() {
        StudentExample studentExample = new StudentExample();
        return studentMapper.selectByExample(studentExample);
    }

    @Override
    public void save(Student student) {
        student.setCreatetime(new Date());
        if (student.getAge() == 30) {
            student.setAge(31);
        }
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void del(int id) {
        studentMapper.deleteByPrimaryKey(id);
    }
}
