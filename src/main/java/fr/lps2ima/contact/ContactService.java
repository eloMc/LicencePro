package fr.lps2ima.contact;

import fr.lps2ima.contact.dao.ContactDao;
import fr.lps2ima.contact.dao.IContactDao;
import fr.lps2ima.contact.model.Contact;

public class ContactService  {
	
	IContactDao dao=new ContactDao();
	
	
	public void add(String name) throws ContactException{
		if(estValide(name)){
			Contact contact = new Contact();
			contact.setName(name);
			dao.add(contact);
			System.out.println("Le contact a été ajouté");
		}
		else{
			throw new ContactException("Impossible de créer le contact");
		}
		
	}
	
	public boolean estValide(String name)throws ContactException{
		boolean val= true;
		if(name==null ||name.length()<3 || name.length()>40){
			val=false;	
			throw new ContactException("Impossible de créer le contact, nom invalide");
			
		}
		
		if(val==true && dao.findByName(name) != null ){
			val=false;
			throw new ContactException("Impossible de créer le contact, nom déjà connu");
			
		}
		return val;
		
	}
	
	public void del(String name) throws ContactException{
		if(dao.findByName(name) != null){           
			dao.remove(name);
			System.out.println("Le contact a été supprimé");
		}
		else{
			throw new ContactException("Ce contact n'existe pas.");
		}
		
	}

}
