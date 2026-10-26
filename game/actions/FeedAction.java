package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actors.Feedable;
import game.items.Food;
import game.items.ToolAbility;

/**
 * Class represents an action to feed another actor
 */
public class FeedAction extends Action {
    /**
     * The actor that is being fed
     */
    private final Feedable feedable;
    /**
     * The item being fed to the actor
     */
    private final Item item;

    /**
     * Constructor
     * @param feedable is the actor being fed
     * @param item is what the actor is being fed
     */
    public FeedAction(Feedable feedable, Item item) {
        this.feedable = feedable;
        this.item = item;
    }

    /**
     * Executes the actors implementation of fedBy, if action is not able to be executed,
     * a message stating why is returned instead.
     *
     * The stamina of the farmer is decreased and the item in their inventory is removed
     * While any specific food affects are applied to the farmer as well.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of fedBy for the feedable actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getStatistic(ActorStatistics.STAMINA) < 2){
            return actor + " does not have enough stamina.";
        }
        actor.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.DECREASE, 2);
        actor.getInventory().remove(item);
        if (item.hasAbility(ToolAbility.EDIBLE)) {
            Food food = (Food) item;
            food.applyFarmerEffect(actor);
            food.farmerEffectDescription(actor);
        }

        return feedable.fedBy(actor, item, map.locationOf(actor));
    }

    /**
     * A description stating who the actor feed and what item they feed with.
     * @param actor The actor performing the action.
     * @return a string stating the feedable and the item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds the " + feedable + " " +item+  ".";
    }
}
