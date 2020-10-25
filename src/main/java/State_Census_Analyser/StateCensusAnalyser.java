package State_Census_Analyser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	/**
	 * UC1 loads csv data
	 * 
	 * @param csvFile
	 * @return
	 * @throws CensusAnalyserException
	 * @throws IOException
	 */
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusIterator = csvToBean.iterator();
			int countOfRecord = 0;
			while (censusIterator.hasNext()) {
				countOfRecord++;
				CSVStateCensus censusData = censusIterator.next();
			}
			return countOfRecord;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
}