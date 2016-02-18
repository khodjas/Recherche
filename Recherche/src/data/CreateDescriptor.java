package data;

public class CreateDescriptor extends Descriptor {

	public CreateDescriptor() {
		super();
	}

	/**
	 * va permettre d'ajouter un KeyWord
	 * 
	 * @param keyWord
	 */
	public void addKeyWord(KeyWord keyWord) {
		super.getKeyWords().add(keyWord);
	}

	/**
	 * va permettre d'ajouter un Site
	 * 
	 * @param site
	 */

	public void addSite(Site site) {
		super.getSites().add(site);
	}

}
