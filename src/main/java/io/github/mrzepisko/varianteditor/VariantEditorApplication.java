package io.github.mrzepisko.varianteditor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class VariantEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VariantEditorApplication.class, args);
	}

}
