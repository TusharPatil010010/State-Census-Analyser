package State_Census_Analyser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.csvbuildernew.CSVBuilderException;
import com.google.gson.Gson;

public class StateCensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\IndiaStateCensusData.csv";
	private static final String STATE_CODE_CSV = "\"C:\\Users\\LENOVO\\eclipse-workspace\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCode.csv\"";
	private static final String CSVFILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\USCensusData.csv";
	private static final String WRONG_FILE = "\\useless.txt";
	private static final String WRONG_EXTENSION = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resourcess\\IndiaStateCensusData.txt";

	/**
	 * UC1 and UC2 checking CSV inputs
	 * 
	 * @throws IOException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenCSVFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
		assertEquals(29, count);
	}

	@Test
	public void givenCSVFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_FILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError()
			throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_EXTENSION);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError()
			throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	@Test
	public void givenCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError()
			throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(CSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC3 sort census data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForFirstState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].stateName);
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].stateName);
	}

	/**
	 * UC4 sort census by code
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResultForFirstState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(STATE_CODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("AN", censusCSV[0].stateCode);
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadIndianStateCode(STATE_CODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		StateCodeCSV[] censusCSV = new Gson().fromJson(sortedCensusData, StateCodeCSV[].class);
		assertEquals("WB", censusCSV[censusCSV.length - 1].stateCode);
	}

	/**
	 * UC5 sort by population
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResultForFirstState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Uttar Pradesh", censusCSV[0].stateName);
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Sikkim", censusCSV[censusCSV.length - 1].stateName);
	}

	/**
	 * UC6 sort by density
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResultForFirstState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Bihar", censusCSV[0].stateName);
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Arunachal Pradesh", censusCSV[censusCSV.length - 1].stateName);
	}

	/**
	 * UC7 Sort by area
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResultFirstState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Rajasthan", censusCSV[0].stateName);
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResultLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Goa", censusCSV[censusCSV.length - 1].stateName);
	}
}
