package rikkei.academy.service;

import rikkei.academy.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService {
    public static List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student(1, "Thu", "Yen Bai", 2));
        studentList.add(new Student(2, "Chuan", "Nam Dinh", 3));
        studentList.add(new Student(3, "Yen", "Ha Noi", 4));
        studentList.add(new Student(4, "Vuong", "Ho Tay", 5));
    }
    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Student findById(long id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId()==id) {
                return studentList.get(i);
            }
        }
        return null;
    }

    @Override
    public void save(Student student) {
        Student student1 = findById(student.getId());
        if (student1 == null) {
            studentList.add(student);
        }else {
            studentList.set(studentList.indexOf(student1),student);
        }
    }

    @Override
    public void deleteById(long id) {
        Student student = findById(id);
        studentList.remove(student);
    }
}
