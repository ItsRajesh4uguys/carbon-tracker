package de.unigoettingen.ct.obd.cmd;

import de.unigoettingen.ct.data.io.Measurement;

public class VehicleIdentificationNumberCmd extends ObdCommand {
	
	private String vin = "UNKNOWNVIN";

	@Override
	public String getCommandString() {
		return "0902";
	}

	@Override
	public void processResponse(String response, Measurement measure)  {
		// TODO Auto-generated method stub
		//this is complicated, see the elm document
	}
	
	/**
	 * This method is an exception to the normal ObdCommand interface.
	 * It is needed ins this case, as this command must return a value and can not modify the measurement object.
	 * Call this method after a call to {@link #processResponse(String, Measurement)}.
	 * @return the received vin
	 */
	public String getVin(){
		return this.vin;
	}

	@Override
	public int getNumberOfExpectedChars() {
		return -1;
	}
	
	@Override
	public String toString() {
		return "Vehicle Identification Number (VIN)";
	}

}
