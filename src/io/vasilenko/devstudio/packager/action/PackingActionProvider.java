package io.vasilenko.devstudio.packager.action;

import com.bmc.arsys.studio.ui.views.objectlist.actions.BaseObjectListAction;
import com.bmc.arsys.studio.ui.views.objectlist.actions.ObjectListActionProvider;
import com.bmc.arsys.studio.ui.views.objectlist.actions.ObjectListActionService;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IViewSite;

import java.util.ArrayList;
import java.util.List;

public class PackingActionProvider extends ObjectListActionProvider {

    private PackingAction packingAction;

    @Override
    public List<BaseObjectListAction> getHookableActions(String arg) {
        return new ArrayList<>();
    }

    @Override
    public void init(IViewSite site) {
        super.init(site);
        packingAction = new PackingAction();
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        if (getSelection() != null) {
            packingAction.setItems(getItems());
            packingAction.setEnabled(packingAction.isEnabled());
            menu.appendToGroup(ObjectListActionService.GROUP_ADDITIONS, packingAction);
        }
    }
}
