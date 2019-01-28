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
