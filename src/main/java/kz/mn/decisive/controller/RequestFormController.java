package kz.mn.decisive.controller;

import kz.mn.decisive.cons.ConsumerClient;
import kz.mn.decisive.dto.ChangeStatusWebRequest;
import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.service.RequestFormService;
import kz.mn.decisive.ws.model.ChangeStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestFormController {
    private final ConsumerClient consumerClient;
    private final RequestFormService requestFormService;

    public RequestFormController(RequestFormService requestFormService, ConsumerClient consumerClient) {
        this.requestFormService = requestFormService;
        this.consumerClient = consumerClient;
    }

    @GetMapping("/requests/all")
    public List<RequestForm> getSubmitRequests(){
        return requestFormService.getSubmitRequests();
    }

    @PostMapping("/requests/status")
    public void changeStatus(@RequestBody ChangeStatusWebRequest changeStatusWebRequest){
        requestFormService.changeStatus(changeStatusWebRequest.getId(), changeStatusWebRequest.getRequestStatus());
        ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest();
        RequestForm requestForm = requestFormService.getRequestById(changeStatusWebRequest.getId());
        changeStatusRequest.setStatus(requestForm.getStatus().name());
        changeStatusRequest.setRequestId(requestForm.getId());
        changeStatusRequest.setContent(requestForm.getContent());
        changeStatusRequest.setFirstname(requestForm.getRequester().getFirstName());
        changeStatusRequest.setIin(requestForm.getRequester().getIin());
        changeStatusRequest.setGatewayId(requestForm.getGatewayId());
        changeStatusRequest.setLastname(requestForm.getRequester().getLastName());
        changeStatusRequest.setPatronymic(requestForm.getRequester().getPatronymic());
        consumerClient.sendRequestForm(changeStatusRequest);

    }
}
