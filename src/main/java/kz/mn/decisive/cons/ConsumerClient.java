package kz.mn.decisive.cons;

import kz.mn.decisive.ws.model.ChangeStatusRequest;
import kz.mn.decisive.ws.model.ChangeStatusResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ConsumerClient extends WebServiceGatewaySupport {
    public ChangeStatusResponse sendRequestForm(ChangeStatusRequest request)
    {
        ChangeStatusResponse response = (ChangeStatusResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8081/ws"));
        return response;
    }
}
