package kz.mn.decisive.service;

import kz.mn.decisive.model.Decider;
import kz.mn.decisive.repository.DeciderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeciderService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DeciderRepository deciderRepository;


    public List<Decider> getDeciders() {
        return deciderRepository.findAll();
    }

    public Decider findByUsername(String username) {
        return deciderRepository.findByUsername(username);
    }


    public void saveDecider(Decider decider) {
        decider.setPassword(passwordEncoder.encode(decider.getPassword()));
        deciderRepository.save(decider);
    }

    public Decider findByUsernameAndPassword(String username, String password) {
        Decider decider = findByUsername(username);
        if (decider != null) {
            if (passwordEncoder.matches(password, decider.getPassword())) {
                return decider;
            }
        }
        return null;
    }
}
