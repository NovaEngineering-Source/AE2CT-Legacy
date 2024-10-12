package github.kasuminova.ae2ctl.common;

import appeng.api.storage.ITerminalHost;
import appeng.container.implementations.ContainerCraftConfirm;
import github.kasuminova.ae2ctl.AE2CTLegacy;
import github.kasuminova.ae2ctl.client.ClientProxy;
import github.kasuminova.ae2ctl.common.container.ContainerCraftingTree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.Nullable;

public class CommonProxy implements IGuiHandler {

    public CommonProxy() {
    }

    public void construction() {

    }

    public void preInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(AE2CTLegacy.MOD_ID, this);
    }

    public void init() {

    }

    public void postInit() {

    }

    public void loadComplete() {

    }

    @Nullable
    @Override
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        ClientProxy.GuiType type = ClientProxy.GuiType.values()[MathHelper.clamp(ID, 0, ClientProxy.GuiType.values().length - 1)];
        return switch (type) {
            case CRAFTING_TREE -> {
                if (!(player.openContainer instanceof ContainerCraftConfirm confirm)) {
                    yield null;
                }
                yield new ContainerCraftingTree(player.inventory, (ITerminalHost) confirm.getTarget());
            }
        };
    }

    @Nullable
    @Override
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return null;
    }

    public enum GuiType {

        CRAFTING_TREE(),

    }

}
