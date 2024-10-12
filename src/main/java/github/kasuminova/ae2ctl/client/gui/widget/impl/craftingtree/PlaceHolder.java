package github.kasuminova.ae2ctl.client.gui.widget.impl.craftingtree;

import github.kasuminova.ae2ctl.client.gui.util.MousePos;
import github.kasuminova.ae2ctl.client.gui.util.RenderPos;
import github.kasuminova.ae2ctl.client.gui.util.RenderSize;
import github.kasuminova.ae2ctl.client.gui.widget.base.DynamicWidget;
import github.kasuminova.ae2ctl.client.gui.widget.base.WidgetGui;

public class PlaceHolder extends DynamicWidget {

    public PlaceHolder() {
        setVisible(false);
    }

    @Override
    public void render(final WidgetGui gui, final RenderSize renderSize, final RenderPos renderPos, final MousePos mousePos) {
        // Do nothing
    }

}
