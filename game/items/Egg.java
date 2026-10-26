package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Egg extends Item {
    /**
     * A class representing the Egg item.
     */
    public Egg() {
        super("Egg", '0');
        makePortable();
    }
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
