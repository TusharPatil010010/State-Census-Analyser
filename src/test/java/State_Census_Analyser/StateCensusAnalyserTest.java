package State_Census_Analyser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class StateCensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE = "\\useless.txt";
	private static final String WRONG_EXTENSION = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resourcess\\IndiaStateCensusData.txt";
	private static final String CSVFILE = "C:\\Users\\LENOVO\\eclipse-workspace\\State_Census_Analyser\\src\\main\\resources\\USCensusData.csv";
	private static final String STATE_CODE_CSV = "C:\\Users\\LENOVO\\eclipse-workspace\\Indian State Census\\IndiaStateCode.csv";

	@Test
	public void givenCSVFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException, CSVBuilderException {
		StateCensusAnalyser analyser = new StateCensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE, CSVStateCensus.class);
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
			count = analyser.loadCSVData(WRONG_FILE, CSVStateCensus.class);
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
			count = analyser.loadCSVData(WRONG_EXTENSION, CSVStateCensus.class);
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
			count = analyser.loadCSVData(STATECENSUS_CSVFILE, CSVStateCensus.class);
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
			count = analyser.loadCSVData(CSVFILE, CSVStateCensus.class);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

}
