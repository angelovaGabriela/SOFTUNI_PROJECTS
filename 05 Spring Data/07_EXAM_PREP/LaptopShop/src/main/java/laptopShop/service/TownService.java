package laptopShop.service;



import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException, JAXBException;
	
	String importTowns() throws JAXBException, IOException;
}
