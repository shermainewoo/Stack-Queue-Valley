package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Waterable;

/**
 * Class represents an action to water the ground.
 */
public class WaterAction extends Action {
    /**
     * The ground that is to be watered.
     */
    private final Waterable waterable;

    /**
     * Constructor
     * @param waterable is the ground that is waterable
     */
    public WaterAction(Waterable waterable) {
        this.waterable = waterable;
    }

    /**
     * Executes the grounds implementation of wateredBy
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of wateredBy for waterable.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return waterable.wateredBy(actor, map.locationOf(actor));
    }

    /**
     * A description stating what actor waters what waterable ground
     * @param actor The actor performing the action.
     * @return a string stating what the actor will water
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + waterable;
    }
}
