import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import fr.lps2ima.contact.ContactException;
import fr.lps2ima.contact.ContactService;
import fr.lps2ima.contact.dao.IContactDao;
import fr.lps2ima.contact.model.Contact;

public class ContactServiceMockTest extends EasyMockSupport{
	
	@Rule
	public EasyMockRule rule= new EasyMockRule(this);
	
	@TestSubject
	private ContactService service = new ContactService();
	
	@Mock
	private IContactDao contactDao;
	
	@Test(expected=ContactException.class)
	public void testAddContact() throws ContactException{
		//1 Enregistrement des comportements de mock
		Contact contact = new Contact();
		EasyMock.expect(contactDao.findByName("valeurQuOnVeut")).andReturn(contact);
		//2 fin d'enregistrement
		replayAll();
		//3 test
		service.add("valeurQuOnVeut");
	}
	
	@Test
	public void testDelContact() throws ContactException{
		//1 Enregistrement des comportements de mock
		Contact contact = new Contact();
		EasyMock.expect(contactDao.findByName("valeurQuOnVeut")).andReturn(contact);
		contactDao.remove("valeurQuOnVeut");
		//2 fin d'enregistrement
		replayAll();
		//3 test
		service.del("valeurQuOnVeut");
		
		// VerifyStrict
		verifyAll();
	}

	
	@Test(expected=ContactException.class)
	public void testDelContactInexist() throws ContactException{
		//1 Enregistrement des comportements de mock
		EasyMock.expect(contactDao.findByName("valeurQuOnVeut")).andReturn(null);
		//2 fin d'enregistrement
		replayAll();
		//3 test
		service.del("valeurQuOnVeut");
	}
}
