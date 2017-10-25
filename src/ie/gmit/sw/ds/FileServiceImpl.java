package ie.gmit.sw.ds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;
import java.util.Arrays;

public class FileServiceImpl extends UnicastRemoteObject implements FileService, Unreferenced{

	private static final long serialVersionUID = 1L;
	ArrayList<File> files;

	protected FileServiceImpl() throws RemoteException {
		super();
	}

	public byte[] getFile(String fileName) throws IOException {
		return Files.readAllBytes(new File("serverFiles/"+fileName+".txt").toPath());
	}

	public ArrayList<File> getFileNames() throws RemoteException {
		File folder = new File("serverFiles/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		return files;
	}

	public void uploadFile(String fileName, byte[] file) throws IOException {
		FileOutputStream stream = new FileOutputStream("serverFiles/" + fileName + ".txt");
		stream.write(file);
		stream.close();
	}
	
	public void unreferenced(){
		System.out.println("FileServiceImpl: unreferenced() called.");
	}

}
