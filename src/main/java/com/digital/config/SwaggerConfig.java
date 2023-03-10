package com.digital.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes())
				.host("combb.person.com:9080")
				.apiInfo(getApiInfo())
				// swagger에서 jwt 토큰값 넣기위한 설정
				.securityContexts(Arrays.asList(securityContext()))
				// swagger에서 jwt 토큰값 넣기위한 설정
				.securitySchemes(Arrays.asList(apiKey())).produces(getProduceContentTypes()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant("/api/**")).build();
	}

	// swagger에서 jwt 토큰값 넣기위한 설정 -> JWT를 인증 헤더로 포함하도록 ApiKey 를 정의.
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	// 전역 AuthorizationScope를 사용하여 JWT SecurityContext를 구성.
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];

		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		// consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}
	
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("commerce-person-v2")
                .build();
    }
}
