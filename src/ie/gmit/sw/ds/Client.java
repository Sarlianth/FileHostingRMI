package ie.gmit.sw.ds;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		
		Scanner console = new Scanner(System.in);
		
		//Ask the registry running on localhost and listening in port 1099 for the instannce of
		//the MessageService object that is bound to the RMI registry with the name howdayService.
		FileService fs = (FileService) Naming.lookup("rmi://localhost:1099/fileService");
		
		System.out.println("List of files on the server: " + fs.getFileNames());
		
		System.out.println("File to download: ");
		String filename = console.nextLine();
		try {
			byte[] bytes = fs.getFile(filename);
			FileOutputStream stream = new FileOutputStream("clientFiles/" + filename + ".txt");
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("File to upload to server: ");
		filename = console.nextLine();
		
		try {
			fs.uploadFile(filename, Files.readAllBytes(new File("clientFiles/"+filename+".txt").toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("List of files on the server: " + fs.getFileNames());
	}
}
