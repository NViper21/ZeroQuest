/*
 ** 2014 January 29
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.core.handlers;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;

/**
 * Replaces the splash text with a random custom one sometimes.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ZeroQuestSplash {
    
    private static final Logger L = LogManager.getLogger();
    private static final ResourceLocation RESOURCE_SPLASHES = new ResourceLocation(ZeroQuest.modid, "splashes.txt");
    public static final String[] GUIMAINMENU_SPLASHTEXT = new String[] {"splashText", "field_73975_c"};
    
    private final Random rand = new Random();
    private List<String> splashLines;

    public ZeroQuestSplash() {
        try {
            InputStream is = null;
            try {
                is = Minecraft.getMinecraft().getResourceManager().getResource(RESOURCE_SPLASHES).getInputStream();
                splashLines = IOUtils.readLines(is, "UTF-8");
            } finally {
                IOUtils.closeQuietly(is);
            }
        } catch (Throwable t) {
            L.warn("Can't load splashes", t);
        }
    }
    
    @SubscribeEvent
    public void onOpenGui(GuiOpenEvent evt) {
        if (evt.gui instanceof GuiMainMenu) {
            try {
                GuiMainMenu menu = (GuiMainMenu) evt.gui;
                String splash = getSplashText(menu);
                if (splash.equals("Kind of dragon free!")) {
                    splash = "Not really dragon free!";
                    setSplashText(menu, splash);
                } else if (splashLines != null && !splashLines.isEmpty() && rand.nextInt(10) == 0) {
                    splash = splashLines.get(rand.nextInt(splashLines.size()));
                    setSplashText(menu, splash);
                }
            } catch (Throwable t) {
                L.warn("Can't override splash", t);
            }
        }
    }
    
    private String getSplashText(GuiMainMenu menu) {
        return ReflectionHelper.getPrivateValue(GuiMainMenu.class, menu, GUIMAINMENU_SPLASHTEXT);
    }
    
    private void setSplashText(GuiMainMenu menu, String splash) {
        ReflectionHelper.setPrivateValue(GuiMainMenu.class, menu, splash, GUIMAINMENU_SPLASHTEXT);
    }
}