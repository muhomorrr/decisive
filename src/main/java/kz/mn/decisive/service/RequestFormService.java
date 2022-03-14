package kz.mn.decisive.service;

import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.model.RequestStatus;
import kz.mn.decisive.repository.RequestFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestFormService {
    private final RequestFormRepository requestFormRepository;

    public RequestFormService(RequestFormRepository requestFormRepository) {
        this.requestFormRepository = requestFormRepository;
    }

    public List<RequestForm> getSubmitRequests() {
        return requestFormRepository.findAllByStatus();
    }

    @Transactional
    public void changeStatus(long id, RequestStatus status) throws Exception {
        if(status != RequestStatus.SUBMIT) {
            requestFormRepository.changeStatusById(id, status.name());
        } else {
            throw new Exception("The status already changed");
        }
    }

    public RequestForm getRequestById(long id){
        return requestFormRepository.findById(id);
    }

    public RequestForm getSubmittedRequestById(long id) {
        return requestFormRepository.findSubmittedById(id);
    }
}
