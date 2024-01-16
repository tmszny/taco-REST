package tms.cloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tms.cloud.data.IngredientRepository;
import tms.cloud.data.TacoRepository;
import tms.cloud.data.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CloudApplication {

    public static void main(String[] args) {
        SpringApplication. run(CloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepository, BCryptPasswordEncoder encoder, TacoRepository tacoRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

                userRepository.save(new User("user", encoder.encode("password")));
                userRepository.save(new User("admin", encoder.encode("haslo")));

                List<Ingredient> taco1 = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP), new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                List<Ingredient> taco2 = List.of(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP), new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));

                tacoRepo.save(new Taco("taco1", taco1));
                tacoRepo.save(new Taco("taco2", taco2));
            }
        };
    }
}
