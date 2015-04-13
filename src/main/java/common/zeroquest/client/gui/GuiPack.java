package common.zeroquest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.zertum.EntityDarkZertum;
import common.zeroquest.entity.zertum.EntityDestroZertum;
import common.zeroquest.entity.zertum.EntityForisZertum;
import common.zeroquest.entity.zertum.EntityIceZertum;
import common.zeroquest.entity.zertum.EntityMetalZertum;
import common.zeroquest.entity.zertum.EntityRedZertum;
import common.zeroquest.inventory.ContainerPack;
import common.zeroquest.lib.Constants;

public class GuiPack extends GuiContainer {
	private float xSize_wolf;
	private float ySize_wolf;
	private final EntityCustomTameable entity;
	private final boolean mouseWasDown;

	private static final ResourceLocation gui = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventory.png");
	private static final ResourceLocation guiDark = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryDark.png");
	private static final ResourceLocation guiDestro = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryDestro.png");
	private static final ResourceLocation guiForis = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryForis.png");
	private static final ResourceLocation guiIce = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryIce.png");
	private static final ResourceLocation guiRed = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryRed.png");
	private static final ResourceLocation guiMetal = new ResourceLocation(Constants.modid + ":" + "textures/gui/petInventoryMetal.png");

	public GuiPack(InventoryPlayer inventoryplayer, EntityCustomTameable entityCustomTameable) {
		super(new ContainerPack(inventoryplayer, entityCustomTameable));
		this.mouseWasDown = false;
		this.entity = entityCustomTameable;
		this.allowUserInput = false;
		this.ySize = ySize + 60;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.entity.inventory.hasCustomName() ? this.entity.inventory.getName()
				: StatCollector.translateToLocal(this.entity.inventory.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - 10, 14, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 155 + 2, 4210752);
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		super.drawScreen(i, j, f);
		xSize_wolf = i;
		ySize_wolf = j;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (entity instanceof EntityDarkZertum) {
			this.mc.getTextureManager().bindTexture(guiDark);
		}
		else if (entity instanceof EntityDestroZertum) {
			this.mc.getTextureManager().bindTexture(guiDestro);
		}
		else if (entity instanceof EntityForisZertum) {
			this.mc.getTextureManager().bindTexture(guiForis);
		}
		else if (entity instanceof EntityIceZertum) {
			this.mc.getTextureManager().bindTexture(guiIce);
		}
		else if (entity instanceof EntityRedZertum) {
			this.mc.getTextureManager().bindTexture(guiRed);
		}
		else if (entity instanceof EntityMetalZertum) {
			this.mc.getTextureManager().bindTexture(guiMetal);
		}
		else {
			this.mc.getTextureManager().bindTexture(gui);
		}

		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		for (int j1 = 0; j1 < 3; j1++) {
			for (int k1 = 0; k1 < 5; k1++) {
				drawTexturedModalRect(l + 78 + 18 * k1, i1 + 9 + 18 * j1 + 15, 197, 2, 18, 18);
			}
		}

		GuiInventory.drawEntityOnScreen(l + 42, i1 + 51, 30, l + 51 - xSize_wolf, (i1 + 75) - 50 - ySize_wolf, this.entity);
	}
}
