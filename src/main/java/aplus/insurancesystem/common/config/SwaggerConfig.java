package aplus.insurancesystem.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "A+조 보험사 시스템",
                description = "명지대학교 분산시스템2 팀프로젝트 A+조 보험사 시스템 API 문서입니다.",
                version = "v1"),
        servers = {
                @Server(url = "https://port-0-insurancesystem-euegqv2blnzmormf.sel5.cloudtype.app/", description = "테스트 서버"),
                @Server(url = "http://52.78.86.187/", description = "실서버")
        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allOpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi
                .builder()
                .group("전체 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi customerOpenApi() {
        String[] paths = {"/api/customers/**"};

        return GroupedOpenApi
                .builder()
                .group("고객 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi insuranceOpenApi() {
        String[] paths = {"/api/insurances/**"};

        return GroupedOpenApi
                .builder()
                .group("보험 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi termsOpenApi() {
        String[] paths = {"/api/terms/**"};

        return GroupedOpenApi
                .builder()
                .group("약관 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi insuranceApplicationOpenApi() {
        String[] paths = {"/api/insurance-applications/**"};

        return GroupedOpenApi
                .builder()
                .group("보험 신청 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi contractOpenApi() {
        String[] paths = {"/api/contracts/**"};

        return GroupedOpenApi
                .builder()
                .group("계약 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi paymentOpenApi() {
        String[] paths = {"/api/payments/**"};

        return GroupedOpenApi
                .builder()
                .group("납입 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi compensationClaimOpenApi() {
        String[] paths = {"/api/compensation-claim/**"};

        return GroupedOpenApi
                .builder()
                .group("보상금 청구 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi surveyOpenApi() {
        String[] paths = {"/api/survey/**"};

        return GroupedOpenApi
                .builder()
                .group("손해사정 API")
                .pathsToMatch(paths)
                .build();
    }
}
