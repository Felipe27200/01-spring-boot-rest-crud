package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // Check the studentId against list size
        if ((studentId < 0) || studentId >= theStudents.size())
        {
            throw new StudentNotFoundException("Student id not found - " + studentId );
        }

        // We are using the get() method of the List<>
        // to get the object with the index of the element.
        return this.theStudents.get(studentId);
    }

    // Add an Exception Handler using @ExceptionHandler, this mark the method like that
    @ExceptionHandler
    /*
    * We have to define an object with the structure of the error response, in this case
    * a POJO student error with the needle fields.
    *
    * In the list parameters also we need catch the type of the error or the nature of it.
    * It is to know what type of exception this method can handle.
    * */
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc)
    {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // The first argument is the body of the response and
        // the second is the status of it.
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add other handler exception to catch every any kind of exception (catch all).
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException (Exception exc) // This handle any type of exception, due to object type
    {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        // This status referer to the 404
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage()); // We can customize this code for showing a different error message
        error.setTimestamp(System.currentTimeMillis());

        // The first argument is the body of the response and
        // the second is the status of it.
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
