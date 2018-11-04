package storage_servers;

import java.io.IOException;

import models.Person;
import models.Response;
import models.Status;

public class StorageService {
	private FileHelper fileHelper;
	public StorageService() {
		fileHelper = new FileHelper();
	}
	
	public Response create(Person person) {
		return new Response(fileHelper.create(person), Status.VALID);
	}

	public Response read(long id) {
		Response response = new Response(null, null);
		try {
			Person person = fileHelper.read(id);
			if(person != null) {
				response.setPerson(person);
				response.setStatus(Status.VALID);
			}else {
				response.setPerson(new Person(id));
				response.setStatus(Status.ID_NOT_FOUND);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public Response update(Person person) {
		return new Response(fileHelper.update(person), Status.VALID);
	}

	public Response delete(long id) {
		fileHelper.delete(id);
		return new Response(null, Status.VALID);
	}
}
