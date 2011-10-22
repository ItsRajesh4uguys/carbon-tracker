package de.unigoettingen.ct.service;



public interface AsynchronousSubsystem {

	public abstract void addStatusListener(SubsystemStatusListener listener);

	public abstract void setUp();

	public abstract void start();

	public abstract void stop();

}