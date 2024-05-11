package com.axialeaa.blockybubbles;

import com.axialeaa.blockybubbles.sodium.SodiumConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
/*? if =1.18.2 { *//*
import net.minecraft.text.TranslatableText;
*//*? } elif >1.17.1 { */
import net.minecraft.text.Text;
/*? } */
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockyBubbles implements ClientModInitializer {

    public static final String MOD_ID = "blocky-bubbles";
    public static final String MOD_NAME = "Blocky Bubbles";
    public static final String PACK_NAME_KEY = "resourcePack.blocky-bubbles:32x_upscale.name";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static boolean isSodiumLoaded = FabricLoader.getInstance().isModLoaded("sodium");
    private static SodiumConfig CONFIG;

    /**
     * @return the default values of a new config file in the fabric mod directory if one doesn't exist, otherwise the configured settings.
     */
    public static SodiumConfig options() {
        if (CONFIG == null) {
            FabricLoader fabricLoader = FabricLoader.getInstance();
            CONFIG = SodiumConfig.loadFromFile(fabricLoader.getConfigDir().resolve(MOD_ID + ".json").toFile());
        }

        return CONFIG;
    }

    @Override
    public void onInitializeClient() {
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer ->
            ResourceManagerHelper.registerBuiltinResourcePack(id("32x_upscale"), modContainer,
            /*? if >=1.19.3 { */
            Text.translatable(PACK_NAME_KEY),
            /*? } elif =1.19.2 { *//*
            String.valueOf(Text.translatable(packNameKey)),
            *//*? } elif =1.18.2 { *//*
            String.valueOf(new TranslatableText(packNameKey)),
            *//*? } */

            // Note: custom pack names are only supported in versions above 1.17.1.
            ResourcePackActivationType.NORMAL)
        );
        LOGGER.info(MOD_NAME + " initialized. Bubble trouble!");

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.BUBBLE_COLUMN, RenderLayer.getCutout());
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

}
