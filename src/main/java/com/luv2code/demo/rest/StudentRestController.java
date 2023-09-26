package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController
{
    /*
    * +--------------------------------------+
    * | DEFINE THE ENDPOINT TO RETURN A LIST |
    * |            OF STUDENTS               |
    * +--------------------------------------+
    *
    * When we return the List of POJOS Students
    * Spring will use for default the Jackson library
    * to convert them to a JSON objects.
    *
    * This is doing automatically.
    * */
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        // Define a List of students and assign an ArrayList
        List<Student> students = new ArrayList<>();

        // Add the students
        students.add(new Student());
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));

        return students;
    }
}
