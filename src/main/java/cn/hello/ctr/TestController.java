package cn.hello.ctr;


import cn.hello.bean.Student;
import cn.hello.service.IStudentService;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author ziyu.wei
 * <p>
 * 2023/10/24 18:21
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/stu")
public class TestController {

    @Autowired
    private IStudentService studentService;



    @PostMapping("/upload")
    public void importFile(HttpServletRequest request, @RequestPart("file") MultipartFile file) {
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File("D:\\doc-t\\需求\\JW-VMI\\测试数据\\241214\\q.db"));
                IOUtils.write(file.getBytes(), fileOutputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("1111");
        }

    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public String getById(@PathVariable Integer id){
        return JSON.toJSONString(studentService.getById(id));
    }

    /**
     * 获取列表
     * @return
     */
    @GetMapping("/list")
    public List<Student> getAll(){
        return studentService.getAll();
    }

    /**
     * 保存接口
     * @param student
     * @return
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Student student){
        studentService.save(student);
        return true;
    }

    /**
     * 更新接口
     * @param student
     * @return
     */
    @PutMapping("/update")
    public boolean update(@RequestBody Student student){
        studentService.update(student);
        return true;
    }

    /**
     * 删除接口
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public boolean del(@PathVariable int id){
        studentService.del(id);
        return true;
    }



}
