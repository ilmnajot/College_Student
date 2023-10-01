package uz.ilmnajot.college.controller;

import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.CollegeRequest;
import uz.ilmnajot.college.model.request.StudentRequest;
import uz.ilmnajot.college.service.CollegeService;

import static uz.ilmnajot.college.enums.Permissions.*;
import static uz.ilmnajot.college.utils.Utils.*;
import static uz.ilmnajot.college.utils.Utils.ADD_COLLEGE;
import static uz.ilmnajot.college.utils.Utils.DELETE_COLLEGE;
import static uz.ilmnajot.college.utils.Utils.GET_ALL_COLLEGE;
import static uz.ilmnajot.college.utils.Utils.GET_COLLEGE;
import static uz.ilmnajot.college.utils.Utils.UPDATE_COLLEGE;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {
    private final CollegeService collegeService;
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @PostMapping(ADD_COLLEGE)
    public HttpEntity<ApiResponse> addCollege(@RequestBody CollegeRequest request){
        ApiResponse apiResponse = collegeService.create(request);
        return apiResponse !=null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_COLLEGE)
    public HttpEntity<ApiResponse> getCollege(@PathVariable Long id) {
        ApiResponse apiResponse = collegeService.getById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(GET_ALL_COLLEGE)
    public HttpEntity<ApiResponse> getAllCollege() {
        ApiResponse apiResponse = collegeService.getAll(

        );
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(UPDATE_COLLEGE)
    public HttpEntity<ApiResponse> editCollege(@RequestBody CollegeRequest request, @PathVariable Long id) {
        ApiResponse apiResponse = collegeService.update(request, id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping(DELETE_COLLEGE)
    public HttpEntity<ApiResponse> deleteCollege(@PathVariable  Long id) {
        ApiResponse apiResponse = collegeService.deleteById(id);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
