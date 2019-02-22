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

package io.vasilenko.devstudio.packager.service;

import com.bmc.arsys.studio.model.ModelException;
import com.bmc.arsys.studio.model.StaleObjectException;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;
import com.bmc.arsys.studio.model.store.ARServerStore;

public interface PackingService {
	
    void updatePackingLists(ItemList<IModelItem> items, final IModelItem packingListItem, ARServerStore store) throws ModelException, StaleObjectException;
}
