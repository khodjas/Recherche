package data;
/**
 * 
 * Classe permettant la création de Descriptor
 *
 */
public class CreateDescriptor extends Descriptor{


	/**
	 * Constructeur
	 * @param keyword de type Word qui sera le Mot-clé du site
	 */

	public CreateDescriptor(Word keyword){
		super(keyword);
	}
	/**
	 * retourne l'unique site du descripteur
	 * @return le site correspondant au descriptor
	 */
	public Site getUniqSite(){
		return super.getSites().get(0);
	}

}
