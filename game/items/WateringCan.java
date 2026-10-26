package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class WateringCan extends Item {
    /**
     * A class representing the Watering Can item,
     * has the ability WATERS which is used in the WaterAction
     */
    public WateringCan() {
        super("Watering Can", 'w');
        makePortable();
        this.enableAbility(ToolAbility.WATERS);
    }

    public void tick(Location currentLocation) {
        super.tick(currentLocation);
    }
}
