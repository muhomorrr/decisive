package kz.mn.decisive.ws;

import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.model.RequestStatus;
import kz.mn.decisive.model.Requester;
import kz.mn.decisive.service.RequestCheckerService;
import kz.mn.decisive.ws.model.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MessageEndpoint {
    private static final String NAMESPACE_URI = "http://www.decisive.mn.kz";
    private final RequestCheckerService requestCheckerService;

    public MessageEndpoint(RequestCheckerService requestCheckerService) {
        this.requestCheckerService = requestCheckerService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "IncomingRequest")
    @ResponsePayload
    public IncomingResponse saveRequest(@RequestPayload IncomingRequest request) {
        IncomingResponse response = new IncomingResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        if(requestCheckerService.isConnectedIntegrator(request.getMessageInfo().getSender())){
            MessageData messageData = request.getMessageData();
            RequesterWSDL requesterWSDL = messageData.getRequester();
            Requester requester = new Requester();
            requester.setIin(requesterWSDL.getIin());
            requester.setFirstName(requesterWSDL.getFirstname());
            requester.setLastName(requesterWSDL.getLastname());
            requester.setPatronymic(requesterWSDL.getPatronymic());
            if(requestCheckerService.isRequesterValid(requester)){
                requester = requestCheckerService.findRequester(requester.getIin());
                RequestForm requestForm = new RequestForm();
                requestForm.setRequester(requester);
                requestForm.setGatewayId(request.getMessageInfo().getId());
                requestForm.setContent(messageData.getContent());
                requestForm.setStatus(RequestStatus.SUBMIT);
                requestCheckerService.saveForm(requestForm);
                responseInfo.setStatus("OK");
                responseInfo.setRequestId(request.getMessageInfo().getId());
            }else {
                responseInfo.setStatus("ERROR");
                responseInfo.setErrorMessage("Invalid requester parameters");
            }
        }else {
            responseInfo.setStatus("ERROR");
            responseInfo.setErrorMessage("There is no such external app or it is not active");
        }
        response.setInfo(responseInfo);
        return response;
    }
}
