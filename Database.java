import java.util.*;
//import java.util.LinkedList;

public class Database {
    public static Map<String,Category> categoryMap;
    private int numUsers;

    public Database() {
        categoryMap = new HashMap<>();
        int categoryCount = 0;
        numUsers = 0;

        Category plot = new Category("Plot", "Rate the overall plot and storyline:");
        plot.setDescription("Plot is the way the narrative is organized to tell a story.");
        categoryMap.put(plot.getName(), plot); // 1 plot

        Category acting = new Category("Acting", "Rate the acting performance throughout the film:");
        categoryMap.put(acting.getName(), acting); // 2 acting

        Category protagonist = new Category("Protagonist", "Rate how much you enjoyed the protagonist:");
        protagonist.setDescription("The protagonist is the main character that the story follows.");
        categoryMap.put(protagonist.getName(), protagonist); // 3 protagonist
        
        Category antagonist = new Category("Antagonist", "Rate how much you enjoyed the antagonist:");
        antagonist.setDescription("The antagonist is the character that actively oposes the protagonist.");
        categoryMap.put(antagonist.getName(), antagonist); // 4 antagonist

        Category sideCharacters = new Category("Side Characters", "Rate the side characters in the film:");
        sideCharacters.setDescription("Side characters are all the characters that surround the protagonist and antagonist.");
        categoryMap.put(sideCharacters.getName(), sideCharacters); // 5 side characters

        Category beginning = new Category("Beginning", "Rate how engaging the beginning of the film was:");
        beginning.setDescription("The beginning includes the background and rising action.");
        categoryMap.put(beginning.getName(), beginning); // 6 beginning
        
        Category ending = new Category("Ending", "Rate how satisfied you were with how the story ended:");
        ending.setDescription("The ending includes the falling action and resolution of the main plot.");
        categoryMap.put(ending.getName(), ending); // 7 ending

        Category cinematography = new Category("Cinematography", "Rate the cinematography/animation style and execution:");
        cinematography.setDescription("Cinematography is the general composition of scenes, camera angles, lighting, colour etc.");
        categoryMap.put(cinematography.getName(), cinematography); // 8 cinematography
        
        Category soundtrack = new Category("Soundtrack", "Rate the soundtrack:");
        soundtrack.setDescription("The soundtrack is the collection of music that is featured in the film.");
        categoryMap.put(soundtrack.getName(), soundtrack); // 9 soundtrack
        
        Category dialogue = new Category("Dialogue", "Rate the dialogue throughout the story");
        dialogue.setDescription("Were there powerful and memerable moments created by the characters' dialogues and monologues?");
        categoryMap.put(dialogue.getName(), dialogue); // 10 dialogue
        
        Category characterDevelopment = new Category("Character Development", "Rate the character development:");
        characterDevelopment.setDescription("Character development is how the character(s) change and grow throughout the story.");
        categoryMap.put(characterDevelopment.getName(), characterDevelopment); // 11 character development

        Category characterComplexity = new Category("Character Complexity", "Rate the character complexity displayed in the film:");
        characterComplexity.setDescription("Character complexity is how detailed and built out the characters are. Do they all have their own motivations, back stories, etc?");
        categoryMap.put(characterComplexity.getName(), characterComplexity); // 12 character complexity
        
        Category entertainment = new Category("Entertainment Value", "Rate the entertainment value of the film:");
        entertainment.setDescription("Entertainment value is how engaged and entertained you were throughout the film.");
        categoryMap.put(entertainment.getName(), entertainment); // 13 entertainment

        Category emotion = new Category("Emotion", "Rate the emotional impact that the story had on you:");
        emotion.setDescription("How much did the film affect your emotions? Did it bring you to tears during the sad moments?\n Did you feel excited when the characters succeeded?");
        categoryMap.put(emotion.getName(), emotion); // 14 emotion

        Category pacing = new Category("Pacing", "Rate how well the story was paced:");
        pacing.setDescription("Pacing is the speed at which the story is told. Were there times where it felt rushed or slow?");
        categoryMap.put(pacing.getName(), pacing); // 15 pacing
        
        Category logic = new Category("Logic", "Rate how logical and realistic (withing the scope of the film) you found the sequence of events, details, etc:");
        categoryMap.put(logic.getName(), logic); // 16 logic
        
        Category worldBuilding = new Category("World Building", "Rate the world building in this film:");
        worldBuilding.setDescription("World building is the details added to make the film's fictitious universe feel real.");
        categoryMap.put(worldBuilding.getName(), worldBuilding); // 17 world building

        Category humor = new Category("Humor", "Rate the humor in the film:");
        categoryMap.put(humor.getName(), humor); // 18 humor

        Category rewatchability = new Category("Rewatchability", "Rate the rewatchability of this film:");
        rewatchability.setDescription("Rewatchability is how likely you are to watch this film again in the future.");
        categoryMap.put(rewatchability.getName(), rewatchability); // 19 rewatchability
    }

    public Map<String,Category> getCategories() {
        return this.categoryMap;
    }

    // normal/standard account has limited categories to rate and provides users with descriptions when available
    public void initializeTestAccount(User inputUser) {

        inputUser.actionMultipliers.clear();
        inputUser.comedyMultipliers.clear();
        inputUser.dramaMultipliers.clear();

        // Create action multipliers hashmap to store category name (key) and multiplier value 
        inputUser.actionMultipliers.put("Plot", 2.5);
        inputUser.actionMultipliers.put("Acting", 0.0);
        inputUser.actionMultipliers.put("Protagonist", 0.0);
        inputUser.actionMultipliers.put("Antagonist", 0.0);
        inputUser.actionMultipliers.put("Side Characters", 0.0);
        inputUser.actionMultipliers.put("Beginning", 0.0); // pro
        inputUser.actionMultipliers.put("Ending", 0.0); // pro
        inputUser.actionMultipliers.put("Cinematography", 0.0); // pro
        inputUser.actionMultipliers.put("Soundtrack", 0.0);
        inputUser.actionMultipliers.put("Dialogue", 0.0);
        inputUser.actionMultipliers.put("Character Development", 0.0); // pro
        inputUser.actionMultipliers.put("Character Complexity", 0.0); // pro
        inputUser.actionMultipliers.put("Entertainment Value", 0.0);
        inputUser.actionMultipliers.put("Emotion", 0.0); // pro
        inputUser.actionMultipliers.put("Pacing", 0.0); // pro
        inputUser.actionMultipliers.put("Logic", 0.0); // pro
        inputUser.actionMultipliers.put("World Building", 0.0); // pro
        inputUser.actionMultipliers.put("Humor", 0.0); 
        inputUser.actionMultipliers.put("Rewatchability", 0.0); // pro
        
        // Create comedy multipliers hashmap to store category name (key) and multiplier value 
        inputUser.comedyMultipliers.put("Plot", 0.6);
        inputUser.comedyMultipliers.put("Acting", 0.0);
        inputUser.comedyMultipliers.put("Protagonist", 0.0);
        inputUser.comedyMultipliers.put("Antagonist", 0.0);
        inputUser.comedyMultipliers.put("Side Characters", 0.0);
        inputUser.comedyMultipliers.put("Beginning", 0.0); // pro
        inputUser.comedyMultipliers.put("Ending", 0.0); // pro
        inputUser.comedyMultipliers.put("Cinematography", 0.0); // pro
        inputUser.comedyMultipliers.put("Soundtrack", 0.0);
        inputUser.comedyMultipliers.put("Dialogue", 0.0);
        inputUser.comedyMultipliers.put("Character Development", 0.0);
        inputUser.comedyMultipliers.put("Character Complexity", 0.0); // pro
        inputUser.comedyMultipliers.put("Entertainment Value", 0.0);
        inputUser.comedyMultipliers.put("Emotion", 0.0);
        inputUser.comedyMultipliers.put("Pacing", 0.0);
        inputUser.comedyMultipliers.put("Logic", 0.0);
        inputUser.comedyMultipliers.put("World Building", 0.0);
        inputUser.comedyMultipliers.put("Humor", 0.0);
        inputUser.comedyMultipliers.put("Rewatchability", 0.0);

        // Create drama  multipliers hashmap to store category name (key) and multiplier value 
        inputUser.dramaMultipliers.put("Plot", 1.0);
        inputUser.dramaMultipliers.put("Acting", 0.0);
        inputUser.dramaMultipliers.put("Protagonist", 0.0);
        inputUser.dramaMultipliers.put("Antagonist", 0.0);
        inputUser.dramaMultipliers.put("Side Characters", 0.0);
        inputUser.dramaMultipliers.put("Beginning", 0.0); // pro
        inputUser.dramaMultipliers.put("Ending", 0.0); // pro
        inputUser.dramaMultipliers.put("Cinematography", 0.0); // pro
        inputUser.dramaMultipliers.put("Soundtrack", 0.0); // pro
        inputUser.dramaMultipliers.put("Dialogue", 0.0);
        inputUser.dramaMultipliers.put("Character Development", 0.0); // pro
        inputUser.dramaMultipliers.put("Character Complexity", 0.0);
        inputUser.dramaMultipliers.put("Entertainment Value", 0.0);
        inputUser.dramaMultipliers.put("Emotion", 0.0);
        inputUser.dramaMultipliers.put("Pacing", 0.0); // pro
        inputUser.dramaMultipliers.put("Logic", 0.0); // pro
        inputUser.dramaMultipliers.put("World Building", 0.0);
        inputUser.dramaMultipliers.put("Humor", 0.0);
        inputUser.dramaMultipliers.put("Rewatchability", 0.0);

}

    // normal/standard account has limited categories to rate and provides users with descriptions when available
    public void initializeNormalAccount(User inputUser) {

            inputUser.actionMultipliers.clear();
            inputUser.comedyMultipliers.clear();
            inputUser.dramaMultipliers.clear();

            // Create action multipliers hashmap to store category name (key) and multiplier value 
            inputUser.actionMultipliers.put("Plot", 2.5);
            inputUser.actionMultipliers.put("Acting", 2.0);
            inputUser.actionMultipliers.put("Protagonist", 2.0);
            inputUser.actionMultipliers.put("Antagonist", 2.0);
            inputUser.actionMultipliers.put("Side Characters", 1.0);
            inputUser.actionMultipliers.put("Beginning", 0.0); // pro
            inputUser.actionMultipliers.put("Ending", 0.0); // pro
            inputUser.actionMultipliers.put("Cinematography", 0.0); // pro
            inputUser.actionMultipliers.put("Soundtrack", 1.0);
            inputUser.actionMultipliers.put("Dialogue", 1.0);
            inputUser.actionMultipliers.put("Character Development", 0.0); // pro
            inputUser.actionMultipliers.put("Character Complexity", 0.0); // pro
            inputUser.actionMultipliers.put("Entertainment Value", 1.0);
            inputUser.actionMultipliers.put("Emotion", 0.0); // pro
            inputUser.actionMultipliers.put("Pacing", 0.0); // pro
            inputUser.actionMultipliers.put("Logic", 0.0); // pro
            inputUser.actionMultipliers.put("World Building", 0.0); // pro
            inputUser.actionMultipliers.put("Humor", 0.3); 
            inputUser.actionMultipliers.put("Rewatchability", 0.0); // pro
            
            // Create comedy multipliers hashmap to store category name (key) and multiplier value 
            inputUser.comedyMultipliers.put("Plot", 0.6);
            inputUser.comedyMultipliers.put("Acting", 2.0);
            inputUser.comedyMultipliers.put("Protagonist", 1.0);
            inputUser.comedyMultipliers.put("Antagonist", 0.0);
            inputUser.comedyMultipliers.put("Side Characters", 1.0);
            inputUser.comedyMultipliers.put("Beginning", 0.0); // pro
            inputUser.comedyMultipliers.put("Ending", 0.0); // pro
            inputUser.comedyMultipliers.put("Cinematography", 0.0); // pro
            inputUser.comedyMultipliers.put("Soundtrack", 0.0);
            inputUser.comedyMultipliers.put("Dialogue", 2.0);
            inputUser.comedyMultipliers.put("Character Development", 0.0);
            inputUser.comedyMultipliers.put("Character Complexity", 0.0); // pro
            inputUser.comedyMultipliers.put("Entertainment Value", 2.5);
            inputUser.comedyMultipliers.put("Emotion", 0.8);
            inputUser.comedyMultipliers.put("Pacing", 0.0);
            inputUser.comedyMultipliers.put("Logic", 0.0);
            inputUser.comedyMultipliers.put("World Building", 0.0);
            inputUser.comedyMultipliers.put("Humor", 2.5);
            inputUser.comedyMultipliers.put("Rewatchability", 1.5);

            // Create drama  multipliers hashmap to store category name (key) and multiplier value 
            inputUser.dramaMultipliers.put("Plot", 1.0);
            inputUser.dramaMultipliers.put("Acting", 2.5);
            inputUser.dramaMultipliers.put("Protagonist", 0.7);
            inputUser.dramaMultipliers.put("Antagonist", 0.0);
            inputUser.dramaMultipliers.put("Side Characters", 1.0);
            inputUser.dramaMultipliers.put("Beginning", 0.0); // pro
            inputUser.dramaMultipliers.put("Ending", 0.0); // pro
            inputUser.dramaMultipliers.put("Cinematography", 0.0); // pro
            inputUser.dramaMultipliers.put("Soundtrack", 0.0); // pro
            inputUser.dramaMultipliers.put("Dialogue", 2.0);
            inputUser.dramaMultipliers.put("Character Development", 0.0); // pro
            inputUser.dramaMultipliers.put("Character Complexity", 2.5);
            inputUser.dramaMultipliers.put("Entertainment Value", 2.0);
            inputUser.dramaMultipliers.put("Emotion", 0.8);
            inputUser.dramaMultipliers.put("Pacing", 0.0); // pro
            inputUser.dramaMultipliers.put("Logic", 0.0); // pro
            inputUser.dramaMultipliers.put("World Building", 0.0);
            inputUser.dramaMultipliers.put("Humor", 0.0);
            inputUser.dramaMultipliers.put("Rewatchability", 0.8);

    }


    // Pro account opens all categories and does not provide the user with descriptions
    public void initializeProAccount(User inputUser) {
        
            inputUser.actionMultipliers.clear();
            inputUser.comedyMultipliers.clear();
            inputUser.dramaMultipliers.clear();

            // Create action multipliers hashmap to store category name (key) and multiplier value 
            inputUser.actionMultipliers.put("Plot", 2.5);
            inputUser.actionMultipliers.put("Acting", 2.0);
            inputUser.actionMultipliers.put("Protagonist", 2.0);
            inputUser.actionMultipliers.put("Antagonist", 2.0);
            inputUser.actionMultipliers.put("Side Characters", 1.0);
            inputUser.actionMultipliers.put("Beginning", 1.0);
            inputUser.actionMultipliers.put("Ending", 1.8);
            inputUser.actionMultipliers.put("Cinematography", 1.5);
            inputUser.actionMultipliers.put("Soundtrack", 1.5);
            inputUser.actionMultipliers.put("Dialogue", 1.0);
            inputUser.actionMultipliers.put("Character Development", 1.5);
            inputUser.actionMultipliers.put("Character Complexity", 1.0);
            inputUser.actionMultipliers.put("Entertainment Value", 1.0);
            inputUser.actionMultipliers.put("Emotion", 1.0);
            inputUser.actionMultipliers.put("Pacing", 2.0);
            inputUser.actionMultipliers.put("Logic", 1.0);
            inputUser.actionMultipliers.put("World Building", 0.6);
            inputUser.actionMultipliers.put("Humor", 0.3);
            inputUser.actionMultipliers.put("Rewatchability", 0.4);
            
            // Create comedy multipliers hashmap to store category name (key) and multiplier value 
            inputUser.comedyMultipliers.put("Plot", 0.6);
            inputUser.comedyMultipliers.put("Acting", 2.0);
            inputUser.comedyMultipliers.put("Protagonist", 1.0);
            inputUser.comedyMultipliers.put("Antagonist", 0.0);
            inputUser.comedyMultipliers.put("Side Characters", 1.0);
            inputUser.comedyMultipliers.put("Beginning", 0.6);
            inputUser.comedyMultipliers.put("Ending", 0.6);
            inputUser.comedyMultipliers.put("Cinematography", 0.5);
            inputUser.comedyMultipliers.put("Soundtrack", 0.0);
            inputUser.comedyMultipliers.put("Dialogue", 2.0);
            inputUser.comedyMultipliers.put("Character Development", 0.0);
            inputUser.comedyMultipliers.put("Character Complexity", 0.4);
            inputUser.comedyMultipliers.put("Entertainment Value", 2.5);
            inputUser.comedyMultipliers.put("Emotion", 0.8);
            inputUser.comedyMultipliers.put("Pacing", 0.0);
            inputUser.comedyMultipliers.put("Logic", 0.0);
            inputUser.comedyMultipliers.put("World Building", 0.0);
            inputUser.comedyMultipliers.put("Humor", 2.5);
            inputUser.comedyMultipliers.put("Rewatchability", 1.5);

            // Create drama  multipliers hashmap to store category name (key) and multiplier value 
            inputUser.dramaMultipliers.put("Plot", 1.0);
            inputUser.dramaMultipliers.put("Acting", 2.5);
            inputUser.dramaMultipliers.put("Protagonist", 1.0);
            inputUser.dramaMultipliers.put("Antagonist", 0.0);
            inputUser.dramaMultipliers.put("Side Characters", 1.0);
            inputUser.dramaMultipliers.put("Beginning", 0.6);
            inputUser.dramaMultipliers.put("Ending", 0.6);
            inputUser.dramaMultipliers.put("Cinematography", 0.5);
            inputUser.dramaMultipliers.put("Soundtrack", 0.5);
            inputUser.dramaMultipliers.put("Dialogue", 2.0);
            inputUser.dramaMultipliers.put("Character Development", 1.0);
            inputUser.dramaMultipliers.put("Character Complexity", 2.5);
            inputUser.dramaMultipliers.put("Entertainment Value", 2.0);
            inputUser.dramaMultipliers.put("Emotion", 0.8);
            inputUser.dramaMultipliers.put("Pacing", 0.7);
            inputUser.dramaMultipliers.put("Logic", 0.7);
            inputUser.dramaMultipliers.put("World Building", 0.0);
            inputUser.dramaMultipliers.put("Humor", 0.3);
            inputUser.dramaMultipliers.put("Rewatchability", 0.8);

    }

    public int getNumUsers() {
        this.numUsers++;
        return this.numUsers;
    }

}