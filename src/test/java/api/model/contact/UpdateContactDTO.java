package api.model.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateContactDTO {
    int id;
    String firstName;
    String lastName;
    String description;
}
