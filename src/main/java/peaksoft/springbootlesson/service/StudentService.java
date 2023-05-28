package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.StudentRequest;
import peaksoft.springbootlesson.dto.StudentResponse;
import peaksoft.springbootlesson.entity.Group;
import peaksoft.springbootlesson.entity.Role;
import peaksoft.springbootlesson.entity.User;
import peaksoft.springbootlesson.repository.GroupRepository;
import peaksoft.springbootlesson.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    public StudentResponse create(StudentRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.STUDENT);
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public StudentResponse mapToResponse(User user){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFirstName(user.getFirstName());
        studentResponse.setLastName(user.getLastName());
        studentResponse.setEmail(user.getEmail());
        studentResponse.setPassword(user.getPassword());
        studentResponse.setRoleName(user.getRole().name());
        studentResponse.setGroup(user.getGroup());
        return studentResponse;
    }
    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User user : userRepository.findAll()){
            studentResponses.add(mapToResponse(user));
        }
        return studentResponses;
    }
    public StudentResponse getStudentById(Long studentId){
        User user = userRepository.findById(studentId).get();
        return mapToResponse(user);
    }
    public StudentResponse updateStudent(Long id, StudentRequest request){
        User user = userRepository.findById(id).get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Group group = groupRepository.findById(request.getGroupId()).get();
        user.setGroup(group);
        userRepository.save(user);
        return mapToResponse(user);
    }
    public void deleteStudent(Long studentId){
        userRepository.deleteById(studentId);
    }

}
