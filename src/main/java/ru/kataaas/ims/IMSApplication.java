package ru.kataaas.ims;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kataaas.ims.entity.RoleEntity;
import ru.kataaas.ims.repository.RoleRepository;

@SpringBootApplication
public class IMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMSApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(RoleRepository roleRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (!roleRepository.existsByName("ROLE_USER")) {
                    RoleEntity userRole = new RoleEntity();
                    userRole.setName("ROLE_USER");
                    roleRepository.save(userRole);

                    RoleEntity vendorRole = new RoleEntity();
                    vendorRole.setName("ROLE_VENDOR");
                    roleRepository.save(vendorRole);

                    RoleEntity adminRole = new RoleEntity();
                    adminRole.setName("ROLE_ADMIN");
                    roleRepository.save(adminRole);
                }
            }
        };
    }

}
