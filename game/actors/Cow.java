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
import game.items.Milk;
import game.items.ToolAbility;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Cow extends Actor implements Feedable {
    private final Map<Integer, Behaviour<Actor, Action>> behaviours = new TreeMap<>();
    /**
     * A class representing cows.
     * WanderBehaviour is added as a new behaviour for Cow
     */
    public Cow() {
        super("Cow", 'C', 20);
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Selects an action for Cow to perform on its current turn
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     * interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action for Cow to perform on its turn
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
     * Cow's implementation of the fedBy method from feedable.
     *
     * @param actor The actor who is feeding the other actor
     * @param food The item that is being fed to the actor
     * @param location The location of the actor being fed
     * @return a message stating what happened, alongside the change in Cow's statistics,
     * as well as the dropping of a new Milk Item.
     */
    @Override
    public String fedBy(Actor actor, Item food, Location location) {
        this.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.INCREASE,5);

        List<Location> adjacent = location.getNearbyLocations(1);
        Location dropTile = adjacent.get(new Random().nextInt(adjacent.size()));
        dropTile.addItem(new Milk());

        return actor + " feeds "+ this + " with " + food + ".";
    }

    /**
     * The specific allowableActions that Cow provides for the actor.
     * An actor is only provided the FeedAction if the farmer has an item that is EDIBLE in their inventory
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return A list of available actions to perform on the Cow.
     */
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
