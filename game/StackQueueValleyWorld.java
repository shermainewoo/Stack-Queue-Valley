package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Chicken;
import game.actors.Cow;
import game.actors.Farmer;
import game.ground.Dirt;
import game.ground.Parsnip;
import game.ground.Tomato;

import java.util.Arrays;
import java.util.List;

public class StackQueueValleyWorld extends World {

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public StackQueueValleyWorld(Display display) {
        super(display);
    }

    public void initialise() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Dirt::new);
        groundCreator.registerGround('P', Parsnip::new);
        groundCreator.registerGround('T', Tomato::new);

        List<String> inheritedFarm = Arrays.asList(
                "..P....T..",
                "..........",
                ".......P..",
                "...T......",
                "..........",
                ".P........",
                ".......T.."
        );

        GameMap inheritedFarmMap = new GameMap("Inherited Farm", groundCreator, inheritedFarm);
        this.addGameMap(inheritedFarmMap);

        Farmer farmer = new Farmer("Bob");
        this.addPlayer(farmer, inheritedFarmMap.at(5, 3));

        inheritedFarmMap.at(0,0).addActor(new Chicken());
        inheritedFarmMap.at(5,5).addActor(new Cow());
    }
}
