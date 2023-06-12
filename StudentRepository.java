package org.jspiders.student_db_api.repository;

import org.jspiders.student_db_api.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByNameIgnoreCase(String name);

    List<Student> findByStreamIgnoreCase(String stream);

    List<Student> findByCourseIgnoreCase(String course);

    List<Student> getByNameAndStream(String name, String stream);

    List<Student> getByCourseAndStream(String course, String stream);

    List<Student> getStudentByPercentageLessThan(double percentage);

    @Query("select s.stream from Student s")
    List<String> getAllStreams();

    @Query("select s.name from Student s where s.course=:course")
    List<String> getStudentByCourse(@Param("course") String course);

    //display name contain whose contain dynamic ?
    @Query("SELECT s.name FROM Student s WHERE s.name LIKE CONCAT('%', :name, '%')")
    List<String> getStudentsByNameContaining(@Param("name") String name);

    //display all student details write native sql query
    @Query(value = "select * from student_info",nativeQuery = true)
    List<Student> getAllDetails();

    //delete all students having specific stream
    @Transactional
    @Query("DELETE FROM Student s where s.stream=:stream")
    void deleteStudents(@Param("stream") String stream);
}
