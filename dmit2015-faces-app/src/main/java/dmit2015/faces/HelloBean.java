package dmit2015.faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;
import net.datafaker.Faker;
import org.omnifaces.util.Messages;

@Named("hello")
@RequestScoped
public class HelloBean {

    @NotBlank(message = "User Input value is required")
    private String userInput;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getMessage() {
        return "Hello, " + userInput;
    }

    public String submit() {

        // Create a new Faker instance for generating realistic fake data
        var faker = new Faker();
        // Generate a random Pokemon name and move
        var randomPokemon = faker.pokemon().name();
        var randonMove = faker.pokemon().move();
        // Send FacesContext message
        Messages.addGlobalInfo("Hi {0}, you got a Pokemon named {1} that likes to {2}",
                userInput, randomPokemon, randonMove);
        return null; // or navigation outcome
    }
}