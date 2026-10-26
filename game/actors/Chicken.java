package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.FeedAction;
import game.items.Egg;
import game.items.ToolAbility;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Chicken extends Actor implements Feedable {
    private static final double CHICKEN_CHANCE = 0.5;
    private final Map<Integer, Behaviour<Actor, Action>> behaviours = new TreeMap<>();

    /**
     * A class representing chickens.
     * WanderBehaviour is added as a new behaviour for Chicken
     */
    public Chicken() {
        super("Chicken", 'c', 10);
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Selects an action for Chicken to perform on its current turn
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     * interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action for Chicken to perform on its turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour<Actor, Action> behaviour: behaviours.values()) {
            Action action = behaviour.operate(this, map.locationOf(this));
            if (action != null)
                return action;
        }
        return lastAction;
    }

    /**
     * Chicken's implementation of the fedBy method from feedable.
     * Random is used so that there is a 50% chance of the chicken dropping an additional egg when fed
     *
     * @param actor The actor who is feeding the other actor
     * @param food The item that is being fed to the actor
     * @param location The location of the actor being fed
     * @return a message stating what happened, alongside the change in chickens statistics,
     * as well as the dropping of a new Egg Item.
     */
    @Override
    public String fedBy(Actor actor, Item food, Location location) {
        double random = Math.random();
        this.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 3);

        List<Location> adjacent = location.getNearbyLocations(1);
        Location dropTile = adjacent.get(new Random().nextInt(adjacent.size()));
        dropTile.addItem(new Egg());

        if (random < CHICKEN_CHANCE){
            dropTile.addItem(new Egg());
        }

        return actor + " feeds "+ this + " with " + food + ".";
    }

    /**
     * The specific allowableActions that Chicken provides for the actor.
     * An actor is only provided the FeedAction if the farmer has an item that is EDIBLE in their inventory
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return A list of available actions to perform on the Chicken.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item item: otherActor.getInventory().getItems()) {
            if (item.hasAbility(ToolAbility.EDIBLE)) {
                actions.add(new FeedAction(this, item));
            }
        }
        return actions;
    }
}
