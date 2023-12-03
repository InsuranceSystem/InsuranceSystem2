package aplus.insurancesystem.common.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AplusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
                                                                                                                                 IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 사용자 ID 가져오기
        Long userId = userDetails.getCustomer().getId();

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{ \"userId\": \"" + userId + "\" }");
    }
}
