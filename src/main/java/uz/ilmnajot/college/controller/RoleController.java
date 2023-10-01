package uz.ilmnajot.college.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.college.model.request.RoleRequest;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.StudentRequest;
import uz.ilmnajot.college.service.RoleService;

@RestController
@RequestMapping("/api/rols")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    private static final String ADD_ROLE = "/add_role";
    private static final String GET_ROLE = "/get_role/{id}";
    private static final String GET_ALL_ROLE = "/all";
    private static final String DELETE_ROLE = "/delete_role/{id}";
    private static final String UPDATE_ROLE = "/update_role/{id}";


    @PostMapping(ADD_ROLE)
    public HttpEntity<ApiResponse> addRole(@RequestBody RoleRequest request) {
        ApiResponse apiResponse = roleService.create(request);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_ROLE)
    public HttpEntity<ApiResponse> getRole(@PathVariable Long id) {
        ApiResponse apiResponse = roleService.getById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_ALL_ROLE)
    public HttpEntity<ApiResponse> getAllRole(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        ApiResponse apiResponse = roleService.getAll(page, size);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(UPDATE_ROLE)
    public HttpEntity<ApiResponse> editRole(@RequestBody RoleRequest request, @PathVariable Long id) {
        ApiResponse apiResponse = roleService.update(request, id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping(DELETE_ROLE)
    public HttpEntity<ApiResponse> deleteRole(@PathVariable  Long id) {
        ApiResponse apiResponse = roleService.deleteById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
