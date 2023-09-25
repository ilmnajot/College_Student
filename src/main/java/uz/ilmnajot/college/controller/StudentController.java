package uz.ilmnajot.college.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.StudentRequest;
import uz.ilmnajot.college.model.request.UserRequest;
import uz.ilmnajot.college.service.StudentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    private static final String CREATE_STUDENT = "/createStudent";
    private static final String GET_STUDENT = "/getStudent/{id}";
    private static final String GET_ALL_STUDENT = "/all";
    private static final String DELETE_STUDENT = "/deleteStudent/{id}";
    private static final String UPDATE_STUDENT = "/createStudent/{id}";

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(CREATE_STUDENT)
    public HttpEntity<ApiResponse> createStudent(@RequestBody StudentRequest request) {
        ApiResponse apiResponse = studentService.create(request);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_STUDENT)
    public HttpEntity<ApiResponse> getStudent(@PathVariable Long id) {
        ApiResponse apiResponse = studentService.getById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_ALL_STUDENT)
    public HttpEntity<ApiResponse> getAllUser() {
        ApiResponse apiResponse = studentService.getAll();
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(UPDATE_STUDENT)
    public HttpEntity<ApiResponse> editStudent(@RequestBody StudentRequest request, @PathVariable Long id) {
        ApiResponse apiResponse = studentService.update(request, id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping(DELETE_STUDENT)
    public HttpEntity<ApiResponse> deleteUser(@PathVariable  Long id) {
        ApiResponse apiResponse = studentService.deleteById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
