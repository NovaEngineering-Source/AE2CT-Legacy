package github.kasuminova.ae2ctl.mixin;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class AE2CTLLateMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.ae2ctl.json");
    }

}
