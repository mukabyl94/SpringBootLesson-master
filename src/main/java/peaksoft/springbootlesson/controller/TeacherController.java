package peaksoft.springbootlesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.TeacherRequest;
import peaksoft.springbootlesson.dto.TeacherResponse;
import peaksoft.springbootlesson.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @PostMapping
    public TeacherResponse create(@RequestBody TeacherRequest request){
        return teacherService.create(request);
    }
    @GetMapping("all")
    public List<TeacherResponse> getAll(){
        return teacherService.getAllTeachers();
    }
    @GetMapping("{id}")
    public TeacherResponse getTeacher(@PathVariable("id")Long id){
        return teacherService.getTeacherById(id);
    }
    @PutMapping("{id}")
    public TeacherResponse update(@PathVariable("id")Long id, @RequestBody TeacherRequest request){
        return teacherService.updateTeacher(id, request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        teacherService.deleteTeacher(id);
        return "Successfully deleted Teacher with id: "+id;
    }
}
