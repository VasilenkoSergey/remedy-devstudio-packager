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
