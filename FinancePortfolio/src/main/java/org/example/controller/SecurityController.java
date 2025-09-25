package org.example.controller;

import org.example.dto.ApiResponse;
import org.example.dto.req.JWTRequest;
import org.example.dto.req.SignUpDTO;
import org.example.dto.response.JWTResponse;
import org.example.security.JwtHelper;
import org.example.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.ORIGIN;

@CrossOrigin(origins = "https://finance-tracker-coral-three.vercel.app")
@RestController
@RequestMapping("/api/auth")
public class SecurityController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody JWTRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails.getUsername());
       var user = userService.findUserByEmail(userDetails.getUsername());
        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).userId(user.getUserId()).build();
        return ResponseEntity.ok(new ApiResponse<>(
                "User Login successfully",
                HttpStatus.OK,
                response
        ));
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exceptionHandler() {
        return new ResponseEntity<>("Credentials Invalid !!", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> createUser(@RequestBody SignUpDTO user){
        var apiResponse =  new ApiResponse<>();
        userService.createUser(user);
        apiResponse.setMessage("User Created Successfully");
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

}
