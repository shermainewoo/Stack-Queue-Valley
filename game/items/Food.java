package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for items which can be fed to feedables
 */
public interface Food {
    void applyFarmerEffect(Actor actor);

    void farmerEffectDescription(Actor actor);
}