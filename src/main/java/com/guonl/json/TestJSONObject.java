package com.guonl.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class TestJSONObject {  
    
    public static void main(String[] args) {  
        Student student_1 = new Student("学号1", "学生1");  
        List<Student> stus = new ArrayList<Student>();  
        stus.add(student_1);  
        Teacher teacher_1 = new Teacher("编号1", "教师1", stus);  
        JSONObject obj = JSONObject.fromObject(teacher_1);  
        System.out.println("JSON格式的Teacher->" + obj.toString());  
        Teacher teacherBean = (Teacher) JSONObject.toBean(obj, Teacher.class);  
        try {  
            Student studentBean = teacherBean.getStus().get(0);  
            System.out.println(studentBean);  
        } catch (Exception e) {  
            System.out.println("出现异常");  
            e.printStackTrace();  
        }  
    }  
      
}  
