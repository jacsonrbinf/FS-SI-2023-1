package br.ufg.inf.application.data.service;

import br.ufg.inf.application.data.entity.SamplePerson;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SamplePersonService {

    private  SamplePersonRepository repository;

    public SamplePersonService(SamplePersonRepository repository) { 
    	this.repository =repository;
    }

	public List<SamplePerson> findAllContacts() {
		return repository.findAll();
	}

	public long countContacts() {
		return repository.count();
	}

	public void deleteContact(SamplePerson person) {
		repository.delete(person);
	}

	public void saveContact(SamplePerson person) {
		if (person == null) { 
		  System.err.println("Contact is null. Are you sure you have connected your form to the application?");
		  return;
		}
		repository.save(person);
	}

}
