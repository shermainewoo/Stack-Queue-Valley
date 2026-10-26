package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface for actors which can be fed
 */
public interface Feedable {
    /**
     * This method is used to define the specific effects of being fed for the actor.
     * @param actor The actor who is feeding the other feedable
     * @param food The item that is being fed to the actor
     * @param location The location of the actor being fed
     * @return the description of what actor is being fed what item
     */
    String fedBy(Actor actor, Item food, Location location);
}
