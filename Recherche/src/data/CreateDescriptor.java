package data;

public class CreateDescriptor extends Descriptor{


	/**
	 * 
	 */

	public CreateDescriptor(String keyword){
		super(keyword);
	}
	
	public Site getUniqSite(){
		return super.getSites().get(0);
	}

}
