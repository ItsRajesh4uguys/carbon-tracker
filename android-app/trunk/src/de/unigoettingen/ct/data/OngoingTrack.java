package de.unigoettingen.ct.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OngoingTrack {
	
	private TrackPart wrappedTrackPart;
	private List<Measurement> measurementList;
	
	public OngoingTrack(Calendar startedAt, String vin, String description, Person driver){
		this.wrappedTrackPart = new TrackPart(startedAt, vin, false, description, driver, null);
		this.measurementList = new ArrayList<Measurement>();
	}
	
	public void addMeasurement(Measurement m){
		if(this.wrappedTrackPart.isLastPart()){
			throw new IllegalStateException("Can not add any more measurements to a closed track.");
		}
		this.measurementList.add(m);
	}
	
	public int getMeasurementCount(){
		return this.measurementList.size();
	}
	
	public void removeFirstMeasurements(int count){
		if(count > this.measurementList.size()){
			throw new IndexOutOfBoundsException("Removing "+count+" measurements not possible, when only "+this.measurementList.size()+" present.");
		}
		//the first 'count' elements are cut off, and will be subject to garbage collection
		this.measurementList = new ArrayList<Measurement>(this.measurementList.subList(count, this.measurementList.size()));
	}
	
	public TrackPart getTrackPart(){
		Measurement[] mArray = new Measurement[this.measurementList.size()];
		mArray = this.measurementList.toArray(mArray);
		TrackPart retVal = this.wrappedTrackPart.getCloneWithoutMeasurements();
		retVal.setMeasurements(mArray);
		return retVal;
	}
	
	public void setClosed(){
		this.wrappedTrackPart.setLastPart(true);
	}
	
	public boolean isClosed(){
		return this.wrappedTrackPart.isLastPart();
	}

	public String getVin() {
		return wrappedTrackPart.getVin();
	}

	public void setVin(String vin) {
		wrappedTrackPart.setVin(vin);
	}
	
}
