package aplus.insurancesystem.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import aplus.insurancesystem.common.converter.EInsuranceTypeConverter;
import aplus.insurancesystem.common.converter.EPaymentCycleConverter;
import aplus.insurancesystem.common.converter.UserInfoArgumentResolver;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final UserInfoArgumentResolver userInfoArgumentResolver;
    private final EPaymentCycleConverter ePaymentCycleConverter;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(ePaymentCycleConverter);
    }
}
