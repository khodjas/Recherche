package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
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

	public Descriptor getSpecificDescriptor(String keyword) {
		Descriptor descriptor2 = new Descriptor(keyword);
		for (int index = 0; index <= descriptors.size(); index++) {
			if (descriptors.get(index).compare(descriptor2)) {

				return descriptors.get(index);
			}

		}
		return null;
	}

	public void load(String fileName) {
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream("fichier.ser"));
			Descriptor descriptor = null;
			while ((descriptor = (Descriptor) stream.readObject()) != null) {
				descriptors.add(descriptor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

}
