package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController
{
    private List<Student> theStudents;

    // Define @PostConstructor to load the data... only once!
    // When this is being constructed.
    @PostConstruct
    public void loadData()
    {
        // Define a List of students and assign an ArrayList
        theStudents = new ArrayList<>();

        // Add the students
        theStudents.add(new Student());
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

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
        return theStudents;
    }

    /*
    * +---------------------------+
    * | SEND PARAMS FOR THE ROUTE |
    * +---------------------------+
    *
    * The params on the route are surround
    * by curly braces {}, and the name of
    * the param inside of them.
    *
    * Then we define a parameter in the method
    * to take the value of the route param and both
    * must have the same name, exactly.
    * */
    @GetMapping("/students/{studentId}")
    /*
    * The @PathVariable put the route param value inside
    * the method param that match with name of the first.
    * */
    public Student getStudent(@PathVariable int studentId)
    {
        // We are using the get() method of the List<>
        // to get the object with the index of the element.
        return this.theStudents.get(studentId);
    }
}
