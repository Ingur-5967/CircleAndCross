package ru.solomka.cross.game;

import ru.solomka.cross.entity.Player;
import ru.solomka.cross.game.GameMap;
import ru.solomka.cross.utils.IntPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Render {

    public static void renderMap(GameMap gameMap) {

        List<String> map = new ArrayList<>();

        StringBuilder line = new StringBuilder();

        Map<Player, List<IntPair>> busyCoordinates = gameMap.getBusyPositions();

        for (int y = 0; y < gameMap.getGameSetting().getMapSize()[1]; y++) {
            for(int x = 0; x < gameMap.getGameSetting().getMapSize()[0]; x++) {

                String element = STR."| {pos\{y}\{x}} | ";
                String replacedElement = null;

                for(Map.Entry<Player, List<IntPair>> aMap : busyCoordinates.entrySet())
                    if(aMap.getValue().contains(new IntPair(x, y))) {
                        replacedElement = element.replace(element, STR." \{aMap.getKey().getName()} ");
                    }

                if(replacedElement == null)
                    replacedElement = " {} ";

                line.append(replacedElement);
            }
            map.add(STR."[\{y}]:  \{line.toString()}\n");
            line = new StringBuilder();
        }


        System.out.println("      [0] [1] [2]");
        for(String component : map) {
            System.out.println(component);
        }

    }
}
