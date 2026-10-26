package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface for ground which is harvestable
 */
public interface Harvestable {
    /**
     * This method is used by a ground subclass to define the effects of being harvested
     * @param actor the actor who harvests the ground
     * @param location the location of the ground being harvested
     * @return the description of what ground was harvested by who
     */
    String harvestBy(Actor actor, Location location);
}
