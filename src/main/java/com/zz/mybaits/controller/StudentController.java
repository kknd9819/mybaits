package com.zz.mybaits.controller;

import com.zz.mybaits.entity.Student;
import com.zz.mybaits.mapper.StudentMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {

    @Resource
    private StudentMapper studentMapper;

    @ApiOperation(value="获取学生列表", notes="获取学生列表")
    @GetMapping("/Students")
    public List<Student> getStudent(){
        return studentMapper.findAll();
    }

    @ApiOperation(value="获取单个学生", notes="根据学生ID查学生")
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/Student/{id}")
    public Student getStudentById(@PathVariable("id") Long id){
        return studentMapper.selectByPrimaryKey(id);
    }

    @ApiOperation(value="新增一个学生", notes="根据学生对象新增一个学生")
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, dataType = "Student")
    @PostMapping("/Student")
    public ResponseEntity<Student> save(Student student){
        int insert = studentMapper.insert(student);
        System.out.println("保存成功:" + insert);
        return ResponseEntity.ok(student);
    }

    @ApiOperation(value="修改单个学生", notes="根据学生ID修改单个学生")
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, dataType = "Long", paramType = "path")
    @PutMapping("/Student/{id}")
    public ResponseEntity<Student> edit(@PathVariable("id") Long id,Student student){
        Student s = studentMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(student,s);
        int i = studentMapper.updateByPrimaryKey(s);
        System.out.println("更新成功:" + i);
        return ResponseEntity.ok(student);
    }

    @ApiOperation(value="删除单个学生", notes="根据学生ID删除单个学生")
    @ApiImplicitParam(name = "id", value = "学生ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/Student/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id){
        Student s = studentMapper.selectByPrimaryKey(id);
        if(s != null){
            int i = studentMapper.deleteByPrimaryKey(id);
            System.out.println("删除成功:" + i);
        }
        return ResponseEntity.ok(s);
    }
}
