package se.kth.iv1350.seminar4.source.integration;

public class ExternalSystemCreator {
    private ExternalAccountingSystem externalAccountingSystem;
	private ExternalInventorySystem externalInventorySystem;
	private ExternalSystemCreator externalSystemCreator;


	/**
	 * Constructor for ExternalSystemCreator
	 */
    public ExternalSystemCreator() {
		externalAccountingSystem = new ExternalAccountingSystem();
		externalInventorySystem = ExternalInventorySystem.getInstance();

		return;
	}

	/**
	 * getter functions for the different external systems
	 * @return the external systems
	 */
	public ExternalSystemCreator getExternalSystemCreator(){
		return externalSystemCreator;
	}
	public ExternalInventorySystem getExternalInventorySystem() {
		return externalInventorySystem;
	}

	public ExternalAccountingSystem getExternalAccountingSystem() {
		return externalAccountingSystem;
	}

    
}
