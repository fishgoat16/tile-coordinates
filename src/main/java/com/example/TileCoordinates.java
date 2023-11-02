package com.kasi;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
		name = "Tile Coordinates",
		description = "Display current player coordinates",
		tags = {"tile", "coordinate", "id", "location"}
)

public class TileCoordinates extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private TileCoordinatesConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Tile Coordinates plugin started");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Tile Coordinates plugin stopped");
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		WorldPoint worldpoint = WorldPoint.fromLocal(client, client.getLocalPlayer().getLocalLocation());
		int x = worldpoint.getX();
		int y = worldpoint.getY();
		int z = worldpoint.getPlane();
		int id = worldpoint.getRegionID();

		if (config.showCoordinates()) {
			if (x != previousX || y != previousY) {
				previousX = x;
				previousY = y;
				String coordinateString = "Current Coordinate: (" + x + ", " + y + ", " + z +") | Region ID: " + id;
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", coordinateString, null);

			}
		}
	}
	private int previousX = -1;
	private int previousY = -1;

	@Provides
	TileCoordinatesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TileCoordinatesConfig.class);
	}
}
