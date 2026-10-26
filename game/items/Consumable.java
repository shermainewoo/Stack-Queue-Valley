package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for items which are consumable
 */
public interface Consumable {
    /**
     * This method is used to define the specific effects of being consumed for the actor
     * @param actor the actor who consumes the item
     * @return the description of which actor consumes what item.
     */
    String consumeBy(Actor actor);
}
