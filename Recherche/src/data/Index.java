package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Index {
	private ArrayList<Descriptor> descriptors;

	public Index() {
		descriptors = new ArrayList<Descriptor>();
	}

	public ArrayList<Descriptor> getDescriptors() {
		return descriptors;
	}

	public Descriptor getSpecificDescriptor(String keyword) throws NullPointerException {
		Descriptor descriptor2 = new Descriptor(keyword);
		for (int index = 0; index < descriptors.size(); index++) {
			if (descriptors.get(index).compare(descriptor2)) {

				return descriptors.get(index);
			}

		}
		throw new NullPointerException();
	}

	public void load(String fileName) {
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream("fichier.ser"));
			Descriptor descriptor;
			while ((descriptor = (Descriptor) stream.readObject()) != null) {
				descriptors.add(descriptor);
			}
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("l'enregistrement est terminé");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		CreateIndex index = new CreateIndex();

		index.load("fichier.ser");
		System.out.println(index.getDescriptors().get(0).getSites().get(0).getUrl());
		for (Descriptor descripto : index.getDescriptors()) {
			System.out.println(descripto.getSites().get(0).getWord().getOccurence());

		}
	}

}
