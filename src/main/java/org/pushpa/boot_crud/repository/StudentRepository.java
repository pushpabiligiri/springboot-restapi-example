package org.pushpa.boot_crud.repository;

import java.util.List;

import org.pushpa.boot_crud.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Integer>{

    List<Student> findByName(String name);

    Student findByMobile(long mobile);

    Student findByEmail(String email);

    List<Student> findByResult(String result);


    List<Student> findByPercentageLessThan(double percentage);

    List<Student> findByPercentageBetween(double min, double max);

    List<Student> findByPercentageGreaterThanEqual(double percentage);

    List<Student> findByPercentageLessThanEqual(double percentage);

   // @Query("select x from Student x where maths>=:?1 and english>=:?1")
    List<Student> findByMathsGreaterThanEqualAndEnglishGreaterThanEqual(int marks1, int marks2);
    
}
