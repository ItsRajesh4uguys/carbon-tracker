package de.unigoettingen.ct.cache;

import java.util.List;

import de.unigoettingen.ct.data.OngoingTrack;
import de.unigoettingen.ct.data.TrackSummary;

/**
 * This simple strategy uploads inactive tracks immediately (and with priority) and uploads active tracks when
 * they exceed 60 measurements. INTENDED TO BE A FIRST SHOT.
 * @author Fabian Sudau
 *
 */
public class SimpleCachingSystem extends AbstractCachingSystem{

	private static final int MEASUREMENT_THRESHOLD = 300; //there is no sophisticated reason for this number, approx. one upload every 5 min so far
	
	public SimpleCachingSystem(TrackCache cache, OngoingTrack activeTrack, PersistenceBinder persistence) {
		super(cache, activeTrack, persistence);
	}

	@Override
	protected void handleCacheChange(List<TrackSummary> tracks) {
		if(tracks.size() > 1 && tracks.get(0).getMeasurementCount() > 0){
			this.invokeUpload(0);
		}
		else if (tracks.get(0).getMeasurementCount() > MEASUREMENT_THRESHOLD){
			this.invokeUpload(0);
		}
	}

}