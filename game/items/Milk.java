package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Milk extends Item {
    /**
     * A class representing the Milk Item.
     */
    public Milk() {
        super("Milk", 'M');
        makePortable();
    }
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
