package kz.mn.decisive.controller;

import kz.mn.decisive.cons.ConsumerClient;
import kz.mn.decisive.dto.ChangeStatusWebRequest;
import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.service.RequestFormService;
import kz.mn.decisive.ws.model.*;
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

    @GetMapping("requests/{id}")
    public RequestForm getSubmitRequest(@PathVariable long id){
        return requestFormService.getSubmittedRequestById(id);
    }

    @PostMapping("/requests/status")
    public ResponseInfo changeStatus(@RequestBody ChangeStatusWebRequest changeStatusWebRequest){
        try {
            requestFormService.changeStatus(changeStatusWebRequest.getId(), changeStatusWebRequest.getRequestStatus());
            RequestForm requestForm = requestFormService.getRequestById(changeStatusWebRequest.getId());
            ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest();
            MessageInfo messageInfo = new MessageInfo();
            ChangeStatusMessageData changeStatusMessageData = new ChangeStatusMessageData();
            messageInfo.setId(requestForm.getGatewayId());
            messageInfo.setSender("MyApi");
            messageInfo.setReceiver(requestForm.getOriginApp());
            changeStatusMessageData.setStatus(requestForm.getStatus().name());
            changeStatusRequest.setMessageData(changeStatusMessageData);
            changeStatusRequest.setMessageInfo(messageInfo);
            return consumerClient.sendRequestForm(changeStatusRequest).getInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
