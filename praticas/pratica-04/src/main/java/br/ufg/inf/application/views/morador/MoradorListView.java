package br.ufg.inf.application.views.morador;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.ufg.inf.application.models.Morador;
import br.ufg.inf.application.service.MoradorService;
import br.ufg.inf.application.views.MainLayout;

@PageTitle("Morador ")
@Route(value = "morador-list", layout = MainLayout.class)
@Uses(Icon.class)
public class MoradorListView extends VerticalLayout{
	Grid<Morador> grid = new Grid<>(Morador.class); 
    TextField filterText = new TextField();
    MoradorView form;
    MoradorService moradorService;

    public MoradorListView(MoradorService moradorService) {
    	this.moradorService = moradorService;
        addClassName("list-view");
        setSizeFull();
        configureGrid(); 
        configureForm(); 

        add(getToolbar(), getContent()); 
        updateList();
        closeEditor();
        
      
    }

    private void configureGrid() {
        grid.addClassNames("morador-grid");
        grid.setSizeFull();
        grid.setColumns("nome", "cpf", "telefone"); 
       
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        
        grid.asSingleSelect().addValueChangeListener(event ->
        editMorador(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        

        Button addContactButton = new Button("ADICIONA MORADOR ");
        
        addContactButton.addClickListener(click -> addMorador()); 

        HorizontalLayout toolbar = new HorizontalLayout( addContactButton); 
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    
    public void editMorador(Morador morador) { 
        if (morador == null) {
            closeEditor();
        } else {
            form.setMorador(morador);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
    private void addMorador() { 
        grid.asSingleSelect().clear();
        editMorador(new Morador());
    }
    
    private void closeEditor() {
        form.setMorador(null);
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
        form = new MoradorView(); 
     
        form.setWidth("25em");
        form.addListener(MoradorView.SaveEvent.class, this::saveContact); 
        form.addListener(MoradorView.DeleteEvent.class, this::deleteContact); 
        form.addListener(MoradorView.CloseEvent.class, e -> closeEditor()); 
    }
    
    private void saveContact(MoradorView.SaveEvent event) {
    	moradorService.saveMorador(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(MoradorView.DeleteEvent event) {
    	moradorService.deleteMorador(event.getContact());
        updateList();
        closeEditor();
    }
    
    private void updateList() { 
        grid.setItems(moradorService.findAllMoradores());
    }
}
