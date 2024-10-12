package github.kasuminova.ae2ctl;

import github.kasuminova.ae2ctl.common.CommonProxy;
import github.kasuminova.ae2ctl.common.network.PktCraftingTreeData;
import github.kasuminova.ae2ctl.common.network.PktSwitchCraftingTree;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = AE2CTLegacy.MOD_ID, name = AE2CTLegacy.MOD_NAME, version = AE2CTLegacy.VERSION,
        dependencies = "required-after:forge@[14.23.5.0,);" +
                       "required-after:appliedenergistics2;" +
                       "required-after:jei;" +
                       "required-after:mixinbooter@[8.0,);",
        acceptedMinecraftVersions = "[1.12, 1.13)",
        acceptableRemoteVersions = "[0.1.0, 0.2.0)"
)
@SuppressWarnings("MethodMayBeStatic")
public class AE2CTLegacy {
    public static final String MOD_ID = "ae2ctl";
    public static final String MOD_NAME = "AE2 Crafting Tree - Legacy";

    public static final String VERSION = Tags.VERSION;

    public static final String CLIENT_PROXY = "github.kasuminova.ae2ctl.client.ClientProxy";
    public static final String COMMON_PROXY = "github.kasuminova.ae2ctl.common.CommonProxy";

    public static final SimpleNetworkWrapper NET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    @Mod.Instance(MOD_ID)
    public static AE2CTLegacy instance = null;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy = null;

    public static Logger log = null;

    public AE2CTLegacy() {
    }

    @Mod.EventHandler
    public void construction(FMLConstructionEvent event) {
        proxy.construction();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = VERSION;
        log = event.getModLog();

        NET_CHANNEL.registerMessage(PktCraftingTreeData.class, PktCraftingTreeData.class, 0, Side.CLIENT);
        NET_CHANNEL.registerMessage(PktSwitchCraftingTree.class, PktSwitchCraftingTree.class, 1, Side.SERVER);

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete();
    }
}
