package github.kasuminova.ae2ctl.common.mod;

import net.minecraftforge.fml.common.Loader;

public enum Mods {

    JEI("jei"),
    AE2("appliedenergistics2"),

    ;
    final String modID;
    final String requiredClass;
    boolean loaded = false;
    boolean initialized = false;

    Mods(final String modID) {
        this.modID = modID;
        this.requiredClass = null;
    }

    Mods(final String modID, final String requiredClass) {
        this.modID = modID;
        this.requiredClass = requiredClass;
    }

    public boolean loaded() {
        if (initialized) {
            return loaded;
        }

        initialized = true;

        if (requiredClass != null) {
            try {
                Class.forName(requiredClass);
                return loaded = true;
            } catch (Throwable e) {
                return loaded = false;
            }
        }
        return loaded = Loader.isModLoaded(modID);
    }

}
