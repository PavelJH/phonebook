package api.model.email;

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
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class EmailDto {
    int id;
    String email;
    int contactId;
}
