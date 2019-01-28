package io.vasilenko.devstudio.packager.service;

import com.bmc.arsys.studio.model.ModelException;
import com.bmc.arsys.studio.model.StaleObjectException;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;
import com.bmc.arsys.studio.model.store.ARServerStore;

public interface PackingService {
	
    void updatePackingLists(ItemList<IModelItem> items, final IModelItem packingListItem, ARServerStore store) throws ModelException, StaleObjectException;
}
