package ch.bfh.red.ui.encoders;

import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.TherapyType;
import com.vaadin.flow.templatemodel.ModelEncoder;

public class TherapyTypeToStringEncoder implements ModelEncoder<TherapyType, String> {


    @Override
    public TherapyType decode(String presentationValue){
        try {
            TherapyType type = TherapyType.valueOf(presentationValue.toUpperCase());
        } catch (IllegalArgumentException e) {}
        return null;

    }
    @Override
    public String encode(TherapyType modelValue)
    {
        return modelValue == null ? null : modelValue.getCode();
    }
}
