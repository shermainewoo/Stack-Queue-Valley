package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Class represents an action to eat an item.
 */
public class ConsumeAction extends Action {
    /**
     * The item that is to be consumed.
     */
    private final Consumable consumable;

    /**
     * Constructor
     * @param consumable is the item that is consumable
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the items implementation of consumeBy
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of consumeBy for consumable.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consumeBy(actor);
    }

    /**
     * A description stating what actor consumes which consumable item
     * @param actor The actor performing the action.
     * @return a string stating what the actor will consume
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable;
    }
}
