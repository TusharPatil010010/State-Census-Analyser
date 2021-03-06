package State_Census_Analyser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.csvbuildernew.CSVBuilderException;
import com.csvbuildernew.CSVBuilderFactory;
import com.csvbuildernew.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<CSVStateCensus> censusCSVList = null;
	List<StateCodeCSV> stateCodeCSVList = null;
	static int censusCounter;

	/**
	 * UC1 loads csv census data
	 * 
	 * @param csvFile
	 * @return
	 * @throws CensusAnalyserException
	 * @throws IOException
	 * @throws CSVBuilderException
	 */
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			return censusCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	/**
	 * UC2 loads csv code data
	 * 
	 * @param csvFile
	 * @return
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	public int loadIndianStateCode(String csvFile) throws IOException, CensusAnalyserException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<StateCodeCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			stateCodeCSVList = csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return stateCodeCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	private <E> void sortList(List<E> cnesusList, Comparator<E> censusComparator) {
		for (int i = 0; i < cnesusList.size(); i++) {
			for (int j = 0; j < cnesusList.size() - i - 1; j++) {
				E census1 = cnesusList.get(j);
				E census2 = cnesusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					cnesusList.set(j, census2);
					cnesusList.set(j + 1, census1);
				}
			}
		}
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < censusCSVList.size(); i++) {
			for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusCSVList.get(j);
				CSVStateCensus census2 = censusCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) < 0) {
					censusCSVList.set(j, census2);
					censusCSVList.set(j + 1, census1);
				}
			}
		}
	}

	/**
	 * UC3 sorts census data
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.stateName);
		this.sortList(censusCSVList, censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	/**
	 * UC4 sorts census according to codes
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
		if (stateCodeCSVList == null || stateCodeCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<StateCodeCSV> censusComparator = Comparator.comparing(census -> census.stateCode);
		this.sortList(stateCodeCSVList, censusComparator);
		String sorted = new Gson().toJson(stateCodeCSVList);
		return sorted;
	}

	/**
	 * UC5 sorts according to population
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.population);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	/**
	 * UC6 sorts according to density
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	/**
	 * UC7 sorts according to Area
	 * 
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}
}