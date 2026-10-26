package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Hoe extends Item {
    /**
     * A class representing the Hoe item,
     * has the ability TILLS which is used in the TillAction
     */
    public Hoe() {
        super("Hoe", 'h');
        makePortable();
        this.enableAbility(ToolAbility.TILLS);
    }

    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
