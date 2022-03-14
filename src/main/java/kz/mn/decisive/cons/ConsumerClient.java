package kz.mn.decisive.cons;

import kz.mn.decisive.ws.model.ChangeStatusRequest;
import kz.mn.decisive.ws.model.IncomingResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ConsumerClient extends WebServiceGatewaySupport {
    public IncomingResponse sendRequestForm(ChangeStatusRequest request)
    {
        IncomingResponse response = (IncomingResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8081/ws"));
        return response;
    }
}
