package io.vasilenko.devstudio.packager.action;

import com.bmc.arsys.studio.ui.views.objectlist.actions.BaseObjectListAction;

import io.vasilenko.devstudio.packager.util.PackingUtils;

public class PackingAction extends BaseObjectListAction {

    private static final String PACKING_ACTION_NAME = "Add To Packing List";

    PackingAction() {
        super();
        setText(PACKING_ACTION_NAME);
    }

    @Override
    public void run() {
        super.run();
        PackingUtils.openPackingListDialog(getItems());
    }
}
