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

package io.vasilenko.devstudio.packager.handler;

import com.bmc.arsys.studio.commonui.common.EditorInput;
import com.bmc.arsys.studio.commonui.common.info.Info;
import com.bmc.arsys.studio.model.ModelException;
import com.bmc.arsys.studio.model.ModelState;
import com.bmc.arsys.studio.model.item.IModelItem;
import com.bmc.arsys.studio.model.item.ItemList;
import com.bmc.arsys.studio.model.store.IModelObject;
import com.bmc.arsys.studio.ui.editors.form.FormEditor;
import com.bmc.arsys.studio.ui.editors.guide.GuideEditor;
import com.bmc.arsys.studio.ui.editors.images.ImageEditor;
import com.bmc.arsys.studio.ui.editors.menu.MenuEditor;
import com.bmc.arsys.studio.ui.editors.packinglist.PackingListEditor;
import com.bmc.arsys.studio.ui.editors.webservices.WebServicesEditor;
import com.bmc.arsys.studio.ui.editors.workflow.WorkflowEditorBase;
import com.bmc.arsys.studio.ui.views.objectlist.ObjectListView;

import io.vasilenko.devstudio.packager.util.PackingUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PackingHandler extends AbstractHandler {

    private final Map<Class<?>, Runnable> actions = new HashMap<>();

    private IWorkbenchPart workbenchPart;

    public PackingHandler() {
        initHandlerActions();
    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
        IWorkbenchPart activePart = activePage.getActivePart();
        workbenchPart = activePart;
        actions.get(activePart.getClass()).run();
        return null;
    }

    private void initHandlerActions() {
        actions.put(ObjectListView.class, this::handleListObject);
        actions.put(FormEditor.class, this::handleEditorObject);
        actions.put(WorkflowEditorBase.class, this::handleEditorObject);
        actions.put(GuideEditor.class, this::handleEditorObject);
        actions.put(WebServicesEditor.class, this::handleEditorObject);
        actions.put(MenuEditor.class, this::handleEditorObject);
        actions.put(ImageEditor.class, this::handleEditorObject);
        actions.put(PackingListEditor.class, this::handleEditorObject);
    }

    private void handleEditorObject() {
        IEditorPart editorPart = (IEditorPart) workbenchPart;
        IModelItem modelItem = ((EditorInput) editorPart.getEditorInput()).getModelItem();
        IModelObject modelObject = ((EditorInput) editorPart.getEditorInput()).getModelObject();
        if (modelObject.getState() == ModelState.EXISTING) {
            ItemList<IModelItem> selectedItems = new ItemList<>();
            selectedItems.addItem(modelItem);
            PackingUtils.openPackingListDialog(selectedItems);
        } else {
            Info.openError(new ModelException("Object " + modelObject.getName() + " doesn't yet exist"));
        }
    }

    private void handleListObject() {
        ObjectListView listViewPart = (ObjectListView) workbenchPart;
        ItemList<IModelItem> selectedItems = new ItemList<>();
        IStructuredSelection selection = (IStructuredSelection) listViewPart.getActionService().getSelection();
        for (Iterator<?> iterator = selection.iterator(); iterator.hasNext(); ) {
            selectedItems.addItem((IModelItem) iterator.next());
        }
        PackingUtils.openPackingListDialog(selectedItems);
    }
}
