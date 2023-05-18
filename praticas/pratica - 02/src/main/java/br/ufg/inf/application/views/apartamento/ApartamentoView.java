package br.ufg.inf.application.views.apartamento;

import br.ufg.inf.application.data.entity.Apartamento;
import br.ufg.inf.application.data.entity.Morador;
import br.ufg.inf.application.data.entity.SamplePerson;
import br.ufg.inf.application.data.service.SamplePersonService;
import br.ufg.inf.application.views.MainLayout;


import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;


public class ApartamentoView extends FormLayout {

	TextField numero = new TextField("Numero"); 
	 TextField andar = new TextField("Andar");
	 TextField metragem = new TextField("Metragem");
	 TextField situacao = new TextField("Situacao");
	 
	 ComboBox<Morador> morador = new ComboBox<>("Morador");

    Button close = new Button("Cancel");
    Button delete = new Button("Delete");
    Button save = new Button("Save");
    private Apartamento apartamento;

   private Binder<Apartamento> binder = new Binder<>(Apartamento.class);

   public ApartamentoView(List<Morador> moradores) {
    
       addClassName("morador-form"); 
       binder.bindInstanceFields(this); 
       
       morador.setItems(moradores);
       morador.setItemLabelGenerator(Morador::getNome);
      
       add(numero, 
    		   andar,
    		   metragem,
    		   situacao,
       	morador,
           createButtonsLayout());
   }


   
   public void setApartamento(Apartamento apartamento) {
       this.apartamento = apartamento; 
       binder.readBean(apartamento); 
   }
   
   
   public static abstract class ApartamentoFormEvent extends ComponentEvent<ApartamentoView> {
   	  private Apartamento apartamento;

   	  protected ApartamentoFormEvent(ApartamentoView source, Apartamento apartamento) { 
   	    super(source, false);
   	    this.apartamento = apartamento;
   	  }

   	  public Apartamento getContact() {
   	    return apartamento;
   	  }
   	}

   	public static class SaveEvent extends ApartamentoFormEvent {
   	  SaveEvent(ApartamentoView source, Apartamento apartamento) {
   	    super(source, apartamento);
   	  }
   	}

   	public static class DeleteEvent extends ApartamentoFormEvent {
   	  DeleteEvent(ApartamentoView source, Apartamento apartamento) {
   	    super(source, apartamento);
   	  }

   	}
   	
   	public static class CloseEvent extends ApartamentoFormEvent {
   		  CloseEvent(ApartamentoView source) {
   		    super(source, null);
   		  }
   		}
   	
   	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
   		    ComponentEventListener<T> listener) { 
   		  return getEventBus().addListener(eventType, listener);
   		}
   	
   	private Component createButtonsLayout() {
   		  save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
   		  delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
   		  close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

   		  save.addClickShortcut(Key.ENTER);
   		  close.addClickShortcut(Key.ESCAPE);

   		  save.addClickListener(event -> validateAndSave()); 
   		  delete.addClickListener(event -> fireEvent(new DeleteEvent(this, apartamento))); 
   		  close.addClickListener(event -> fireEvent(new CloseEvent(this))); 

   		  binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); 
   		  return new HorizontalLayout(save, delete, close);
   		}

   		private void validateAndSave() {
   			
   		  try {
   		    binder.writeBean(apartamento); 
   		    fireEvent(new SaveEvent(this, apartamento)); 
   		    
   		   
   		  } catch (ValidationException e) {
   		    e.printStackTrace();
   		  }
   		}
}
