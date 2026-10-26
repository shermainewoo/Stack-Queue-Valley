package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Harvestable;

/**
 * Class represents an action to harvest the ground
 */
public class HarvestAction extends Action {
    /**
     * The location of the ground to be harvested
     */
    private final Location location;

    /**
     * The ground that is to be harvested.
     */
    private final Harvestable harvestable;

    /**
     * Constructor
     * @param harvestable is the ground that is harvestable
     * @param location is the location of the ground
     */
    public HarvestAction(Harvestable harvestable, Location location) {
        this.harvestable = harvestable;
        this.location = location;
    }

    /**
     * Executes the grounds implementation of harvestBy
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of harvestBy for harvestable
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return harvestable.harvestBy(actor, location);
    }

    /**
     * A description stating what actor harvests what harvestable ground
     * @param actor The actor performing the action.
     * @return a string stating what the actor will harvest
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests the " + harvestable + ".";
    }
}
