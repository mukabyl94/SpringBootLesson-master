package peaksoft.springbootlesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlesson.dto.GroupRequest;
import peaksoft.springbootlesson.dto.GroupResponse;
import peaksoft.springbootlesson.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public GroupResponse create(@RequestBody GroupRequest request){
        return groupService.create(request);
    }
    @GetMapping("all")
    public List<GroupResponse> getAll(){
        return groupService.getAllGroups();
    }
    @GetMapping("{id}")
    public GroupResponse getGroup(@PathVariable("id")Long id){
        return groupService.getGroupById(id);
    }
    @PutMapping("{id}")
    public GroupResponse update(@PathVariable("id")Long id, @RequestBody GroupRequest request){
        return groupService.updateGroup(id, request);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        groupService.deleteGroup(id);
        return "Successfully deleted Group with id: "+id;
    }
}
