package com.bakir.patients_mvc.web;

import com.bakir.patients_mvc.entities.MyUser;
import com.bakir.patients_mvc.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
@PostMapping("/register/user")
    public MyUser register(@RequestBody MyUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));//encoder password
     return myUserRepository.save(user);
}


}
