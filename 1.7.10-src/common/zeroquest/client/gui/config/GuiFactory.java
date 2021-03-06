package common.zeroquest.client.gui.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.ConfigElement;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiFactory implements IModGuiFactory
{
	//Get Examples from ForgeGuiFactory\\ TODO

	@Override
	public void initialize(Minecraft minecraftInstance) {
		
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ModGuiConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
	
	public static class ModGuiConfig extends GuiConfig{

		public ModGuiConfig(GuiScreen guiScreen){
			super(guiScreen, 
					getConfigElements(), ZeroQuest.modid, false, false, I18n.format("gui.config.title"));
		}
		
	    private static List<IConfigElement> getConfigElements()
	    {
	        List<IConfigElement> list = new ArrayList<IConfigElement>();
	        list.add(new DummyCategoryElement("function", "gui.config.function", GeneralEntry.class));
	        list.add(new DummyCategoryElement("load", "gui.config.load", ElementEntry.class));
	        //list.add(new DummyCategoryElement("misc", "gui.config.misc", MiscEntry.class));
	        return list;
	    }
	    
	    /**
	     * This custom list entry provides the General Settings entry on the Minecraft Forge Configuration screen. 
	     * It extends the base Category entry class and defines the IConfigElement objects that will be used to build the child screen.
	     */
	    public static class GeneralEntry extends CategoryEntry
	    {
	        public GeneralEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
	        {
	            super(owningScreen, owningEntryList, prop);
	        }
	        
	        @Override
	        protected GuiScreen buildChildScreen()
	        {
	            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
	            // GuiConfig object's entryList will also be refreshed to reflect the changes.
	            return new GuiConfig(this.owningScreen, 
	                    (new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_FUNCTION)).getChildElements()), 
	                    this.owningScreen.modID, ConfigHandler.CATEGORY_FUNCTION, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, 
	                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
	                    GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()));
	        }
	    }

	    public static class ElementEntry extends CategoryEntry
	    {
	        public ElementEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
	        {
	            super(owningScreen, owningEntryList, prop);
	        }
	        
	        @Override
	        protected GuiScreen buildChildScreen()
	        {
	            return new GuiConfig(this.owningScreen,
	                    (new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_LOAD)).getChildElements()), 
	                    this.owningScreen.modID, ConfigHandler.CATEGORY_FUNCTION, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, 
	                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
	                    GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()));
	        }
	    }
	    
	    public static class MiscEntry extends CategoryEntry
	    {
	        public MiscEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
	        {
	            super(owningScreen, owningEntryList, prop);
	        }
	        
	        @Override
	        protected GuiScreen buildChildScreen()
	        {
	            return new GuiConfig(this.owningScreen,
	                    (new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_MISC)).getChildElements()), 
	                    this.owningScreen.modID, ConfigHandler.CATEGORY_MISC, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, 
	                    this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
	                    GuiConfig.getAbridgedConfigPath(ForgeModContainer.getConfig().toString()));
	        }
	    }
	}
}
