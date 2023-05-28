package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import peaksoft.springbootlesson.dto.TeacherRequest;
import peaksoft.springbootlesson.dto.TeacherResponse;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.CourseRepository;
import peaksoft.springbootlesson.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TeacherResponse create(TeacherRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.INSTRUCTOR);
        Course course = courseRepository.findById(request.getCourseId()).get();
        user.setCourse(course);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public TeacherResponse mapToResponse(User user){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setFirstName(user.getFirstName());
        teacherResponse.setLastName(user.getLastName());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setPassword(user.getPassword());
        teacherResponse.setRoleName(user.getRole().name());
        teacherResponse.setCourse(user.getCourse());
        return teacherResponse;
    }
    public List<TeacherResponse> getAllTeachers(){
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (User user : userRepository.findAll()){
            teacherResponses.add(mapToResponse(user));
        }
        return teacherResponses;
    }
    public TeacherResponse getTeacherById(Long teacherId){
        User user = userRepository.findById(teacherId).get();
        return mapToResponse(user);
    }
    public TeacherResponse updateTeacher(Long id, TeacherRequest request){
        User user = userRepository.findById(id).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Course course = courseRepository.findById(request.getCourseId()).get();
        user.setCourse(course);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public void deleteTeacher(Long teacherId){
        userRepository.deleteById(teacherId);
    }


//    if(user == null){
//        log.error("User not found");
//        throw new NotFoundException("User not found");
//    }

}
