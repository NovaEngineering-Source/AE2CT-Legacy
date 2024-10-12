package github.kasuminova.ae2ctl.common.network;

import appeng.crafting.CraftingTreeNode;
import github.kasuminova.ae2ctl.client.gui.GuiCraftingTree;
import github.kasuminova.ae2ctl.common.integration.ae2.data.LiteCraftTreeNode;
import github.kasuminova.ae2ctl.common.util.AEItemStackSet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PktCraftingTreeData implements IMessage, IMessageHandler<PktCraftingTreeData, IMessage> {

    private LiteCraftTreeNode root = null;

    public PktCraftingTreeData() {
    }

    public PktCraftingTreeData(final CraftingTreeNode root) {
        this.root = LiteCraftTreeNode.of(root, null);
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        AEItemStackSet stackSet = new AEItemStackSet();

        // Read
        stackSet.fromBuffer(buf);
        root = LiteCraftTreeNode.fromBuffer(buf, stackSet, null);
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        AEItemStackSet stackSet = new AEItemStackSet();

        // PreWrite
        ByteBuf buffer = Unpooled.buffer();
        root.writeToBuffer(buffer, stackSet);

        // Write
        stackSet.writeToBuffer(buf);
        buf.writeBytes(buffer);
    }

    @Override
    public IMessage onMessage(final PktCraftingTreeData message, final MessageContext ctx) {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message));
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    protected static void processPacket(final PktCraftingTreeData message) {
        LiteCraftTreeNode root = message.root;
        GuiScreen cur = Minecraft.getMinecraft().currentScreen;
        if (!(cur instanceof GuiCraftingTree treeGUI)) {
            return;
        }

        treeGUI.onDataUpdate(root);
    }

}
