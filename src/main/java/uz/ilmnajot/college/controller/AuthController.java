package uz.ilmnajot.college.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.LoginRequest;
import uz.ilmnajot.college.model.request.UserRequest;
import uz.ilmnajot.college.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private static  final String  REGISTER_USER = "/registerUser";
    private static  final String  LOGIN_TO_SYSTEM = "/login";

    private static final String VERIFY_USER_BY_EMAIL = "/verifyEmail";

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping(REGISTER_USER)
    public HttpEntity<ApiResponse> register(@RequestBody UserRequest request){
        ApiResponse register = authService.register(request);
        return register !=null
                ? ResponseEntity.ok(register)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping(LOGIN_TO_SYSTEM)
    public HttpEntity<ApiResponse> login(@RequestBody LoginRequest request){
        ApiResponse login = authService.login(request);
        return login !=null
                ? ResponseEntity.ok(login)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping(VERIFY_USER_BY_EMAIL)
    public HttpEntity<ApiResponse> verifyEmail(
            @RequestParam String email,
            @RequestParam String emailCode){
        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
        return apiResponse !=null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}
