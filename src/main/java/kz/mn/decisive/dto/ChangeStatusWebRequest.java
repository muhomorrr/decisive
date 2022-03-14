package kz.mn.decisive.dto;

import kz.mn.decisive.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusWebRequest {
    private long id;
    private RequestStatus requestStatus;
}
