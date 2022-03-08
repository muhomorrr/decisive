package kz.mn.decisive.dto;

import kz.mn.decisive.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeStatusWebRequest {
    private long id;
    private RequestStatus requestStatus;
}
