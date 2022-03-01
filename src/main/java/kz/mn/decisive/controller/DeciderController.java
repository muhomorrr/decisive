package kz.mn.decisive.controller;

import kz.mn.decisive.model.Decider;
import kz.mn.decisive.service.DeciderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeciderController {
    private final DeciderService deciderService;

    public DeciderController(DeciderService deciderService) {
        this.deciderService = deciderService;
    }

    @GetMapping("decider/all")
    public List<Decider> getDeciders(){
        return deciderService.getDeciders();
    }


}
