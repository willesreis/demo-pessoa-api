package com.example.demopessoaapi.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PessoaFormValidationTest {

	@Autowired
	private Validator validator;

	@Test
	public void invalidNomeShouldFail() {
		PessoaForm form = new PessoaForm(null);
		form.setNome("John");
		form.setEstadoCivil(1);
		form.setDataNascimento(LocalDate.now());

		Set<ConstraintViolation<PessoaForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void invalidEstadoCivilShouldFail() {
		PessoaForm form = new PessoaForm(null);
		form.setNome("Johnny");
		form.setEstadoCivil(null);
		form.setDataNascimento(LocalDate.now());

		Set<ConstraintViolation<PessoaForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void invalidDataNascimentoShouldFail() {
		PessoaForm form = new PessoaForm(null);
		form.setNome("Johnny");
		form.setEstadoCivil(1);
		form.setDataNascimento(null);

		Set<ConstraintViolation<PessoaForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

}
