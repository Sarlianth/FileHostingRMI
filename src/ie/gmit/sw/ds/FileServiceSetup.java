package ie.gmit.sw.ds;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;

public class FileServiceSetup {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		FileServiceImpl fsi = new FileServiceImpl();
		
		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);
		
		//Bind our remote object to the registry with the human-readable name "howdayService"
		Naming.rebind("fileService", fsi);
		
		//Print a nice message to standard output
		System.out.println("Server ready.");
		System.out.println(fsi.getFileNames());

	}

}
