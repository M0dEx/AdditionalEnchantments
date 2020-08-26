package eu.m0dex.additionalenchantments.playerdata;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerCache {

    Map<UUID, PlayerData> playerMap;

    public PlayerCache() {

        playerMap = new HashMap<>();
    }

    public void addPlayer(UUID uuid) {

        if(!playerMap.containsKey(uuid))
            playerMap.put(uuid, new PlayerData());
    }

    public PlayerData getPlayerData(UUID uuid) {

        addPlayer(uuid);

        return playerMap.get(uuid);
    }

    public void removePlayer(UUID uuid) {

        playerMap.remove(uuid);
    }
}
