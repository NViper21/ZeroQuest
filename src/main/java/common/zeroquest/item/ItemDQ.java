package common.zeroquest.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import common.zeroquest.ZeroQuest;

public class ItemDQ extends Item {

	public ItemDQ() {
		super();
		this.setCreativeTab(ZeroQuest.DarkTab);
	}

	/**
	 * Checks if this entity is running on a client.
	 * 
	 * Required since MCP's isClientWorld returns the exact opposite...
	 * 
	 * @return true if the entity runs on a client or false if it runs on a
	 *         server
	 */
	public boolean isClient(Entity entity) {
		return entity.worldObj.isRemote;
	}

	/**
	 * Checks if this entity is running on a server.
	 * 
	 * @return true if the entity runs on a server or false if it runs on a
	 *         client
	 */
	public boolean isServer(Entity entity) {
		return !entity.worldObj.isRemote;
	}
}
