package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndPoint {
    ADD_NEW_CONTACT("/api/contact"),
    DELETE_CONTACT("/api/contact/{id}"),
    GET_LIST_OF_CONTACTS("/api/contact"),
    GET_CONTACT_BY_CONTACT_ID("/api/contact/{id}"),
    UPDATE_CONTACT("/api/contact/"),
    ADD_NEW_EMAIL("/api/email"),
    GET_EMAIL_BY_EMAIL_ID("/api/email/{id}"),
    GET_LIST_OF_EMAILS_BY_CONTACT_ID("/api/email/{id}/all"),// contactID
    UPDATE_EMAIL("/api/email");

    private final String value;
}
