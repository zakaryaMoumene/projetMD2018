package angryBirds;

public class GravityInverser extends Character {

    private static final long serialVersionUID = 1L;

    private Gravity g;
    public GravityInverser(Gravity g, String imageUrl) {
        setDesign(imageUrl);
        setSizeX(40);
        setSizeY(40);
        this.g = g;
    }

    @Override
    public Integer collisionWith(Character character) {
        if(character instanceof Bird){
            g.inverse();
            setActivated(false);
        }
        return null;
    }

}
