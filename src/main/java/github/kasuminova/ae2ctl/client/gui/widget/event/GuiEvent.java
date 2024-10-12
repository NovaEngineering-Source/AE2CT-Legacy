package github.kasuminova.ae2ctl.client.gui.widget.event;

import github.kasuminova.ae2ctl.client.gui.widget.base.WidgetGui;

public abstract class GuiEvent {
    protected final WidgetGui gui;

    public GuiEvent(final WidgetGui gui) {
        this.gui = gui;
    }

    public WidgetGui getGui() {
        return gui;
    }
}
