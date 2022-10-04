package com.example.football.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface StatService {
    boolean areImported();

    String readStatsFileContent() throws IOException;

    String importStats() throws FileNotFoundException, JAXBException;

}
