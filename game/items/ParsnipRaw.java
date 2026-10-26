package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actions.ConsumeAction;

public class ParsnipRaw extends Item implements Consumable, Food {
    /**
     * A class representing portable parsnips. Differentiated from crop as ParsnipRaw
     */
    public ParsnipRaw() {
        super("Parsnip", 'p');
        makePortable();
        this.enableAbility(ToolAbility.EDIBLE);
    }

    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }

    /**
     * The specific allowableActions that Parsnips provide for the actor.
     * @param actor the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return A list of available actions to perform on Parsnip
     */
    public ActionList allowableActions(Actor actor, GameMap map){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Parsnips specific implementation of consumeBy method from Consumable.
     * @param actor the actor who consumes the item
     * @return a message stating what the effect was, alongside a change in actors health,
     * and the removal of the item from inventory
     */
    @Override
    public String consumeBy(Actor actor) {
        actor.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 1);
        actor.getInventory().remove(this);
        return actor + " eats the Parsnip, and 1 HP is restored.";
    }

    /**
     * Parsnips specific implementation of applyFarmerEffect from Food
     * @param actor the actor who is feeding the item.
     */
    @Override
    public void applyFarmerEffect(Actor actor) {
        actor.modifyStatisticMaximum(ActorStatistics.STAMINA, StatisticOperations.INCREASE,1);
        actor.modifyStatistic(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 1);
    }

    /**
     * Parsnips specific implementation of farmerEffectDescription from Food
     * @param actor the actor who is feeding the item.
     */
    @Override
    public void farmerEffectDescription(Actor actor) {
        System.out.println (actor + " has max stamina raised by 1");
    }
}
