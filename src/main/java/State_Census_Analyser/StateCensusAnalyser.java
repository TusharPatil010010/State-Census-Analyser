package State_Census_Analyser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import java.util.List;

import com.google.gson.Gson;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

    private static String POPULATION_FILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\populationwise_sort.json";
    private static String NAME_FILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\namewise_sort.json";
    static List<CSVStateCensus> censusList;
    static int censusCounter;
    

	public <T> int loadCSVData(String csvFile, Object myClass) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
			sortThisListBasedOnStateName(censusCSVList);
			sortThisListBasedOnPopulation(censusCSVList);
			return censusCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
    private static void writeThisListToJsonFile(String writeToFile) {
        Gson gson = new Gson();
        String json = gson.toJson(censusList);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(writeToFile);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    private static void sortThisListBasedOnStateName(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = Comparator.comparing(CSVStateCensus::getStateName);
        censusList.sort(c);
        writeThisListToJsonFile(NAME_FILE);
    }
    
    private static void sortThisListBasedOnPopulation(List<CSVStateCensus> censusList) {
        Comparator<CSVStateCensus> c = (s1, s2) -> Integer.parseInt(s2.getPopulation()) - Integer.parseInt(s1.getPopulation());
        censusList.sort(c);
        writeThisListToJsonFile(POPULATION_FILE);
    }
}