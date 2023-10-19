package org.pushpa.boot_crud.dao;

import java.util.List;
import java.util.Optional;

import org.pushpa.boot_crud.dto.Student;
import org.pushpa.boot_crud.exception.DataNotFoundException;
import org.pushpa.boot_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveManyStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow(() -> {throw new DataNotFoundException("Data not found with ID: " + id);});
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findByMobile(long mobile) {
        return studentRepository.findByMobile(mobile);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> findByPercentageGreaterThan(double percentage) {
        return studentRepository.findByPercentageGreaterThanEqual(percentage);
    }

    public List<Student> findByPercentageLesser(double percentage) {
        return studentRepository.findByPercentageLessThanEqual(percentage);
    }

    public List<Student> findByResult(String result) {
        return studentRepository.findByResult(result);
    }

    public List<Student> findByMathsEnglishGreater(int marks) {
        return studentRepository.findByMathsGreaterThanEqualAndEnglishGreaterThanEqual(marks,marks);
    }

    public void delete(Student findById) {
         studentRepository.delete(findById);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }
    

    

    // public List<Student> findByPercentageBetween(double min, double max){
    // return studentRepository.findByPercentageBetween(min, max);
    // }

    // public void deleteById(int id){
    // studentRepository.deleteById(id);
    // }

    // public Student getStudentById(int id) throws StudentNotFoundException {
    // Optional<Student> student = this.studentRepository.findById(id);
    // if (!student.isPresent()) { // is not present
    // throw new StudentNotFoundException("Student not found for id " + id);
    // }
    // return student.get();
    // }

    // public String deleteStudentById(int id) throws StudentNotFoundException {
    // Optional<Student> student = this.studentRepository.findById(id);
    // if (!student.isPresent()) {
    // throw new StudentNotFoundException("Student not found for id " + id);
    // }
    // studentRepository.deleteById(id);
    // return "Data deleted successfully";
    // }

    // public Student editStudentById(int id, Student studentDetails) throws
    // StudentNotFoundException {
    // Optional<Student> student= this.studentRepository.findById(id);
    // if (!student.isPresent()) {
    // throw new StudentNotFoundException("Student not found for id " + id);
    // }
    // student.setName(studentDetails.getName());
    // student.get().setEmail(studentDetails.getEmail());
    // student.get().setMobile(studentDetails.getMobile());
    // student.get().setMaths(studentDetails.getMaths());
    // student.get().setEnglish(studentDetails.getEnglish());
    // student.get().setScience(studentDetails.getScience());
    // return studentRepository.save(student);
    // }

}
