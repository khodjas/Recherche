package data;

public class CreateDescriptor extends Descriptor{


	public CreateDescriptor(String keyword){
		super(keyword);
	}
	
	public String getUniqSite(){
		return super.getSites().get(0);
	}

}
