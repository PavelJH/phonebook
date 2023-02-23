package api.model.contact;


/*@Getter // автоматические Getter
@Setter
@AllArgsConstructor//конструктор с аргументами
@NoArgsConstructor//конструктор без аргументами
@ToString

 */


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
public class ContactDto {
    //int id; // делаем его необязательным
    String firstName;
    String lastName;
    String description;

}
