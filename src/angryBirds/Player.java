package angryBirds;

// classe joueur encore incomplète. On aurait souhaité mettre en place un système pour le scoring
public class Player {

    private String name;
    private int mouseX, mouseY;

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
