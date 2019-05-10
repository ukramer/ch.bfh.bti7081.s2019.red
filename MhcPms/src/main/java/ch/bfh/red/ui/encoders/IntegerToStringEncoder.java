package ch.bfh.red.ui.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;

public class IntegerToStringEncoder implements ModelEncoder<Integer, String> {

    @Override
    public String encode(Integer modelValue) {
        if (modelValue == null)
            return null;
        return modelValue.toString();
    }

    @Override
    public Integer decode(String presentationValue) {
        if (presentationValue == null)
            return null;
        return Integer.parseInt(presentationValue);
    }

}
