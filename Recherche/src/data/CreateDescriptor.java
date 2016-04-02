package data;

public class CreateDescriptor extends Descriptor{


	/**
	 * 
	 */

	public CreateDescriptor(Word keyword){
		super(keyword);
	}
	
	public Site getUniqSite(){
		return super.getSites().get(0);
	}

}
