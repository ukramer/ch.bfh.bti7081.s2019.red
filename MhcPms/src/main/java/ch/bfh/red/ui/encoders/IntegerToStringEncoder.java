package ch.bfh.red.ui.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;
import org.apache.commons.lang3.StringUtils;

public class IntegerToStringEncoder implements ModelEncoder<Integer, String> {

    @Override
    public String encode(Integer modelValue) {
        if (modelValue == null) return null;
        return modelValue.toString();
    }

    @Override
    public Integer decode(String presentationValue) {
        if (StringUtils.isNotBlank(presentationValue)){
            return Integer.parseInt(presentationValue);
        }
        return null;
    }

}
