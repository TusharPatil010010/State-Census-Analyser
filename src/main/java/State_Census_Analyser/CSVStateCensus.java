package State_Census_Analyser;

import com.opencsv.bean.CsvBindByName;

import java.util.Comparator;

public class CSVStateCensus implements Comparable<CSVStateCensus> {

	@CsvBindByName(column = "State")
	private String stateName;

	@CsvBindByName(column = "Population", required = true)
	private String population;

	@CsvBindByName(column = "AreaInSqKm")
	private String areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm", required = true)
	private String densityPerSqKm;

	public CSVStateCensus() {

	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getAreaInSqKm() {
		return areaInSqKm;
	}

	public void setAreaInSqKm(String areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}

	public String getDensityPerSqKm() {
		return densityPerSqKm;
	}

	public void setDensityPerSqKm(String densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}

	@Override
	public String toString() {
		return "stateName='" + stateName + '\'' + ", population='" + population + '\'' + ", areaInSqKm='" + areaInSqKm
				+ '\'' + ", densityPerSqKm='" + densityPerSqKm + '\'' + "\n";
	}

	@Override
	public int compareTo(CSVStateCensus stateCensus) {
		return this.stateName.compareTo(stateCensus.stateName);
	}

	static class StateCensusComparator implements Comparator<CSVStateCensus> {
		public int compare(CSVStateCensus obj1, CSVStateCensus obj2) {
			return obj1.getStateName().compareTo(obj2.getStateName());
		}
	}

}
