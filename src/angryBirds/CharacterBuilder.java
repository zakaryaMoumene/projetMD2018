package angryBirds;

public class CharacterBuilder {

    public Character buildCharacter(String characterType, Object... params) {
        switch (characterType) {
        case "bird":
            return new Bird((String) params[0]);
        case "pig":
            return new Pig((String) params[0]);
        case "fire":
            return new Fire((String) params[0]);
        case "wind":
            return new Wind((double) params[0], (String) params[1]);
        case "mushroom":
            return new Mushroom((double) params[0], (String) params[1]);
        case "gInverser":
            return new GravityInverser((Gravity) params[0], (String) params[1]);
        default:
            break;
        }
        return null;
    }
}
