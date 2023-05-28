package peaksoft.springbootlesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.StudentRequest;
import peaksoft.springbootlesson.dto.StudentResponse;
import peaksoft.springbootlesson.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request){
        return studentService.create(request);
    }
    @GetMapping("all")
    public List<StudentResponse> getAll(){
        return studentService.getAllStudents();
    }
    @GetMapping("{id}")
    public StudentResponse getStudent(@PathVariable("id")Long id){
        return studentService.getStudentById(id);
    }
    @PutMapping("{id}")
    public StudentResponse update(@PathVariable("id")Long id, @RequestBody StudentRequest request){
        return studentService.updateStudent(id, request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        studentService.deleteStudent(id);
        return "Successfully deleted Student with id: "+id;
    }
    
}
