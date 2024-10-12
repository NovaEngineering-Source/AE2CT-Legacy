package github.kasuminova.ae2ctl.client.gui.widget.impl.craftingtree.event;

import github.kasuminova.ae2ctl.client.gui.widget.event.GuiEvent;
import github.kasuminova.ae2ctl.common.integration.ae2.data.LiteCraftTreeNode;

public class CraftingTreeDataUpdateEvent extends GuiEvent {

    private final LiteCraftTreeNode root;

    public CraftingTreeDataUpdateEvent(final LiteCraftTreeNode root) {
        super(null);
        this.root = root;
    }

    public LiteCraftTreeNode getRoot() {
        return root;
    }

}
