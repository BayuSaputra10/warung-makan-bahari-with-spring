package warungmakanbahariapi.wmbahari.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.ERole;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.entity.UserRole;
import warungmakanbahariapi.wmbahari.model.request.AuthRequest;
import warungmakanbahariapi.wmbahari.model.request.CustomerRequest;
import warungmakanbahariapi.wmbahari.model.request.UserRequest;
import warungmakanbahariapi.wmbahari.model.response.UserResponse;
import warungmakanbahariapi.wmbahari.repository.UserRepository;
import warungmakanbahariapi.wmbahari.security.JwtUtils;
import warungmakanbahariapi.wmbahari.service.AuthService;
import warungmakanbahariapi.wmbahari.service.CustomerService;
import warungmakanbahariapi.wmbahari.service.RoleService;
import warungmakanbahariapi.wmbahari.service.UserService;
import warungmakanbahariapi.wmbahari.utils.ValidationUtils;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    private final CustomerService customerService;

    private final ValidationUtils validationUtils;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void initSuperAdmin(){
        Optional<User> optionalUserCredential = userRepository
                .findByEmail("superadmin@gamil.com");
        if (optionalUserCredential.isPresent()) return;

        UserRole roleSuperAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);

        String hashPassword = passwordEncoder.encode("password");

        User userCredential = User.builder()
                .username("superadmin@gmail.com")
                .password(hashPassword)
                .role((List<Role>) roleSuperAdmin)
                .build();

        userRepository.saveAndFlush(userCredential);

        Customer customer = Customer.builder()
                .user(userCredential)
                .build();

        CustomerRequest customerRequest = CustomerRequest.builder()
                .phoneNumber(customer.getPhoneNumber())
                .name(customer.getName())
                .build();
        customerService.create(customerRequest);

    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(AuthRequest request) {
        validationUtils.validate(request);

        UserRole roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        User userCredential = User.builder()
                .username(request.getEmail())
                .password(hashPassword)
                .role((List<Role>) roleCustomer)
                .build();

        userRepository.saveAndFlush(userCredential);

        Customer customer = Customer.builder()
                .user(userCredential)
                .build();
        CustomerRequest customerRequest = CustomerRequest.builder()
                .phoneNumber(customer.getPhoneNumber())
                .name(customer.getName())
                .build();
        customerService.create(customerRequest);

        return toUserResponse(userCredential);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public User registerAdmin(AuthRequest request) {
        validationUtils.validate(request);

        UserRole roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        User userCredential = User.builder()
                .username(request.getEmail())
                .password(hashPassword)
                .role((List<Role>) roleAdmin)
                .build();

        userRepository.saveAndFlush(userCredential);

        Customer customer = Customer.builder()
                .user(userCredential)
                .build();

        CustomerRequest customerRequest = CustomerRequest.builder()
                .phoneNumber(customer.getPhoneNumber())
                .name(customer.getName())
                .build();
        customerService.create(customerRequest);

        return toUserResponse(userCredential);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(AuthRequest request) {
        validationUtils.validate(request);


        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        User userCredential = (User) authenticate.getPrincipal();
        return jwtUtils.generateToken(userCredential);
    }

    private static User toUserResponse(User userCredential) {
        List<String> responseRoles = userCredential.getRole()
                .stream()
                .map(Role::getRoleName).toList();

        User build = User.builder()
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .build();


        return build;
    }
}
