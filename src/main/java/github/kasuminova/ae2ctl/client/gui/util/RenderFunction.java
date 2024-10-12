package github.kasuminova.ae2ctl.client.gui.util;

import github.kasuminova.ae2ctl.client.gui.widget.base.DynamicWidget;
import github.kasuminova.ae2ctl.client.gui.widget.base.WidgetGui;

@FunctionalInterface
public interface RenderFunction {

    void doRender(DynamicWidget dynamicWidget, WidgetGui gui, RenderSize renderSize, RenderPos renderPos, MousePos mousePos);

}
