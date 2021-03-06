package net.novaviper.zeroquest.common.events;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.common.item.tools.ItemNileBow;
import net.novaviper.zeroquest.common.lib.Constants;

public class FOVEvent {
	//Go in AbstractClientPlayer for FOV bow
	
	@SubscribeEvent
    public void FOVBowUpdate(FOVUpdateEvent event){
		if(event.entity.getItemInUse() != null){
			if(event.entity.getItemInUse().getItem() instanceof ItemNileBow){
	            int i = event.entity.getItemInUseDuration();
	            float f1 = (float)i / 20.0F;

	            if (f1 > 1.0F)
	            {
	                f1 = 1.0F;
	            }
	            else
	            {
	                f1 *= f1;
	            }

	            event.newfov *= 1.0F - f1 * 0.15F;
			}
		}
	}
}