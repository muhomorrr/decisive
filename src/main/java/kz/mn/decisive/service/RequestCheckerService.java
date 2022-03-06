package kz.mn.decisive.service;

import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.model.Requester;
import kz.mn.decisive.repository.ExternalAppRepository;
import kz.mn.decisive.repository.RequestFormRepository;
import kz.mn.decisive.repository.RequesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestCheckerService {
    private final ExternalAppRepository externalAppRepository;
    private final RequesterRepository requesterRepository;
    private final RequestFormRepository requestFormRepository;

    public RequestCheckerService(ExternalAppRepository externalAppRepository, RequesterRepository requesterRepository, RequestFormRepository requestFormRepository) {
        this.externalAppRepository = externalAppRepository;
        this.requesterRepository = requesterRepository;
        this.requestFormRepository = requestFormRepository;
    }

    public boolean isConnectedIntegrator(String senderName){
        return externalAppRepository.existsByName(senderName) && externalAppRepository.getByName(senderName).getIsActive();
    }

    @Transactional
    public boolean isRequesterValid(Requester requester){
        if (requesterRepository.existsByIin(requester.getIin())){
           return true;
        }else {
            if(requester.getIin().length()==12 &&
            requester.getLastName() != null &&
            requester.getFirstName() != null){
                requesterRepository.save(requester);
                return true;
            }
            return false;
        }
    }

    public void saveForm(RequestForm requestForm){
        requestFormRepository.save(requestForm);
    }

    public Requester findRequester(String iin){
        return requesterRepository.findByIin(iin);
    }
}
