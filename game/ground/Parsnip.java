package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.HarvestAction;
import game.items.ParsnipRaw;

import java.util.List;
import java.util.Random;

public class Parsnip extends Ground implements Harvestable {
    private static final double PARSNIP_CHANCE = 0.5;

    /**
     * A class representing parsnips.
     */
    public Parsnip() {
        super('P', "Parsnip");
    }

    /**
     * Parsnips implementation of harvestBy method from Harvestable.
     * If actor is not capable of harvesting the Parsnip, a message stating why is returned
     * @param actor the actor who harvests the ground
     * @param location the location of the ground being harvested
     * @return a message stating what happened, alongside the change in ground set
     * a change in the actors stamina, and the addition of a new item.
     *
     * Random is also used to give a 50% chance of dropping a parsnip item.
     */
    @Override
    public String harvestBy(Actor actor, Location location) {
        double random = Math.random();

        if (actor.getStatistic(ActorStatistics.STAMINA) < 1){
            return actor + " is too tired to harvest the crop.";
        };
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.DECREASE, 1);
        location.setGround(new Dirt());
        actor.getInventory().add(new ParsnipRaw());

        if (random>PARSNIP_CHANCE){
            List<Location> adjacent = location.getNearbyLocations(1);
            Location dropTile = adjacent.get(new Random().nextInt(adjacent.size()));
            dropTile.addItem(new ParsnipRaw());
            return "Two parsnips were harvested by " + actor;
        }
        return "Parsnip was harvested by " + actor;
    }

    /**
     * The specific allowableActions that parsnips provides for the actor
     * HarvestActions are only provided if the actor is adjacent or on top of a harvestable ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of available actions to perform on Parsnip
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
