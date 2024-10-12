package github.kasuminova.ae2ctl.common.integration;

import github.kasuminova.ae2ctl.AE2CTLegacy;
import mezz.jei.Internal;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.bookmarks.BookmarkList;
import mezz.jei.config.Config;
import mezz.jei.input.InputHandler;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;

public class JEIUtils {

    public static Field inputHandler = null;
    public static Field bookmarkList = null;

    static {
        // I Just want to get the BookmarkList...
        try {
            Field inputHandler = Internal.class.getDeclaredField("inputHandler");
            inputHandler.setAccessible(true);
            JEIUtils.inputHandler = inputHandler;

            Field bookmarkList = InputHandler.class.getDeclaredField("bookmarkList");
            bookmarkList.setAccessible(true);
            JEIUtils.bookmarkList = bookmarkList;
        } catch (NoSuchFieldException e) {
            AE2CTLegacy.log.warn(e);
        }
    }

    public static IJeiRuntime getJeiRuntime() {
        return Internal.getRuntime();
    }

    public static void addItemStackToBookmarkList(ItemStack stack) {
        if (inputHandler == null || bookmarkList == null || stack.isEmpty()) {
            return;
        }

        try {
            InputHandler handler = (InputHandler) inputHandler.get(null);
            BookmarkList bookmark = (BookmarkList) bookmarkList.get(handler);

            if (!Config.isBookmarkOverlayEnabled()) {
                Config.toggleBookmarkEnabled();
            }
            bookmark.add(stack);
        } catch (IllegalAccessException e) {
            AE2CTLegacy.log.warn(e);
        }
    }

}
