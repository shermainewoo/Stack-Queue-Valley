package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface for ground which is waterable
 */
public interface Waterable {
    /**
     * This method is used by a ground subclass to define the effects of being watered
     * @param actor the actor who waters the ground
     * @param location the location of the ground being watered
     * @return the description of what ground was watered by who
     */
    String wateredBy(Actor actor, Location location);
}
