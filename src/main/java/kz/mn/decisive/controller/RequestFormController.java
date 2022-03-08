package kz.mn.decisive.controller;

import kz.mn.decisive.dto.ChangeStatusRequest;
import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.service.RequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestFormController {
    private final RequestFormService requestFormService;

    public RequestFormController(RequestFormService requestFormService) {
        this.requestFormService = requestFormService;
    }

    @GetMapping("/requests/all")
    public List<RequestForm> getSubmitRequests(){
        return requestFormService.getSubmitRequests();
    }

    @PostMapping("/requests/status")
    public void changeStatus(@RequestBody ChangeStatusRequest changeStatusRequest){
        requestFormService.changeStatus(changeStatusRequest.getId(), changeStatusRequest.getRequestStatus());
    }
}
