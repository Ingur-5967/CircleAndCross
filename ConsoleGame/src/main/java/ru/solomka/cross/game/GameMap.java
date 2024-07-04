package ru.solomka.cross.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.solomka.cross.Main;
import ru.solomka.cross.entity.GameSetting;
import ru.solomka.cross.entity.Player;
import ru.solomka.cross.game.enums.GameEndResult;
import ru.solomka.cross.game.enums.GameState;
import ru.solomka.cross.utils.IntPair;

import java.util.*;

@Getter
@NoArgsConstructor(force = true)
public class GameMap {

    private static final List<Player> PLAYERS = new ArrayList<>();

    @Setter private GameState state;

    private final GameSetting gameSetting;

    public GameMap(GameState state, GameSetting gameSetting) {
        this.state = state;
        this.gameSetting = gameSetting;
    }

    public GameMap initMap(Player ...players) {
        System.out.println("[Log] Map is init!");
        PLAYERS.addAll(Arrays.asList(players));
        return this;
    }

    public Player getOfflinePlayer() {
        return PLAYERS.stream().filter(p -> !p.isActiveRole())
                .findAny().orElse(null);
    }

    public Player getActivePlayer() {
        return PLAYERS.stream().filter(Player::isActiveRole)
                .findAny().orElse(null);
    }

    public int getFreePositions() {
        int busyCoordsActive = getActivePlayer().getPositions().size();
        int busyCoordsPassive = getOfflinePlayer().getPositions().size();
        GameSetting setting = Main.ACTIVE_MAP.getGameSetting();
        return (setting.getMapSize()[0] * setting.getMapSize()[1]) - (busyCoordsActive + busyCoordsPassive);
    }

    public Map<Player, List<IntPair>> getBusyPositions() {
        Map<Player, List<IntPair>> positions = new LinkedHashMap<>();
        PLAYERS.forEach(p -> positions.put(p, p.getPositions()));
        return positions;
    }
    
    public boolean isWinPosition(Player player) {
        for(Map.Entry<GameEndResult, List<Boolean>> aMap : GameEndResult.check(player).entrySet()) {
            if(aMap.getValue().size() == 3) return true;
        }
        return false;
    }
}