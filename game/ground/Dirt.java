package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.*;
import game.actions.TillAction;
import game.actions.WaterAction;
import game.items.ToolAbility;

public class Dirt extends Ground implements Tillable, Waterable {
    /**
     * A class representing dirt on the ground.
     */
    public Dirt() {
        super('.', "Dirt");
    }

    /**
     * Dirt's implementation of the tilledBy method from Tillable.
     * If actor is not capable of tilling the ground, a message stating why is returned.
     * @param actor the actor who tills the ground
     * @param location the location of the ground being tilled
     * @return a message stating what happened, alongside the change in ground set
     * as well as a change in actors stamina
     */
    @Override
    public String tilledBy(Actor actor, Location location) {
        if (!actor.hasAbility(ToolAbility.TILLS)){
            return actor + " does not have a hoe.";
        }
        if (actor.getStatistic(ActorStatistics.STAMINA) < 2) {
            return actor + " does not have enough stamina.";
        }
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.DECREASE, 2);
        location.setGround(new Soil());
        return "Dirt was tilled by " + actor;
    }

    /**
     * Dirt's implementation of the wateredBy method from Waterable.
     * If actor is not capable of tilling the ground, a message stating why is returned.
     * @param actor the actor who waters the ground
     * @param location the location of the ground being watered
     * @return a message stating what happened, alongside the change in ground set
     * as well as a change in actors stamina
     */
    @Override
    public String wateredBy(Actor actor, Location location) {
        if (!actor.hasAbility(ToolAbility.WATERS)){
            return actor + " does not have a watering can.";
        }
        if (actor.getStatistic(ActorStatistics.STAMINA) < 2) {
            return actor + " does not have enough stamina.";
        }
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.DECREASE, 2);
        location.setGround(new Puddle());
        return "Dirt was watered by " + actor;
    }

    /**
     * The specific allowableActions that dirt provides for the actor.
     * TillActions or WaterActions are only provided for Dirt the actor is directly ontop of.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of available actions to perform on Dirt.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (location.map().locationOf(actor)==location){
            actions.add(new TillAction(this));
            actions.add(new WaterAction(this));
        }
        return actions;
    }
}
