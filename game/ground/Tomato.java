package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.HarvestAction;
import game.items.TomatoRaw;

public class Tomato extends Ground implements Harvestable {
    /**
     * A class representing tomatoes.
     */
    public Tomato() {
        super('T', "Tomato");
    }

    /**
     * Tomatoes implementation of harvestBy method from Harvestable.
     * If actor is not capable of harvesting the Tomato, a message stating why is returned
     * @param actor the actor who harvests the ground
     * @param location the location of the ground being harvested
     * @return a message stating what happened, alongside the change in ground
     * a change in the actors stamina, and the addition of a new item
     */
    @Override
    public String harvestBy(Actor actor, Location location) {
        if (actor.getStatistic(ActorStatistics.STAMINA) < 2){
            return actor + " is too tired to harvest the crop.";
        }
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.DECREASE, 2);
        actor.modifyStatisticMaximum(ActorStatistics.STAMINA, StatisticOperations.INCREASE, 1);
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.INCREASE, 1);
        location.setGround(new Dirt());
        actor.getInventory().add(new TomatoRaw());
        actor.getInventory().add(new TomatoRaw());
        return "Tomato was harvested by " + actor;
    }

    /**
     * The specific allowableActions that parsnips provides for the actor
     * HarvestActions are only provided if the actor is adjacent or on top of a harvestable ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of available actions to perform on Tomato
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        Location actorLocation = location.map().locationOf(actor);
        if (actorLocation ==location || location.getNearbyLocations(1).contains(actorLocation)){
            actions.add(new HarvestAction(this, location));
        }
        return actions;
    }
}
