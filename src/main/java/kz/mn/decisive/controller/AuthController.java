package kz.mn.decisive.controller;

import kz.mn.decisive.config.jwt.JwtProvider;
import kz.mn.decisive.dto.AuthRequest;
import kz.mn.decisive.dto.AuthResponse;
import kz.mn.decisive.dto.RegistrationRequest;
import kz.mn.decisive.model.Decider;
import kz.mn.decisive.service.DeciderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private DeciderService deciderService;
    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest) {
        Decider u = new Decider();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getUsername());
        u.setIsActive(true);
        deciderService.saveDecider(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        System.out.println("1");
        Decider decider = deciderService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(decider.getUsername());
        System.out.println(token);
        return new AuthResponse(token);
    }
}
