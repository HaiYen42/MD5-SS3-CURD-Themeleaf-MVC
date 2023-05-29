package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Student;
import rikkei.academy.service.IStudentService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/student"})
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public String showListStudent(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("listStudent", studentList);
        model.addAttribute("message", "<button>OK con de</button>");
        return "student/list";
    }

    @GetMapping("/{id}")
    public String detailStudent(@PathVariable int id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/detail";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/create";
    }

    //Cách 1 TheamLeaf lấy theo @RequestParam
//    @PostMapping("/create")
//    public String actionCreate(@RequestParam("name") String name,
//                                @RequestParam("address") String address,
//                                @RequestParam("age") int age ){
//        long id =0;
//        if (studentService.findAll().size()==0) {
//            id=1;
//        }else {
//            id = studentService.findAll().get(studentService.findAll().size()-1).getId()+1;
//        }
//        Student student = new Student(id, name, address, age);
//        studentService.save(student);
//        System.out.println("name "+ name);
//        return "redirect:/";
//    }
    //Cách 1- Cách 2: show form Edit-
//    @GetMapping("/edit/{id}")
//    public String showFormEdit(@PathVariable("id") long id, Model model){
//        Student student = studentService.findById(id);
//        model.addAttribute("studentEdit", student);
//        return "student/edit";
//    }

    //Cách 1- Edit
//    @PostMapping("edit/{id}")
//    public String actionEdit(@RequestParam("name") String name,
//                             @RequestParam("address") String address,
//                             @RequestParam("age") int age,
//                             @PathVariable("id") int id){
//        Student student = studentService.findById(id);
//        student.setName(name);
//        student.setAddress(address);
//        student.setAge(age);
//        studentService.save(student);
//        return "redirect:/";
//    }

    //Cách 1-
//    @GetMapping("delete/{id}")
//    public String deleteStudent(@PathVariable("id") long id ){
//        studentService.deleteById(id);
//        return "redirect:/";
//    }

//    Cách 2: Create - TheamLeaf theo cách truyền đối tượng từ backend--> fontEnd
    @PostMapping("create")
    public String actionCreate(Student student) {
        long id = 0;
        if (studentService.findAll().size() == 0) {
            id = 1;
        } else {
            id = studentService.findAll().get(studentService.findAll().size() - 1).getId() + 1;
        }
        student.setId(id);
        studentService.save(student);
        System.out.println("student--->" + student);
        return "redirect:/";
    }

    //Cách 2
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable("id") long id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("studentEdit", student);
        return "student/edit";
    }
    @PostMapping("/edit/{id}")
    public String actionEdit(Student student){
        studentService.save(student);
        System.out.println("student--->" + student);
        return "redirect:/";
    }
    // Cách 2 Delete


   @GetMapping("/delete/{id}")
   public String showFormdelete(@PathVariable("id") long id, Model model){
       Student student = studentService.findById(id);
       model.addAttribute("studentDelete", student);
       return "student/delete";
   }
    @PostMapping("/delete/{id}")
    public String actionDelete(Student student){
        System.out.println("studentDelete "+ student);
        studentService.deleteById(student.getId());
        return "redirect:/";
    }
}
