package com.zz.mybaits.mapper;

import com.zz.mybaits.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    @Resource
    private StudentMapper studentMapper;

    @Test
    @Transactional
    public void testStudent(){
        Student student = studentMapper.selectByPrimaryKey(7L);
        student.setName("屌丝郑");
        int i = studentMapper.updateByPrimaryKeySelective(student);
        System.out.println(i);
        Student student1 = new Student();
        student1.setName("测试异常");
        student1.setAge(11);
        int insert = studentMapper.insert(student1);
        System.out.println(insert);
    }

}