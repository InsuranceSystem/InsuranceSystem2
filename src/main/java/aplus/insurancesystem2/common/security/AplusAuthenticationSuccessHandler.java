package aplus.insurancesystem2.common.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AplusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
                                                                                                                                 IOException {
//        SavedRequest save = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        System.out.println("save = " + save);
//        String uri = save != null ? save.getRedirectUrl() : "/";
//        System.out.println("uri = " + uri);
//        redirectStrategy.sendRedirect(request, response, uri);
//        Enumeration<String> list = request.getSession().getAttributeNames();
//        while (list.hasMoreElements()) {
//            System.out.println(list.nextElement());
//        }
    }
}
