package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface for ground which is tillable.
 */
public interface Tillable {
    /**
     * This method is used by a ground subclass to define what the effects of being tilled are.
     * @param actor the actor who tills the ground
     * @param location the location of the ground being tilled
     * @return the description of what ground was tilled by who
     */
    String tilledBy(Actor actor, Location location);
}
