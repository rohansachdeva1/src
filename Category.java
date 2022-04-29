// import java.util.*;

public class Category {

    private String name;
    private String description;
    private String question;

    // default constructor
    public Category() {
        this.name = "";
        this.description = "";
        this.question = "";
    }

    // main constructor
    public Category(String inputName, String inputDescription, String inputQuestion) {
        this.name = inputName;
        this.description = inputDescription;
        this.question = inputQuestion;
    }

    // no description constructor
    public Category(String inputName, String inputQuestion) {
        this.name = inputName;
        this.question = inputQuestion;
    }

    // no description and no question constructor
    public Category(String inputName) {
        this.name = inputName;
    }

    // Name setter and getter
    public void setName(String inputName) {
        this.name = inputName;
    }
    public String getName() {
        return this.name;
    }

    // Description setter and getter
    public void setDescription(String inputDescription) {
        this.description = inputDescription;
    }
    public String getDescription() {
        return this.description;
    }

    // Question setter and getter
    public void setQuestion(String inputQuestion) {
        this.question = inputQuestion;
    }
    public String getQuestion() {
        return this.question;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        /*
        if (this.description != null) {
            result.append(this.description + "\n");
        } */
        result.append(this.question);

        return result.toString();
    }

}