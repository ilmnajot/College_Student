package uz.ilmnajot.college.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.Entity.User;
import uz.ilmnajot.college.config.jwt.JwtProvider;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.LoginRequest;
import uz.ilmnajot.college.model.request.UserRequest;
import uz.ilmnajot.college.model.response.LoginResponse;
import uz.ilmnajot.college.model.response.UserResponse;
import uz.ilmnajot.college.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, UserService userService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, MailService mailService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.mailService = mailService;
        this.modelMapper = modelMapper;
    }

    public ApiResponse register(UserRequest request) {

        if (userRepository.findByEmailAndDeletedFalse(request.getEmail()).isEmpty()) {
            User user = userService.saveUser(request);
            int nextedInt = new Random().nextInt(9999999);
            user.setEmailCode(String.valueOf(nextedInt).substring(0, 4));
            User saved = userRepository.save(user);
            mailService.sendMail(user.getUsername(), user.getEmailCode());
            UserResponse userResponse = modelMapper.map(saved, UserResponse.class);
            return new ApiResponse("successfully registered", true,userResponse);
        }
        return new ApiResponse("failed to register", false, "already registered user with this email " + request.getEmail());
    }


    public ApiResponse login(LoginRequest request) {
       try {
           UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                   request.getEmail(),
                   request.getPassword());
           Authentication authenticate = authenticationManager.authenticate(authenticationToken);
           User user = (User) authenticate.getPrincipal();
           String token = jwtProvider.generateToken(user);
           LoginResponse loginResponse = new LoginResponse();
           loginResponse.setToken(token);
           return new ApiResponse("successfully login to System", true, loginResponse);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ApiResponse("failed to login to System", false);
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmailAndDeletedFalse(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())){
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("successfully verified", true);
            }
            return new ApiResponse("failed to verify, email code does not match", false);
        }
        return new ApiResponse("there is no such user", false);
    }
}
