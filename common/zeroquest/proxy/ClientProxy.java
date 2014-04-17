package common.zeroquest.proxy;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.model.ModelDarkZertum;
import common.zeroquest.entity.model.ModelDestroZertum;
import common.zeroquest.entity.model.ModelRedZertum;
import common.zeroquest.entity.model.ModelZertum;
import common.zeroquest.entity.render.RenderDarkZertum;
import common.zeroquest.entity.render.RenderDestroZertum;
import common.zeroquest.entity.render.RenderRedZertum;
import common.zeroquest.entity.render.RenderZertum;
import common.zeroquest.renderer.ItemRendererNileTable;
import common.zeroquest.renderer.RendererNileTable;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy{

	
	public void registerRenderThings() {
	   	RenderingRegistry.registerEntityRenderingHandler(EntityZertum.class, new RenderZertum(new ModelZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityRedZertum.class, new RenderRedZertum(new ModelRedZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDarkZertum.class, new RenderDarkZertum(new ModelDarkZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDestroZertum.class, new RenderDestroZertum(new ModelDestroZertum(), 0.5F));
	   	
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNileTable.class, new RendererNileTable());
	   	MinecraftForgeClient.registerItemRenderer(ModBlocks.nileWorktable.blockID, new ItemRendererNileTable());
}
	public void reigsterClientLangugaes(){
		//Creative Tabs//
	   	LanguageRegistry.instance().addStringLocalization(ZeroQuest.ZeroTab.getTranslatedTabLabel(), "Zero Quest Tab");
	   	
	   	//Entities//
	   	LanguageRegistry.instance().addStringLocalization("entity.Zertum.name", "Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.RedZertum.name", "Red Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.DarkZertum.name", "Dark Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.DestroZertum.name", "Destro Zertum");
	   	//Achievements
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZQuestStart.getName(), "Start Zero Quest");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZQuestStart.getDescription(), "Gather Grains, Dust, or Essence");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNWorkBench.getName(), "Nile Crafting");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNWorkBench.getDescription(), "Craft a Nile Crafting Table");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNileSword.getName(), "Nile Combat");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNileSword.getDescription(), "Craft a Nile Sword");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertKill.getName(), "Death to the Savages!");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertKill.getDescription(), "Kill a Zertum");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildBone.getName(), "Nile Taming");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildBone.getDescription(), "Craft a Nile Bone");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertTame.getName(), "Zertum, My Pet!");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertTame.getDescription(), "Tame a Zertum");
	}
	
	public void registerSound(){
		
		MinecraftForge.EVENT_BUS.register(new SoundManagerZQuest());
	}

}