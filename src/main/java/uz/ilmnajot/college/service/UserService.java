package uz.ilmnajot.college.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.Entity.User;
import uz.ilmnajot.college.enums.RoleName;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.UserRequest;
import uz.ilmnajot.college.model.response.UserResponse;
import uz.ilmnajot.college.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements BaseService<UserRequest, UUID> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse create(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmailAndDeletedFalse(userRequest.getEmail());
        if (optionalUser.isPresent()){
            return new ApiResponse("user already exists", false);
        }
        User user = saveUser(userRequest);
        return new ApiResponse("user created", true, modelMapper.map(user, UserResponse.class));
    }

    @Override
    public ApiResponse getById(UUID uuid) {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            return new ApiResponse("sucess", true, userResponse);
        }
        return new ApiResponse("there is no such user with this id: " + uuid, false);
    }

    @Override
    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }
        return new ApiResponse("success", true, userResponses);
    }

    @Override
    public ApiResponse deleteById(UUID uuid) {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userRepository.save(user);
            return new ApiResponse("successfully deleted user", true);
        }
        return new ApiResponse("not found", false);
    }

    @Override
    public ApiResponse update(UserRequest userRequest, UUID uuid) {
        return null;
    }

    public User saveUser(UserRequest userRequest){
        return userRepository.save(User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .region(userRequest.getRegion())
                .roleName(RoleName.USER)
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build());
    }

}
