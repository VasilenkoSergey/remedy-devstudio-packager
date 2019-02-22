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

package io.vasilenko.devstudio.packager.view;

import com.bmc.arsys.studio.commonui.common.info.Info;
import com.bmc.arsys.studio.commonui.common.selector.container.ContainerSelectorConfiguration;
import com.bmc.arsys.studio.commonui.common.selector.container.ContainerSelectorDlg;
import com.bmc.arsys.studio.model.ModelException;
import com.bmc.arsys.studio.model.StaleObjectException;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;
import com.bmc.arsys.studio.model.store.ARServerStore;

import io.vasilenko.devstudio.packager.service.PackingService;
import io.vasilenko.devstudio.packager.service.PackingServiceImpl;

import org.eclipse.swt.widgets.Shell;

public class PackingListDialog extends ContainerSelectorDlg {

    private static final int SELECTED_PACKING_LIST = 0;

    private ItemList<IModelItem> modelItems;

    public PackingListDialog(final Shell shell, final ContainerSelectorConfiguration configuration) {
        super(shell, configuration);
        configuration.setEnableMultipleSelection(false);
        configuration.setShowApplicationSelection(false);
    }

    @Override
    protected void okPressed() {
        PackingService packingService = new PackingServiceImpl();
        try {
            packingService.updatePackingLists(modelItems, getSelectedItems().get(SELECTED_PACKING_LIST), 
            		(ARServerStore) getConfiguration().getStore());
            super.okPressed();
        } catch (ModelException | StaleObjectException e) {
            Info.openError(e);
        }
    }

    public void setModelItems(final ItemList<IModelItem> modelItems) {
        this.modelItems = modelItems;
    }
}
