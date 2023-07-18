package lucas.kaito.murakami.springbootrestapi.controller;

import lucas.kaito.murakami.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1,"Test", "Example");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "Test").body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Test1", "Example1"));
        students.add(new Student(2, "Test2", "Example2"));
        students.add(new Student(3, "Test3", "Example3"));
        students.add(new Student(4, "Test4", "Example4"));
        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1

    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") Integer studentId) {
        Student student = new Student(studentId, "Test", "Example");
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API with Path Variables
    // {id} - URI template variable
    // http://localhost:8080/students/1/test/example

    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable2(@PathVariable Integer id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1
    // ? = Query param

    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam Integer id) {
        Student student = new Student(id, "Rest", "Example");
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/query2?id=1&firstName=Test&lastName=Example
    // ? = Query param
    // & = And, multiple Query param

    @GetMapping("/query2")
    public ResponseEntity<Student> studentRequestVariable2(@RequestParam Integer id, @RequestParam String firstName, @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody

    @PostMapping("/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring BOOT REST API that handles HTTP PUT Request - updating existing resource

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring BOOT REST API that handles HTTP DELETE Request - deleting the existing resource

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        return ResponseEntity.ok("Student deleted successfully!");
    }

}
