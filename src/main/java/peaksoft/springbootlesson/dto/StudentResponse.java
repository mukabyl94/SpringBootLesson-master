package peaksoft.springbootlesson.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.springbootlesson.entity.Group;

@Getter
@Setter
public class StudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Group group;
    private String roleName;
}
