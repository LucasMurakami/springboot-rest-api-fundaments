package lucas.kaito.murakami.springbootrestapi.controller;

import lucas.kaito.murakami.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("/student")
    public Student getStudent() {
        return new Student(
                1,
                "Test",
                "Example"
        );
    }

    // http://localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Test1", "Example1"));
        students.add(new Student(2, "Test2", "Example2"));
        students.add(new Student(3, "Test3", "Example3"));
        students.add(new Student(4, "Test4", "Example4"));
        return students;
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1

    @GetMapping("/students/{id}")
    public Student studentPathVariable(@PathVariable("id") Integer studentId) {
        return new Student(studentId, "Test", "Example");
    }

    // Spring BOOT REST API with Path Variables
    // {id} - URI template variable
    // http://localhost:8080/students/1/test/example

    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable2(@PathVariable Integer id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1
    // ? = Query param

    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam Integer id) {
        return new Student(id, "Rest", "Example");
    }

    // http://localhost:8080/students/query2?id=1&firstName=Test&lastName=Example
    // ? = Query param
    // & = And, multiple Query param

    @GetMapping("/students/query2")
    public Student studentRequestVariable2(@RequestParam Integer id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring BOOT REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody

    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }



}
