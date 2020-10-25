package State_Census_Analyser;

public class CSVBuilderFactory {

	public static <E> ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
