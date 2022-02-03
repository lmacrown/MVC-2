package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageCodeResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        Assertions.assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        //bindingResult.rejectValue("itemName", "required");
        new FieldError("item", "itemName", null, false, messageCodes, null, null);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }

}
