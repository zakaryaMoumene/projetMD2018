package angryBirds;

// pour fournir les objet allant composer l'ar�ne  
public class ArenaComponentBuilder {
   
    public Gravity createGravity(double d) {
        return new Gravity(d);
    }

    public CollisionEngine createCollisionEngine() {
        return new CollisionEngine();
    }
}
