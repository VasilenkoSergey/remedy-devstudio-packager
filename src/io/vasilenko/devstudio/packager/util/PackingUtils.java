package io.vasilenko.devstudio.packager.util;

import com.bmc.arsys.studio.commonui.common.selector.container.ContainerSelectorConfiguration;
import com.bmc.arsys.studio.commonui.common.selector.container.SelectorContainerType;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;

import io.vasilenko.devstudio.packager.view.PackingListDialog;

import org.eclipse.swt.widgets.Display;

public final class PackingUtils {

    private PackingUtils() {
    }

    public static void openPackingListDialog(final ItemList<IModelItem> selectedItems) {
        ContainerSelectorConfiguration selectorConfig =
                new ContainerSelectorConfiguration(SelectorContainerType.PACKING_LISTS, selectedItems.getFirstItem().getStore());

        PackingListDialog packingListDialog = new PackingListDialog(Display.getDefault().getActiveShell(), selectorConfig);
        packingListDialog.setModelItems(selectedItems);
        packingListDialog.open();
    }
}
