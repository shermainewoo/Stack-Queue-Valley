package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Tillable;

/**
 * Class represents an action to till the ground.
 */
public class TillAction extends Action {
    /**
     * The ground that is to be tilled.
     */
    private final Tillable tillable;

    /**
     * Constructor
     * @param tillable is the ground that is tillable.
     */
    public TillAction(Tillable tillable) {
        this.tillable = tillable;
    }

    /**
     * Executes the grounds implementation of tilledBy
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of tilledBy for tillable.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return tillable.tilledBy(actor, map.locationOf(actor));
    }

    /**
     * A description stating what actor tills which tillable ground
     * @param actor The actor performing the action.
     * @return a string stating what the actor will till.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " tills the " + tillable;
    }
}
