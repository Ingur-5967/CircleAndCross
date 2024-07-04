package ru.solomka.cross.game.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.solomka.cross.Main;
import ru.solomka.cross.entity.Player;
import ru.solomka.cross.utils.IntPair;

import java.util.*;

@Getter
public enum GameEndResult {

    DIGITAL_RIGHT(Arrays.asList(
            new IntPair(2, 0),
            new IntPair(1, 1),
            new IntPair(0, 2)
    )),

    DIGITAL_LEFT(Arrays.asList(
            new IntPair(2, 2),
            new IntPair(1, 1),
            new IntPair(0, 0)
    )),

    LINE_VERTICAL_1(Arrays.asList(
            new IntPair(0, 0),
            new IntPair(0, 1),
            new IntPair(0, 2)
    )),

    LINE_VERTICAL_2(Arrays.asList(
            new IntPair(1, 0),
            new IntPair(1, 1),
            new IntPair(1, 2)
    )),

    LINE_VERTICAL_3(Arrays.asList(
            new IntPair(2, 0),
            new IntPair(2, 1),
            new IntPair(2, 2)
    )),

    LINE_HORIZONTAL_1(Arrays.asList(
            new IntPair(0, 0),
            new IntPair(1, 0),
            new IntPair(2, 0)
    )),

    LINE_HORIZONTAL_2(Arrays.asList(
            new IntPair(0, 1),
            new IntPair(1, 1),
            new IntPair(2, 1)
    )),

    LINE_HORIZONTAL_3(Arrays.asList(
            new IntPair(0, 2),
            new IntPair(1, 2),
            new IntPair(2, 2)
    ));

    private final List<IntPair> validState;

    GameEndResult(List<IntPair> validState) {
        this.validState = validState;
    }

    public static @NotNull Map<GameEndResult, List<Boolean>> check(Player player) {

        Map<GameEndResult, List<Boolean>> checkMap = new HashMap<>();

        List<Boolean> checkList;
        for(GameEndResult result : GameEndResult.values()) {
            checkList = new ArrayList<>();
            List<IntPair> positions = result.getValidState();
            for(IntPair position : positions) {
                for(IntPair busyPosition : Main.ACTIVE_MAP.getBusyPositions().get(player)) {
                    if(position.getFirstNumber() != busyPosition.getFirstNumber() || position.getSecondNumber() != busyPosition.getSecondNumber()) continue;
                    checkList.add(true);
                }
            }
            checkMap.put(result, checkList);
        }

        return checkMap;
    }
}