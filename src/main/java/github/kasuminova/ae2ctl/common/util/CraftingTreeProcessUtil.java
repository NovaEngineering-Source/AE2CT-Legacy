package github.kasuminova.ae2ctl.common.util;

import appeng.crafting.CraftingTreeNode;
import appeng.crafting.CraftingTreeProcess;
import github.kasuminova.ae2ctl.AE2CTLegacy;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Map;

public class CraftingTreeProcessUtil {

    private static final MethodHandle nodesHandle;

    static {
        try {
            Field nodes = CraftingTreeProcess.class.getDeclaredField("nodes");
            nodes.setAccessible(true);
            nodesHandle = MethodHandles.lookup().unreflectGetter(nodes);
        } catch (Throwable e) {
            AE2CTLegacy.log.fatal("[AE2CTL-FATAL] Failed to initialize CraftingTreeProcessUtil!", e);
            throw new RuntimeException(e);
        }
    }

    public static Map<CraftingTreeNode, Long> getNodes(final CraftingTreeProcess process) {
        try {
            return (Map<CraftingTreeNode, Long>) nodesHandle.invoke(process);
        } catch (Throwable e) {
            AE2CTLegacy.log.fatal("[AE2CTL-FATAL] Failed to get nodes from CraftingTreeProcess!", e);
            throw new RuntimeException(e);
        }
    }

}
