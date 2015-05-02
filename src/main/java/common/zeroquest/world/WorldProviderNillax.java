package common.zeroquest.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

import common.zeroquest.ZeroQuest;
import common.zeroquest.world.gen.ChunkProviderNillax;

public class WorldProviderNillax extends WorldProvider {
	/** Array for sunrise/sunset colors (RGBA) */
	private final float[] colorsSunriseSunset = new float[4];

	@Override
	public void registerWorldChunkManager() {
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkMangerNillax(worldObj.getSeed(), worldObj.getWorldInfo().getTerrainType());
		this.hasNoSky = false;
		this.dimensionId = ZeroQuest.NillaxID;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderNillax(this.worldObj, this.worldObj.getSeed(), true, worldObj.getWorldInfo().getGeneratorOptions());
	}

	/** Get Provider for dimension **/
	public static WorldProvider getProviderForDimension(int p_76570_0_) {
		return DimensionManager.createProviderFor(ZeroQuest.NillaxID);
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName() {
		return "Nillax";
	}

	@Override
	/** Welcome message **/
	public String getWelcomeMessage() {
		return "Entering the Nillax Dimension";
	}

	@Override
	public String getDepartMessage() {
		return "Leaving the Nillax Dimension";
	}

	@Override
	/** Can player re-spawn here **/
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getInternalNameSuffix() {
		// TODO Auto-generated method stub
		return "_nillax";
	}

}