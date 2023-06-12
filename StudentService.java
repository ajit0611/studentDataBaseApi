package org.jspiders.student_db_api.service;

import org.jspiders.student_db_api.domain.Student;
import org.jspiders.student_db_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    // 1. add new student
    public Student addNewStudent(Student s) {
        Student student = repository.save(s);
        return student;
    }

    // 2. Display all record
    public List<Student> displayAllStudents() {
        List<Student> studentList = repository.findAll();
        return studentList;
    }

    // 3 get student by id

    public Student getStudentById(int id) {
        Student s= repository.findById(id).orElse(null);
        return s;
    }

    //4-- display student By Name
    public List<Student> getStudentByName(String name) {

        return repository.findByNameIgnoreCase(name);
    }

    //  5 get student by stream
    public List<String> getStudentNamesByStream(String stream) {
        List<Student> studentList = repository.findByStreamIgnoreCase(stream);
        return studentList.stream()
                .filter(s -> s.getStream().equalsIgnoreCase(stream))
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    //6  get student by course and display name and stream

    public List<String> getStudentByCourse(String course) {
        List<Student> studentList = repository.findByCourseIgnoreCase(course);
        return studentList.stream()
                .filter(s -> s.getCourse().equalsIgnoreCase(course))
                .map(s -> ("Name: " + s.getName() + ", Stream: " + s.getStream()))
                .collect(Collectors.toList());
    }
    // 7 update student by id

    public void updateStudent(int id, Student s) {
        Optional<Student> studentList = repository.findById(id);
        studentList.ifPresent(student -> {
            student.setId(s.getId());
            student.setName(s.getName());
            student.setStream(s.getStream());
            student.setPercentage(s.getPercentage());
            student.setCourse(s.getCourse());
            repository.save(student);
        });

    }

    // 8--delete student record using id
    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    //find Name And Stream;
    public List<Student> getStudentByNameAndStream(String name,String stream ){
        return repository.getByNameAndStream(name,stream);
    }

    // fetch Student detail using student course and student
    public List<Student> getStudentByCourseAndStream(String course,String stream ){
        return repository.getByCourseAndStream(course,stream);
    }

        // get student having percentage less than ? Dynamic value
    public List<Student> getStudentPercentageLess(double percentage){
        return repository.getStudentByPercentageLessThan(percentage);
    }

    // Get all stream name
    public List<String> getStudentStream(String stream){
        return repository.getAllStreams();
    }

    //get Student name by course name
    public List<String> getStudentNameByCourse(String course){
        return repository.getStudentByCourse(course);
    }

    // get student name having contain alphabet
    public List<String> getStudentNameContains(String name){
        return repository.getStudentsByNameContaining(name);
    }

    // delete student by using stream
    public void deleteStudent(String stream){
        repository.deleteStudents(stream);
    }

}
