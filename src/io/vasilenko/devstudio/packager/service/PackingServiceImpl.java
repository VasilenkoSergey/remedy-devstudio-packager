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
import com.bmc.arsys.studio.model.ar.ARPackingList;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;
import com.bmc.arsys.studio.model.store.ARServerStore;
import com.bmc.arsys.studio.model.type.IARSystemTypes;

import static io.vasilenko.devstudio.packager.constants.PackingConstants.*;

import java.util.HashSet;
import java.util.Set;

public class PackingServiceImpl implements PackingService {

    @Override
    public void updatePackingLists(final ItemList<IModelItem> items, final IModelItem packingListItem, final ARServerStore store) throws ModelException, StaleObjectException {
        ARPackingList arPackingList = (ARPackingList) store.getContainer(packingListItem.getName());
        arPackingList.setStore(store);

        Set<String> itemSet = new HashSet<>();
        for (IModelItem item : items) {
            itemSet.add(item.getName());
        }
        
        setContent(arPackingList, itemSet, items.getFirstItem().getItemType().getTypeName());
        store.storeObject(IARSystemTypes.PACKING_LIST, arPackingList, true, false);
    }

    private void setContent(final ARPackingList arPackingList, final Set<String> itemSet, final String itemType) throws ModelException {
        switch (itemType) {
            case FORM_TYPE:
            	Set<String> forms = arPackingList.getForms();
            	forms.addAll(itemSet);
                arPackingList.setForms(forms);
                break;
            case ACTIVELINK_TYPE:
            	Set<String> activeLinks = arPackingList.getActiveLinks();
            	activeLinks.addAll(itemSet);
                arPackingList.setActiveLinks(activeLinks);
                break;
            case ACTIVELINK_GUIDE_TYPE:
            	Set<String> activeLinkGuides = arPackingList.getActiveLinkGuides();
            	activeLinkGuides.addAll(itemSet);
                arPackingList.setActiveLinkGuides(activeLinkGuides);
                break;
            case FILTER_TYPE:
            	Set<String> filters = arPackingList.getFilters();
            	filters.addAll(itemSet);
                arPackingList.setFilters(filters);
                break;
            case FILTER_GUIDE_TYPE:
            	Set<String> filterGuides = arPackingList.getFilterGuides();
            	filterGuides.addAll(itemSet);
                arPackingList.setFilterGuides(filterGuides);
                break;
            case ESCALATION_TYPE:
            	Set<String> escalations = arPackingList.getEscalations();
            	escalations.addAll(itemSet);
                arPackingList.setEscalations(escalations);
                break;
            case MENU_TYPE:
            	Set<String> menus = arPackingList.getMenus();
            	menus.addAll(itemSet);
                arPackingList.setMenus(menus);
                break;
            case WEBSERVICE_TYPE:
            	Set<String> webservices = arPackingList.getWebServices();
            	webservices.addAll(itemSet);
                arPackingList.setWebServices(webservices);
                break;
            case IMAGE_TYPE:
            	Set<String> images = arPackingList.getImages();
            	images.addAll(itemSet);
                arPackingList.setImages(images);
                break;
            case PACKING_LIST_TYPE:
            	Set<String> packingLists = arPackingList.getPackingLists();
            	packingLists.addAll(itemSet);
                arPackingList.setPackingLists(packingLists);
                break;
            default:
                throw new ModelException("Type " + itemType + " not supported");
        }
    }
}
