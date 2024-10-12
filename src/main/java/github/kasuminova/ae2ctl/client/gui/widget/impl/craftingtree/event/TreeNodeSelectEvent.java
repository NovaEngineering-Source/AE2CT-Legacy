package github.kasuminova.ae2ctl.client.gui.widget.impl.craftingtree.event;

import github.kasuminova.ae2ctl.client.gui.widget.event.GuiEvent;
import github.kasuminova.ae2ctl.client.gui.widget.impl.craftingtree.TreeNode;

public class TreeNodeSelectEvent extends GuiEvent {

    private final TreeNode selectedNode;

    public TreeNodeSelectEvent(final TreeNode selectedNode) {
        super(null);
        this.selectedNode = selectedNode;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

}
