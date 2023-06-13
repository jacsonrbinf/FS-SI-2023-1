package br.ufg.inf.application.views.edificio;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import br.ufg.inf.application.models.Apartamento;
import br.ufg.inf.application.models.Edificio;
import br.ufg.inf.application.models.Morador;

public class EdificioForm extends FormLayout {
	TextField nome = new TextField("nome");
	TextField endereco = new TextField("endereco");

	Button close = new Button("Cancel");
	Button delete = new Button("Delete");
	Button save = new Button("Save");
	private Edificio edificio;

	private Binder<Edificio> binder = new Binder<>(Edificio.class);
	ComboBox<Apartamento> apartamentos = new ComboBox<>("Apartamento");

	public EdificioForm(List<Apartamento> aptos) {

		addClassName("edificio-form");
		binder.bindInstanceFields(this);
		
		apartamentos.setItems(aptos);
		apartamentos.setItemLabelGenerator(Apartamento::getSituacao);

		add(nome, endereco,apartamentos, createButtonsLayout());
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
		binder.readBean(edificio);
	}

	public static abstract class EdificioFormEvent extends ComponentEvent<EdificioForm> {
		private Edificio edificio;

		protected EdificioFormEvent(EdificioForm source, Edificio edificio) {
			super(source, false);
			this.edificio = edificio;
		}

		public Edificio getContact() {
			return edificio;
		}
	}

	public static class SaveEvent extends EdificioFormEvent {
		SaveEvent(EdificioForm source, Edificio edificio) {
			super(source, edificio);
		}
	}

	public static class DeleteEvent extends EdificioFormEvent {
		DeleteEvent(EdificioForm source, Edificio edificio) {
			super(source, edificio);
		}

	}

	public static class CloseEvent extends EdificioFormEvent {
		CloseEvent(EdificioForm source) {
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
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, edificio)));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {

		try {
			binder.writeBean(edificio);
			fireEvent(new SaveEvent(this, edificio));

		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

}
