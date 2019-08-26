package io.github.mrzepisko.varianteditor.dao;

import io.github.mrzepisko.varianteditor.model.Variant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataLoader {

    @Bean
    CommandLineRunner initVariants(VariantRepository repository) {
        return args -> {
            int variantCount = 10;
            Variant[] variants = new Variant[variantCount];
            for (long i = 0; i < variantCount; i++) {
                log.info("" + repository.save(new Variant(i, "alteration", "chromosome", "lorem ipsum")));
            }
        };
    }
}
