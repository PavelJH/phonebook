package api.model.phone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties// это то что необязательно
@JsonInclude(JsonInclude.Include.NON_DEFAULT)// - для того чтобы ы неуказаных полях, невключать в тедо запроса

public class PhoneDTO {

    String countryCode;
    String phoneNumber;
    int contactId;

}
