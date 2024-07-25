package jwtexample.services;

import jwtexample.dto.JwtAuthenticationResponse;
import jwtexample.dto.SignInRequest;
import jwtexample.dto.SignUpRequest;
import jwtexample.models.Role;
import jwtexample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwtexample.repositories.UserRepository;


@Service
public class AuthenticationService {
    @Autowired
  private  UserRepository userRepository;
    @Autowired
  private  UserService userService;
    @Autowired
  private  PasswordEncoder passwordEncoder;
    @Autowired
  private  JwtService jwtService;
    @Autowired
  private  AuthenticationManager authenticationManager;

  public JwtAuthenticationResponse signup(SignUpRequest request) {
      User user = new User(request.getFirstName(),
              request.getLastName(),
              request.getEmail(),
              passwordEncoder.encode(request.getPassword()),
              Role.ROLE_USER);

      user = userService.save(user);
      String jwt = jwtService.generateToken(user);

      JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt);
      return jwtAuthenticationResponse;
  }


  public JwtAuthenticationResponse signin(SignInRequest request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      User user = userRepository.findByEmail(request.getEmail())
              .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
      String jwt = jwtService.generateToken(user);
      JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt);
      return jwtAuthenticationResponse;
  }
  
}
