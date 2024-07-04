package ru.solomka.cross;

import ru.solomka.cross.entity.GameSetting;
import ru.solomka.cross.entity.Player;
import ru.solomka.cross.game.GameMap;
import ru.solomka.cross.game.Render;
import ru.solomka.cross.game.enums.GameState;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static GameMap ACTIVE_MAP;

    public static void main() {

        String[] actualRole = {"X", "O"};
        int firstRole = new Random().nextInt(2);

        ACTIVE_MAP = new GameMap(GameState.IDLE, new GameSetting(new int[]{3, 3}, 3)).initMap(Arrays.asList(
                new Player(actualRole[firstRole], Collections.emptyList(), Boolean.parseBoolean(String.valueOf(firstRole))),
                new Player(actualRole[1 - firstRole], Collections.emptyList(), !Boolean.parseBoolean(String.valueOf(firstRole)))
        ).toArray(Player[]::new));

        Render.renderMap(Main.ACTIVE_MAP);

        while (Main.ACTIVE_MAP.getState() == GameState.IDLE) {

            Main.ACTIVE_MAP.getActivePlayer().move();

            if(Main.ACTIVE_MAP.getFreePositions() == 0) {
                Main.ACTIVE_MAP.setState(GameState.END);
                System.out.println("Игра окончена!");
            }
        }
    }
}