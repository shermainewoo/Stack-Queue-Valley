package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.items.Hoe;
import game.items.WateringCan;

public class Farmer extends Actor {
    /**
     * a Hoe and WateringCan is added to the inventory when first created,
     * alongside the new stamina statistic, using STAMINA_CONSTANT
     * @param name the name of the farmer
     */
    public Farmer(String name) {
        super("Farmer " + name, '@', 100);
        int STAMINA_CONSTANT = 20;
        this.addNewStatistic(ActorStatistics.STAMINA, new BaseStatistic(STAMINA_CONSTANT));

        this.getInventory().add(new Hoe());
        this.getInventory().add(new WateringCan());
    }

    /**
     * Each turn, a menu is printed which displays the available actions that the Farmer can take
     * It also prints out the current HP, Stamina and Inventory is printed
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do
     * interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return a menu displaying current stats and actions.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            this.unconscious(map);
            return new DoNothingAction();
        }
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        this.modifyStatistic(ActorStatistics.STAMINA, StatisticOperations.INCREASE, 1);

        StringBuilder inventoryList = new StringBuilder();
        for (Item item : this.getInventory().getItems()){
            if (inventoryList.length() > 0){
                inventoryList.append(", ");
            }
            inventoryList.append(item);
        }

        display.println(this.name);
        display.println("HP: "+ this.getStatistic(ActorStatistics.HEALTH));
        display.println("Stamina: "+ this.getStatistic(ActorStatistics.STAMINA));
        display.println("Inventory: [" + inventoryList + "]");

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
}
