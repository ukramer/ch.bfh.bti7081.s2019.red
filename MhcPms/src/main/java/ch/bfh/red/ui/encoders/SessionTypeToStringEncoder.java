package ch.bfh.red.ui.encoders;

import ch.bfh.red.backend.models.SessionType;
import com.vaadin.flow.templatemodel.ModelEncoder;

public class SessionTypeToStringEncoder implements ModelEncoder<SessionType, String> {


    @Override
    public SessionType decode(String presentationValue){
        try {
            SessionType type = SessionType.valueOf(presentationValue.toUpperCase());
        } catch (IllegalArgumentException e) {}
        return null;

    }
    @Override
    public String encode(SessionType modelValue)
    {
        return modelValue == null ? null : modelValue.getCode();
    }
}
