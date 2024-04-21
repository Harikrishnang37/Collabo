//package com.penguins.collabo.controllers;
//
//import com.penguins.collabo.Repository.RoleRepository;
//import com.penguins.collabo.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private AuthenticationManager authenticationManager;
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;
//    private JWTGenerator jwtGenerator;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
//                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtGenerator = jwtGenerator;
//    }
//
//    @PostMapping("login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsername(),
//                        loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
//    }
//
//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
//        if (userRepository.existsByUsername(registerDto.getUsername())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
//
//        Role roles = roleRepository.findByName("USER").get();
//        user.setRoles(Collections.singletonList(roles));
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
//    }
//}