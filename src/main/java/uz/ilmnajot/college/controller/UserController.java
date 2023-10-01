package uz.ilmnajot.college.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.UserRequest;
import uz.ilmnajot.college.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public HttpEntity<ApiResponse> createUser(@RequestBody UserRequest request) {
        ApiResponse apiResponse = userService.create(request);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/getUser/{id}")
    public HttpEntity<ApiResponse> getUser(@PathVariable UUID id) {
        ApiResponse apiResponse = userService.getById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/all")
    public HttpEntity<ApiResponse> getAllUser() {
        ApiResponse apiResponse = userService.getAll();
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping("updateUser/{id}")
    public HttpEntity<ApiResponse> editUser(@RequestBody UserRequest request, @PathVariable UUID id) {
        ApiResponse apiResponse = userService.update(request, id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping("/deleteUser/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable  UUID id) {
        ApiResponse apiResponse = userService.deleteById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
