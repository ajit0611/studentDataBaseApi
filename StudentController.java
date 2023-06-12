package org.jspiders.student_db_api.controller;

import org.jspiders.student_db_api.domain.Student;
import org.jspiders.student_db_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
        @Autowired
        private StudentService service;

        @PostMapping("/students/add")
        public void addNewStudents(@RequestBody Student s){
            service.addNewStudent(s);
        }

        @GetMapping("/students")
        public List<Student> getAllStudent(){
            return service.displayAllStudents();
        }

        @GetMapping("/students/{id}")
        public Student getStudentById(@PathVariable(value = "id") int id){
            return service.getStudentById(id);
        }

        @GetMapping("/students/by-name/{name}")
        public List<Student> getStudentByName(@PathVariable(value = "name") String name){
            return service.getStudentByName(name);
        }

        @GetMapping("/students/names/by-stream/{stream}")
        public List<String> getStudentByStream(@PathVariable(value = "stream") String stream){
            return service.getStudentNamesByStream(stream);
        }


        @GetMapping("/students/by-course/{course}")
        public List<String> getStudentByCourse(@PathVariable(value = "course") String course){
            return service.getStudentByCourse(course);
        }

       @PutMapping("/students/update/by-id/{id}")
        public void updateStudent(@PathVariable(value = "id")int id,@RequestBody Student s){
            service.updateStudent(id,s);
        }

        @DeleteMapping("/students/delete/by-id/{id}")
        public void deleteStudent(@PathVariable(value = "id") int id){
            service.deleteStudent(id);
        }

        @GetMapping("/students/by-NameAndStream/{name}/{stream}")
        public List<Student> getStudentByNameAndStream(@PathVariable(value = "name") String name, @PathVariable(value = "stream") String stream){
            return service.getStudentByNameAndStream(name,stream);
        }
        @GetMapping("/students/by-CourseAndStream/{course}/{stream}")
        public List<Student> getStudentByCourse(@PathVariable(value = "course") String course, @PathVariable(value = "stream") String stream){
            return service.getStudentByCourseAndStream(course,stream);
        }

        @GetMapping("/students/by-percentage/{percentage}")
        public List<Student> getStudentByPercentageLess(@PathVariable(value = "percentage") double percentage){
            return service.getStudentPercentageLess(percentage);
        }

        @GetMapping("/students/by-stream/{stream}")
        public List<String> getStudentByAllStream(@PathVariable(value = "stream") String stream){
            return service.getStudentStream(stream);
        }

        @GetMapping("/studentsName/by-course/{course}")
        public List<String> getStudentNameByCourse(@PathVariable(value = "course") String course){
            return service.getStudentNameByCourse(course);
        }
        @GetMapping("/students/by-contain/{name}")
        public List<String> getStudentNameContains(@PathVariable(value = "name") String name) {
            return service.getStudentNameContains(name);
        }

        @DeleteMapping("/students/delete_student/{stream}")
        public void deleteStudent(@PathVariable(value = "stream") String stream) {
        service.deleteStudent(stream);
    }
}
