package org.pushpa.boot_crud.service;

import java.util.ArrayList;
import java.util.List;

import org.pushpa.boot_crud.dao.StudentDao;
import org.pushpa.boot_crud.dto.Student;
import org.pushpa.boot_crud.exception.DataNotFoundException;
import org.pushpa.boot_crud.exception.DataShouldNotRepeatException;
import org.pushpa.boot_crud.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public ResponseStructure<Student> save(Student student) {
        Student student1 = studentDao.findByMobile(student.getMobile());
        Student student2 = studentDao.findByEmail(student.getEmail());

        if (student1 == null && student2 == null) {
            student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
            if (student.getMaths() < 35 || student.getEnglish() < 35 || student.getScience() < 35) {
                student.setResult("fail");
            } else {
                if (student.getPercentage() >= 85)
                    student.setResult("Distinction");
                else if (student.getPercentage() >= 60)
                    student.setResult("First class");
                else
                    student.setResult("Second class");
            }
            ResponseStructure<Student> structure = new ResponseStructure<>();
            structure.setData(studentDao.save(student));
            structure.setStatus(HttpStatus.CREATED.value());
            structure.setMessage("data saved successfully");
            return structure;
        } else {
            if (student1 == null) {
                throw new DataShouldNotRepeatException("Email Should not be repeated:" + student.getEmail());
            } else if (student2 == null) {
                throw new DataShouldNotRepeatException("Moblie Should not be repeated:" + student.getMobile());
            } else {
                throw new DataShouldNotRepeatException(
                        "Moblie and Email Should not be repeated:" + student.getEmail() + " " + student.getMobile());
            }
        }
    }

    public ResponseStructure<List<Student>> saveManyStudents(List<Student> students) {
        List<Student> list = new ArrayList<Student>();
        for (Student student : students) {
            Student student1 = studentDao.findByMobile(student.getMobile());
            Student student2 = studentDao.findByEmail(student.getEmail());
            if (student1 == null && student2 == null)
                list.add(student);
        }
        if (list.isEmpty()) {
            throw new DataShouldNotRepeatException("All Data Already exists");
        } else {
            for (Student student : list) {
                student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
                if (student.getMaths() < 35 || student.getEnglish() < 35 || student.getScience() < 35) {
                    student.setResult("fail");
                } else {
                    if (student.getPercentage() >= 85)
                        student.setResult("Distinction");
                    else if (student.getPercentage() >= 60)
                        student.setResult("First class");
                    else
                        student.setResult("Second class");
                }
            }
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            if (list.size() != students.size()) 
                structure.setMessage(
                        list.size() + " data saved, " + (students.size() - list.size()) + " data not saved");
            else 
                structure.setMessage("all data saved success");
               
               
                structure.setData(studentDao.saveManyStudents(list));
                structure.setStatus(HttpStatus.CREATED.value());
            
            return structure;
        }
    }

    public ResponseStructure<List<Student>> fetchAllStudents() {
        List<Student> list = studentDao.fetchAllStudents();
        if (list.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found successfully");
            return structure;
        }

    }

    public ResponseStructure<Student> findById(int id) {
        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setData(studentDao.findById(id));
        structure.setStatus(HttpStatus.FOUND.value());
        structure.setMessage("Data Found Successfully");
        return structure;

    }

    public ResponseStructure<Student> findByMoblie(long moblie) {
        Student student = studentDao.findByMobile(moblie);
        if (student == null) {
            throw new DataNotFoundException("No data found with moblie " + moblie);
        } else {
            ResponseStructure<Student> structure = new ResponseStructure<>();
            structure.setData(student);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully");
            return structure;
        }
    }

    public ResponseStructure<Student> findByEmail(String email) {
        Student student = studentDao.findByEmail(email);
        if (student == null) {
            throw new DataNotFoundException("No data found with email " + email);
        } else {
            ResponseStructure<Student> structure = new ResponseStructure<>();
            structure.setData(student);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByPercentageGreaterThan(double percentage) {
        List<Student> list = studentDao.findByPercentageGreaterThan(percentage);
        if (list.isEmpty()) {
            throw new DataNotFoundException("No students record,whose percentage is greater than " + percentage);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully, " + list.size() + " data found");
            return structure;
        }

    }

    public ResponseStructure<List<Student>> findByPercentageLesser(double percentage) {
        List<Student> list = studentDao.findByPercentageLesser(percentage);
        if (list.isEmpty()) {
            throw new DataNotFoundException("No students record,whose percentage is lesser than " + percentage);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully, " + list.size() + " data found");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByResult(String result) {
        List<Student> list = studentDao.findByResult(result);
        if (list.isEmpty()) {
            throw new DataNotFoundException("No students record,whose result is " + result);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully, " + list.size() + " data is present");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByMathsEnglishGreater(int marks) {
        List<Student> list = studentDao.findByMathsEnglishGreater(marks);
        if (list.isEmpty()) {
            throw new DataNotFoundException("No students record,whose maths and english is " + marks);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data Found Successfully, " + list.size() + " data is present");
            return structure;
        }
    }

    public ResponseStructure<Student> delete(int id) {
        ResponseStructure<Student> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Data deleted with id " + id);
		structure.setData(studentDao.findById(id));

		studentDao.delete(studentDao.findById(id));
		return structure;
    }

    public ResponseStructure<Student> update(Student student) {
        studentDao.findById(student.getId());
        student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
        if (student.getMaths() < 35 || student.getEnglish() < 35 || student.getScience() < 35) {
            student.setResult("fail");
        } else {
            if (student.getPercentage() >= 85)
                student.setResult("Distinction");
            else if (student.getPercentage() >= 60)
                student.setResult("First class");
            else
                student.setResult("Second class");
        }
        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setData(studentDao.save(student));
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setMessage("data saved successfully");
        return structure;
    }

}

// public Student getStudentById(int id) throws StudentIdInvalidException {
// System.out.println(id);
// if (id == 0) {
// throw new StudentIdInvalidException("Invalid id " + id);
// }
// Student student = this.studentDao.getStudentById(id);
// return student;

// }

// public String deleteStudentById(int id) throws StudentIdInvalidException {
// if (id == 0) {
// throw new StudentIdInvalidException("Invalid id " + id);
// }
// String message = this.studentDao.deleteStudentById(id);
// return message;
// }

// public Student editStudentById(int id, Student studentDetails) throws
// StudentIdInvalidException {
// if(id==0){
// throw new StudentIdInvalidException("Invalid id " + id);
// }
// studentDetails.setPercentage((studentDetails.getMaths() +
// studentDetails.getEnglish() + studentDetails.getScience()) / 3.0);
// if (studentDetails.getMaths() < 35 || studentDetails.getEnglish() < 35 ||
// studentDetails.getScience() < 35) {
// studentDetails.setResult("fail");
// } else {
// if (studentDetails.getPercentage() >= 85)
// studentDetails.setResult("Distinction");
// else if (studentDetails.getPercentage() >= 60)
// studentDetails.setResult("First class");
// else
// studentDetails.setResult("Second class");
// }
// Student student= studentDao.editStudentById(id, studentDetails);
// return student ;
// }
