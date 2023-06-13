package br.ufg.inf.application.views.morador;

import br.ufg.inf.application.models.Morador;
import br.ufg.inf.application.views.MainLayout;
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


public class MoradorView extends FormLayout  {

	 TextField nome = new TextField("Nome"); 
	 TextField cpf = new TextField("CPF");
	 TextField telefone = new TextField("Telefone");

     Button close = new Button("Cancel");
     Button delete = new Button("Delete");
     Button save = new Button("Save");
     private Morador morador;

    private Binder<Morador> binder = new Binder<>(Morador.class);

    public MoradorView() {
     
        addClassName("morador-form"); 
        binder.bindInstanceFields(this); 
       
        add(nome, 
        	cpf,
        	telefone,
            createButtonsLayout());
    }


    
    public void setMorador(Morador morador) {
        this.morador = morador; 
        binder.readBean(morador); 
    }
    
    
    public static abstract class ContactFormEvent extends ComponentEvent<MoradorView> {
    	  private Morador morador;

    	  protected ContactFormEvent(MoradorView source, Morador morador) { 
    	    super(source, false);
    	    this.morador = morador;
    	  }

    	  public Morador getContact() {
    	    return morador;
    	  }
    	}

    	public static class SaveEvent extends ContactFormEvent {
    	  SaveEvent(MoradorView source, Morador morador) {
    	    super(source, morador);
    	  }
    	}

    	public static class DeleteEvent extends ContactFormEvent {
    	  DeleteEvent(MoradorView source, Morador morador) {
    	    super(source, morador);
    	  }

    	}
    	
    	public static class CloseEvent extends ContactFormEvent {
    		  CloseEvent(MoradorView source) {
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
    		  delete.addClickListener(event -> fireEvent(new DeleteEvent(this, morador))); 
    		  close.addClickListener(event -> fireEvent(new CloseEvent(this))); 

    		  binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); 
    		  return new HorizontalLayout(save, delete, close);
    		}

    		private void validateAndSave() {
    			
    		  try {
    		    binder.writeBean(morador); 
    		    fireEvent(new SaveEvent(this, morador)); 
    		    
    		   
    		  } catch (ValidationException e) {
    		    e.printStackTrace();
    		  }
    		}
}
