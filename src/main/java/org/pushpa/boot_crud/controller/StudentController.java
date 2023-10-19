package org.pushpa.boot_crud.controller;

import java.util.List;

import org.pushpa.boot_crud.dto.Student;
import org.pushpa.boot_crud.exception.DataShouldNotRepeatException;
import org.pushpa.boot_crud.exception.StudentIdInvalidException;
import org.pushpa.boot_crud.exception.DataNotFoundException;
import org.pushpa.boot_crud.helper.ResponseStructure;
import org.pushpa.boot_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

// @Controller
// @ResponseBody
@RestController // combination of @Controller and @ResponseBody
public class StudentController {

    // @GetMapping("/abc")
    // public String hello(){
    // return "Hello";
    // }
    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<ResponseStructure<Student>> save(@RequestBody Student student){
        return new ResponseEntity<ResponseStructure<Student>>(studentService.save(student),HttpStatus.CREATED);
    }

    @PostMapping("/students/many")
    public ResponseEntity<ResponseStructure<List<Student>>> saveManyStudents(@RequestBody List<Student> students){
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.saveManyStudents(students), HttpStatus.CREATED);
    }

    //fetch all students
    @GetMapping("/students")
    @Operation(summary = "fetch all Records")
    public ResponseEntity<ResponseStructure<List<Student>>> fetchAllStudents() {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.fetchAllStudents(),HttpStatus.FOUND);
    }

    //fetch by Id
    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseStructure<Student>> findById(@PathVariable int id){
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findById(id),HttpStatus.FOUND);

    }

    //fetch by Mobile
    @GetMapping("/student/mobile/{mobile}")
    public ResponseEntity<ResponseStructure<Student>> findByMoblie(@PathVariable long mobile) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findByMoblie(mobile),HttpStatus.FOUND);
    }

    //fetch by email
    @GetMapping("/student/email/{email}")
    public ResponseEntity<ResponseStructure<Student>> findByEmail(@PathVariable String email) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findByEmail(email),HttpStatus.FOUND);
    }

    //fetch by percentage greater than 60 or equal
    @GetMapping("/students/percentage/greater/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageGreaterThan(@PathVariable double percentage){
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByPercentageGreaterThan(percentage),HttpStatus.FOUND);
    }

    //fetch by percentage lesser than
    @GetMapping("/students/percentage/lesser/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageLesser(@PathVariable double percentage){
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByPercentageLesser(percentage),HttpStatus.FOUND);
    }

   //fetch by result
    @GetMapping("/students/result/{result}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByResult(@PathVariable String result){
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByResult(result),HttpStatus.FOUND);
    }

    //fetch by Maths and English
    @GetMapping("/student/percentage/maths/English/greater/{marks}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByMathsEnglishGreater(@PathVariable int marks){
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByMathsEnglishGreater(marks),HttpStatus.FOUND);
    }

    //delete 
    @DeleteMapping("/student/{id}")
    public ResponseEntity<ResponseStructure<Student>> delete(@PathVariable int id){
        return new ResponseEntity<ResponseStructure<Student>>(studentService.delete(id),HttpStatus.FOUND);
    }

    //update
    @PutMapping("/students")
    public ResponseEntity<ResponseStructure<Student>> update(@RequestBody Student student){
        return new ResponseEntity<ResponseStructure<Student>>(studentService.update(student),HttpStatus.CREATED);
    }
      


    // @GetMapping("/student/name/abc")
    // public ResponseEntity<ResponseStructure<List<Student>>> findByName(@RequestParam String name){
    //     return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByName(name),HttpStatus.FOUND);
    // }

    

    // @GetMapping("/students/result/distinction")
    // public ResponseEntity<ResponseStructure<List<Student>>> findByResult(@RequestParam String result){
    //     return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByResult(result),HttpStatus.FOUND);
    // }

    

    // @GetMapping("/student/percentage/between/60")
    // public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageBetween(@RequestParam double min, @RequestParam double max){
    //     return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByPercentageBetween(min, max),HttpStatus.FOUND);
    // }

    // @GetMapping("/students/1")
    // public ResponseEntity<ResponseStructure<Student>> deletById(@RequestParam int id) {
    //     return new ResponseEntity<ResponseStructure<Student>>(studentService.deletById(id),HttpStatus.FOUND);
    // }




    // @GetMapping("/studentId")
    // public Student getStudentById(@RequestParam int id) {
    //     Student student = null;
    //     try {
    //         student = studentService.getStudentById(id);
    //     } catch (StudentIdInvalidException | StudentNotFoundException e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return student;
    // }

   


    // @PutMapping("/editstudent")
    // public Student editStudentById(@RequestParam int id, @RequestBody Student studentDetails) {
    //     return studentService.editStudentById(id, studentDetails);
    // }

}
