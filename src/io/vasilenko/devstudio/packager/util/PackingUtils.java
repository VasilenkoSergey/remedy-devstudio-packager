/*
 * Copyright 2019 Sergey Vasilenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
