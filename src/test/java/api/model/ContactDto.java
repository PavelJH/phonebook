package api.model;


/*@Getter // автоматические Getter
@Setter
@AllArgsConstructor//конструктор с аргументами
@NoArgsConstructor//конструктор без аргументами
@ToString

 */


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    String firstName;
    String lastName;
    String description;

}
