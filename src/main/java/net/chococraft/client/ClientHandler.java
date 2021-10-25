package net.chococraft.client;

import net.chococraft.Chococraft;
import net.chococraft.client.gui.NestScreen;
import net.chococraft.client.models.armor.ChocoDisguiseModel;
import net.chococraft.client.models.entities.AdultChocoboModel;
import net.chococraft.client.renderer.entities.ChocoboRenderer;
import net.chococraft.common.init.ModContainers;
import net.chococraft.common.init.ModEntities;
import net.chococraft.common.init.ModRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static final ModelLayerLocation CHOCOBO = new ModelLayerLocation(new ResourceLocation(Chococraft.MODID, "main"), "chocobo");
    public static final ModelLayerLocation CHOCO_DISGUISE = new ModelLayerLocation(new ResourceLocation(Chococraft.MODID, "main"), "choco_disguise");

    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModRegistry.GYSAHL_GREEN.get(), RenderType.cutout());

        MenuScreens.register(ModContainers.NEST.get(), NestScreen::new);
    }

    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CHOCOBO.get(), ChocoboRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CHOCOBO, () -> AdultChocoboModel.createBodyLayer());
        event.registerLayerDefinition(CHOCO_DISGUISE, () -> ChocoDisguiseModel.createArmorDefinition());
    }

//    public void openChocoboInfoGui(EntityChocobo chocobo, PlayerEntity player) {
//        super.openChocoboInfoGui(chocobo, player);
//        Minecraft.getMinecraft().displayGuiScreen(new GuiChocoboInfo(chocobo, player));
//    }
//
//    public void openChocoBook(PlayerEntity player) {
//        super.openChocoBook(player);
//        Minecraft.getMinecraft().displayGuiScreen(new GuiChocoboBook(player));
//    }
}