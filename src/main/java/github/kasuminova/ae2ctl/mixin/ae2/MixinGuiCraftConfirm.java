package github.kasuminova.ae2ctl.mixin.ae2;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiCraftConfirm;
import github.kasuminova.ae2ctl.AE2CTLegacy;
import github.kasuminova.ae2ctl.client.gui.widget.vanilla.GuiButtonImageExt;
import github.kasuminova.ae2ctl.common.network.PktSwitchCraftingTree;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiCraftConfirm.class)
public abstract class MixinGuiCraftConfirm extends AEBaseGui {

    @Unique
    private GuiButtonImageExt ae2ctl$craftTree;

    public MixinGuiCraftConfirm() {
        super(null);
    }

    @Inject(method = "initGui", at = @At("RETURN"))
    private void injectInitGui(final CallbackInfo ci) {
        ae2ctl$craftTree = new GuiButtonImageExt(-1,
                (this.guiLeft + this.xSize) - 26, this.guiTop - 4, 26, 19,
                0, 0, 19,
                new ResourceLocation(AE2CTLegacy.MOD_ID, "textures/gui/guicraftingtree_light.png"));
        ae2ctl$craftTree.setMessage(I18n.format("gui.crafting_tree.switch"));
        this.buttonList.add(this.ae2ctl$craftTree);
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
    private void injectActionPerformed(final GuiButton btn, final CallbackInfo ci) {
        if (btn == ae2ctl$craftTree) {
            AE2CTLegacy.NET_CHANNEL.sendToServer(new PktSwitchCraftingTree());
            ci.cancel();
        }
    }

}
