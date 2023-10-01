package uz.ilmnajot.college.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.college.Entity.Role;
import uz.ilmnajot.college.enums.Permissions;
import uz.ilmnajot.college.exception.RoleNotFoundException;
import uz.ilmnajot.college.model.common.ApiResponse;
import uz.ilmnajot.college.model.request.RoleRequest;
import uz.ilmnajot.college.model.response.RoleResponseList;
import uz.ilmnajot.college.repository.RoleRepository;
import uz.ilmnajot.college.utils.Utils;

import java.util.List;
import java.util.Optional;

import static uz.ilmnajot.college.utils.Utils.SUCCESSFULLY;

@Service
public class RoleService implements BaseService<RoleRequest, Long> {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponse create(RoleRequest roleRequest) {
        Optional<Role> optionalRole = roleRepository.findRoleByName(roleRequest.getName());
        if (optionalRole.isPresent()) {
            return new ApiResponse("already exists this role ", false, optionalRole.get());
        }
        Role role = modelMapper.map(roleRequest, Role.class);
        setRole(roleRequest, role);
        Role savedRole = roleRepository.save(role);
        return new ApiResponse("success", true, savedRole);
    }

    @Override
    public ApiResponse getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()
                -> new RoleNotFoundException("Role " + id + " not found"));
        return new ApiResponse(SUCCESSFULLY, true, role);
    }

    @Override
    public ApiResponse getAll() {
        return null;
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()
                -> new RoleNotFoundException(Utils.REASON_NOT_FOUND));
        role.setActive(false);
        role.setDeleted(true);
        roleRepository.save(role);
        return new ApiResponse(SUCCESSFULLY, true);
    }

    @Override
    public ApiResponse update(RoleRequest roleRequest, Long id) {

        return null;
    }

    public void setRole(RoleRequest roleRequest, Role role) {
        if (roleRepository.findByNameAndActiveTrue(roleRequest.getName()).isPresent()) {
            throw new RoleNotFoundException("role not found");
        }
        List<Permissions> permissions = roleRequest.getPermissions();

        role.setName(roleRequest.getName());
        role.setRoleName(roleRequest.getRole().getRoleName());
        role.setActive(true);
        role.setPermissions(permissions);
    }

    public ApiResponse getAll(int page, int size) {
       Pageable pageable = PageRequest.of(page, size);
        Page<Role> rolePage = roleRepository.findAllByActiveTrue(pageable);
        RoleResponseList roleResponseList = new RoleResponseList(rolePage.getContent(), rolePage.getSize(), rolePage.getTotalPages(), rolePage.getNumberOfElements());
        return new ApiResponse("success" + roleResponseList, true, pageable);
    }
}
