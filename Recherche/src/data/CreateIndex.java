package data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateIndex extends Index {

	public CreateIndex() {
		super();
	}

	// permettra de r�cup�rer les diff�rents sites des diff�rents descripteurs
	public ArrayList<Descriptor> getDescriptors() {
		return super.getDescriptors();
	}

	// ajout d'un descripteur si il n'�xiste pas, d'un site sinon
	public void inserDescriptor(CreateDescriptor buffer) throws ValidException {
		if (getDescriptors().isEmpty()) {
			getDescriptors().add(buffer);
		} else {
			for (int index = 0; index < getDescriptors().size(); index++) {
				if (getDescriptors().get(index).compare(buffer)) {
					getDescriptors().get(index).addSite(buffer.getUniqSite());
					throw new ValidException();
				}

			}
			getDescriptors().add(buffer);
		}
	}

	public void save(String fileName) {
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(fileName));
			for (Descriptor descriptor : getDescriptors()) {
				stream.writeObject(descriptor);
			}
			stream.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		
	}

}
