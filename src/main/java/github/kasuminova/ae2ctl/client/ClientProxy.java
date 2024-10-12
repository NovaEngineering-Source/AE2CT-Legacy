package github.kasuminova.ae2ctl.client;


import appeng.api.storage.ITerminalHost;
import appeng.container.implementations.ContainerCraftConfirm;
import github.kasuminova.ae2ctl.client.gui.GuiCraftingTree;
import github.kasuminova.ae2ctl.client.handler.ClientTickHandler;
import github.kasuminova.ae2ctl.common.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

public class ClientProxy extends CommonProxy {

    @Override
    public void construction() {
        super.construction();
    }

    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(ClientTickHandler.class);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public void loadComplete() {
        super.loadComplete();
    }

    @Nullable
    @Override
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        GuiType type = GuiType.values()[MathHelper.clamp(ID, 0, GuiType.values().length - 1)];
        return switch (type) {
            case CRAFTING_TREE -> {
                if (!(player.openContainer instanceof ContainerCraftConfirm confirm)) {
                    yield null;
                }
                yield new GuiCraftingTree(player.inventory, (ITerminalHost) confirm.getTarget());
            }
        };
    }

}
