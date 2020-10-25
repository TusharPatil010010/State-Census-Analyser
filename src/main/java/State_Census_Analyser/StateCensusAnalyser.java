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
			Iterator<CSVStateCensus> censusIterator = this.getCSVFileIterator(reader, CSVStateCensus.class);
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

	/**
	 * UC2 loads india state code data
	 * 
	 * @param csvFile
	 * @return
	 * @throws IOException
	 * @throws CensusAnalyserException
	 */
	public int loadIndianStateCode(String csvFile) throws IOException, CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			Iterator<StateCodeCSV> censusIterator = this.getCSVFileIterator(reader, StateCodeCSV.class);
			int countOfRecord = 0;
			while (censusIterator.hasNext()) {
				countOfRecord++;
				StateCodeCSV censusData = censusIterator.next();
			}
			return countOfRecord;
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}