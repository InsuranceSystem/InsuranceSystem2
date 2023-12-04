package aplus.insurancesystem.common.converter;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;

@Component
public class PaymentCycleConverter implements Converter<String, PaymentCycle> {

    @Override
    public PaymentCycle convert(String source) {
        return Arrays.stream(PaymentCycle.values())
                .filter(e -> e.getName().equals(source))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
