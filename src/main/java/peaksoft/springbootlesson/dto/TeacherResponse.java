package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Course;

@Getter
@Setter
public class TeacherResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Course course;
    private String roleName;
}
