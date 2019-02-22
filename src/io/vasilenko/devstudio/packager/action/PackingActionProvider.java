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
