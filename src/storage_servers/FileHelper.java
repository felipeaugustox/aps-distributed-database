package storage_servers;

import models.Gender;
import models.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	private FileWriter fr;
	private String localPath;
	
	public FileHelper() {
		fr = null;
		localPath = System.getProperty("user.home") + "/DistributedDatabase/person/";
	}
	
	public String personToTxtData(Person person) {
		String data = person.getName() + "\r\n" + 
				person.getAge() + "\r\n" +
				person.getGender() + "\r\n" +
				person.getAddress();
		return data;
	}
	
	public boolean create(Person person) {
		String data = personToTxtData(person);
		
		File file = new File(localPath + person.getId() + ".txt");
        file.getParentFile().getParentFile().mkdir();
        file.getParentFile().mkdir();
        
        try {
            file.createNewFile();
        	fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return true;
	}
	
	public Person read(long id) throws IOException {
		String currentStrLine;
		Person person = new Person(id);
		int lineIndex = 0;
		BufferedReader br = null;
		
		File file = new File(localPath + id + ".txt");
		if(file.exists()) {
			try {
				br = new BufferedReader(new FileReader(file)); 			
				while ((currentStrLine = br.readLine()) != null) {
					switch(lineIndex) {
						case 0: person.setName(currentStrLine); break;
						case 1: person.setAge(Integer.valueOf(currentStrLine)); break;
						case 2: person.setGender(Gender.valueOf(currentStrLine)); break;
						case 3: person.setAddress(currentStrLine); break;
					}
					lineIndex++;
			  	}
			}catch(Exception e) {
				
			}finally {
				br.close();
			}
		}
		
		return person;
	}
	
	public void update() { }
	public void delete() { }
}
