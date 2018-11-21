package xyz.zyrs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.zyrs.demo.bean.Student;
import xyz.zyrs.demo.service.StudentService;

import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/student",method = RequestMethod.GET)
    public String get_student_list_all(Map<String,Object> map) {
        map.put("studentList",studentService.get_student_list_all());
        //System.out.println("学生列表"+studentService.get_student_list_all());
        return "student";
    }

    /**
     * 修改 或 新增学生信息
     * @param student
     * @return
     */
    @RequestMapping(value="/student_save_modify",method = RequestMethod.POST)
    public String student_save_modify(Student student){
        //System.out.println(student);
        if(student.getId() == 0){
            studentService.insert_student(student);
        }else{
            studentService.update_student(student);
        }


        return "redirect:/student";
    }

    /**
     * 删除学生信息
     * @param id  学生id
     * @return  1成功 2失败
     */
    @RequestMapping(value="/student_delete",method = RequestMethod.DELETE)
    @ResponseBody
    public int student_delete(Integer id){

        try{
            studentService.delete_student(id);
            return 1;
        }catch (Exception e){
            return 2;
        }
    }

}