package aplus.insurancesystem2.common.config;

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
        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customerOpenApi() {
        String[] paths = {"/customers/**"};

        return GroupedOpenApi
                .builder()
                .group("고객 API")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi insuranceOpenApi() {
        String[] paths = {"/insurances/**"};

        return GroupedOpenApi
                .builder()
                .group("보험 API")
                .pathsToMatch(paths)
                .build();
    }
}
