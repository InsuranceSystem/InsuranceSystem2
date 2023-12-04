package aplus.insurancesystem.common.converter;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;
import aplus.insurancesystem.common.security.CustomUserDetails;
import aplus.insurancesystem.common.security.CustomerInfo;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;

@Component
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CustomerInfo.class) &&
               parameter.hasParameterAnnotation(GetCustomer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(principal, "anonymousUser")) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }

        Customer customer = ((CustomUserDetails) principal).getCustomer();
        return new CustomerInfo(customer.getId(), customer.getRole());
    }
}
