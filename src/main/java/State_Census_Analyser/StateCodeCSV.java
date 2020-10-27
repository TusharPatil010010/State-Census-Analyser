package State_Census_Analyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {
	@CsvBindByName(column = "State Name", required = true)
	public String state;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "StateCodeCSV [state=" + state + ", stateCode=" + stateCode + "]";
	}
}