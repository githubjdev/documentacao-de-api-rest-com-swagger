package br.com.springboot.curso_jdev_treinamento.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = {"Api V2"}, description = "Ex: Api v2")
@RestController
@RequestMapping(value = "api/v2")
public class ControllerApi2 {
	
	@RequestMapping(value = "/nomeapi2/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso  Spring Boot API: " + name + "!";
	}

}
