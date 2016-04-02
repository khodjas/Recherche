package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateIndex extends Index {

	public CreateIndex() {
		super();
	}

	// permettra de récupérer les différents sites des différents descripteurs
	public ArrayList<Descriptor> getDescriptors() {
		return super.getDescriptors();
	}

	// ajout d'un descripteur si il n'éxiste pas, d'un site sinon
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

	public void addDictonnary(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String[] buffer;
			String value;
			CreateDescriptor descriptor;
			while ((value = reader.readLine()) != null) {
				buffer = value.split(" ");
				FamilyWord word = new FamilyWord(buffer[0]);
				for (int index = 1; index < buffer.length; index++) {
					word.addWord(buffer[index]);
				}
				descriptor = new CreateDescriptor(word);
				inserDescriptor(descriptor);
			}
			reader.close();
		} catch (ValidException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("l'enregistrement du dictionnaire est terminé");
		}
	}

	public static void main(String[] args) {

	}

}
