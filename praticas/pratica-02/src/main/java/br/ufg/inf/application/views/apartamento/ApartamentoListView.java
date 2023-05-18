package br.ufg.inf.application.views.apartamento;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.ufg.inf.application.data.entity.Apartamento;
import br.ufg.inf.application.data.entity.Morador;
import br.ufg.inf.application.data.service.ApartamentoService;
import br.ufg.inf.application.data.service.MoradorService;
import br.ufg.inf.application.views.MainLayout;
import br.ufg.inf.application.views.morador.MoradorView;

@PageTitle("Apartamento ")
@Route(value = "apartamento-list", layout = MainLayout.class)
@Uses(Icon.class)
public class ApartamentoListView extends VerticalLayout{
	Grid<Apartamento> grid = new Grid<>(Apartamento.class); 
    TextField filterText = new TextField();
    ApartamentoView form;
    ApartamentoService apartamentoService;
    
	 public ApartamentoListView(ApartamentoService apartamentoService) {
	    	this.apartamentoService = apartamentoService;
	        addClassName("list-view-ap");
	        setSizeFull();
	        configureGrid(); 
	        configureForm(); 

	        add(getToolbar(), getContent()); 
	        updateList();
	        closeEditor();
	        
	      
	    }

	    private void configureGrid() {
	        grid.addClassNames("apartamento-grid");
	        grid.setSizeFull();
	        grid.setColumns("numero", "andar", "metragem","situacao"); 
	        grid.addColumn(contact -> contact.getMorador().getNome()).setHeader("morador"); 
	       
	        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
	        
	        grid.asSingleSelect().addValueChangeListener(event ->
	        editApartamento(event.getValue()));
	    }

	    private HorizontalLayout getToolbar() {
	        

	        Button addContactButton = new Button("ADICIONA APARTAMENTO ");
	        
	        addContactButton.addClickListener(click -> addApartamento()); 

	        HorizontalLayout toolbar = new HorizontalLayout( addContactButton); 
	        toolbar.addClassName("toolbar");
	        return toolbar;
	    }
	    
	    public void editApartamento(Apartamento apartamento) { 
	        if (apartamento == null) {
	            closeEditor();
	        } else {
	            form.setApartamento(apartamento);
	            form.setVisible(true);
	            addClassName("editing");
	        }
	    }
	    
	    private void addApartamento() { 
	        grid.asSingleSelect().clear();
	        editApartamento(new Apartamento());
	    }
	    
	    private void closeEditor() {
	        form.setApartamento(null);
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
	        form = new ApartamentoView(apartamentoService.findAllMoradores()); 
	     
	        form.setWidth("25em");
	        form.addListener(ApartamentoView.SaveEvent.class, this::saveContact); 
	        form.addListener(ApartamentoView.DeleteEvent.class, this::deleteContact); 
	        form.addListener(ApartamentoView.CloseEvent.class, e -> closeEditor()); 
	    }
	    
	    private void saveContact(ApartamentoView.SaveEvent event) {
	    	apartamentoService.saveApartamento(event.getContact());
	        updateList();
	        closeEditor();
	    }

	    private void deleteContact(ApartamentoView.DeleteEvent event) {
	    	apartamentoService.deletApartamento(event.getContact());
	        updateList();
	        closeEditor();
	    }
	    
	    private void updateList() { 
	        grid.setItems(apartamentoService.findAllApartamentos());
	        
	    }
	    
}
