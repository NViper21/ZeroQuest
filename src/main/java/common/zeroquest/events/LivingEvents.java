package common.zeroquest.events;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import common.zeroquest.entity.EntityMetalZertum;

public class LivingEvents {

	@SubscribeEvent
	public void blastResist(LivingHurtEvent event) {
		if (event.entity instanceof EntityMetalZertum) {
			if (event.source.isExplosion()) {
				EntityMetalZertum dog = (EntityMetalZertum) event.entity;
				if (dog.talents.getLevel("blastresist") != 0) {
					event.ammount = event.ammount / dog.talents.getLevel("blastresist");
				}
			}
		}
		// System.out.println(event.ammount);
	}
}
