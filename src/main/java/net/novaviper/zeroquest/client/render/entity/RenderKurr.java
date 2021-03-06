package net.novaviper.zeroquest.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.common.entity.mob.EntityKurr;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.util.ResourceReference;

@SideOnly(Side.CLIENT)
public class RenderKurr extends RenderLiving {

	public RenderKurr(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_) {

		super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
	}

	protected ResourceLocation getTextures(EntityKurr par1EntityJakan) {
		return ResourceReference.kurr;
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getTextures((EntityKurr) par1Entity);
	}
}