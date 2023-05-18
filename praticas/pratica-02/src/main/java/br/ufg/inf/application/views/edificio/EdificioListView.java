package br.ufg.inf.application.views.edificio;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import br.ufg.inf.application.data.entity.Edificio;
import br.ufg.inf.application.data.service.EdificioService;
import br.ufg.inf.application.views.MainLayout;


@PageTitle("Edificio ")
@Route(value = "edificio-list", layout = MainLayout.class)
@Uses(Icon.class)
public class EdificioListView extends VerticalLayout{
	Grid<Edificio> grid = new Grid<>(Edificio.class); 
    TextField filterText = new TextField();
    EdificioForm form;
    EdificioService edificioService;

    public EdificioListView(EdificioService edificioService) {
    	this.edificioService = edificioService;
        addClassName("list-view");
        setSizeFull();
        configureGrid(); 
        configureForm(); 

        add(getToolbar(), getContent()); 
        updateList();
        closeEditor();
        
      
    }

    private void configureGrid() {
        grid.addClassNames("edificio-grid");
        grid.setSizeFull();
        grid.setColumns("nome", "endereco"); 
       
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        
        grid.asSingleSelect().addValueChangeListener(event ->
        editEdificio(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        

        Button addContactButton = new Button("ADICIONA EDIFICIO");
        
        addContactButton.addClickListener(click -> addEdificio()); 

        HorizontalLayout toolbar = new HorizontalLayout( addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editEdificio(Edificio edificio) { 
        if (edificio == null) {
            closeEditor();
        } else {
            form.setEdificio(edificio);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
    private void addEdificio() { 
        grid.asSingleSelect().clear();
        editEdificio(new Edificio());
    }
    
    private void closeEditor() {
        form.setEdificio(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid); 
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new EdificioForm(); 
     
        form.setWidth("25em");
        form.addListener(EdificioForm.SaveEvent.class, this::saveEdificio); 
        form.addListener(EdificioForm.DeleteEvent.class, this::deleteEdificio); 
        form.addListener(EdificioForm.CloseEvent.class, e -> closeEditor()); 
    }
    
    private void saveEdificio(EdificioForm.SaveEvent event) {
    	edificioService.saveEdificio(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteEdificio(EdificioForm.DeleteEvent event) {
    	edificioService.deleteEdificio(event.getContact());
        updateList();
        closeEditor();
    }
    
    private void updateList() { 
        grid.setItems(edificioService.findAllEdificios());
    }

}
