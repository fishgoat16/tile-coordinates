package com.kasi;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Tile Coordinates")
public interface TileCoordinatesConfig extends Config
{
	@ConfigItem(
			keyName = "showCoordinates",
			name = "Display Coordinates",
			description = "Display current player coordinates"
	)
	default boolean showCoordinates()
	{
		return true;
	}
}
