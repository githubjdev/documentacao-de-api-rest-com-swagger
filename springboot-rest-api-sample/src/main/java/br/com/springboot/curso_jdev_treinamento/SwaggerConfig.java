package br.com.springboot.curso_jdev_treinamento;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocSwgger() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.springboot.curso_jdev_treinamento.controllers"))
				.paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseCodeHttp())
				.apiInfo(apiInfo())
				.securityContexts(securityContext())
				.securitySchemes(listApiKey());
		
	}
	
	private List<ApiKey> listApiKey(){
		List<ApiKey> apiKeys = new ArrayList<ApiKey>();
		apiKeys.add(new ApiKey("JWT", "Authorization", "header"));
		return apiKeys;
	}
	
	
	private List<ResponseMessage> responseCodeHttp(){
		 
		List<ResponseMessage> list = new ArrayList<ResponseMessage>();
		
		list.add(new ResponseMessageBuilder()
				.code(500)
				.message("Erro interno do servidor")
				.responseModel(new ModelRef("Error"))
				.build());
		
		
		list.add(new ResponseMessageBuilder()
				.code(404)
				.message("Conteúdo não encontrado")
				.responseModel(new ModelRef("Error"))
				.build());
		
		
		list.add(new ResponseMessageBuilder()
				.code(401)
				.message("Não autorizado")
				.responseModel(new ModelRef("Error"))
				.build());
		
		
		
		
		list.add(new ResponseMessageBuilder()
				.code(403)
				.message("Acesso negado")
				.responseModel(new ModelRef("Error"))
				.build());
		
		
		return list;
	}

	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Curso Swagger JDEV Treinamento")
				.description("Api e doc do curso do alex")
				.version("0.0.1")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.license("Apache License")
				.contact(new Contact("Alex F. Egidio",
						"https://jdevtreinamento.com.br", "contato@jdevtreinamento.com.br"))
				.build();
	}

 private  List<SecurityContext> securityContext() {
	 List<SecurityContext> list = new ArrayList<SecurityContext>();
	 list.add(SecurityContext.builder().securityReferences(defaultAuth()).build());
	 return list;
 }	

 private List<SecurityReference> defaultAuth(){
	 AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	 AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	 authorizationScopes[0] = authorizationScope;
	 return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
 }	

}
