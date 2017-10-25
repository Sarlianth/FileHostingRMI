package ie.gmit.sw.ds;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FileService extends java.rmi.Remote {
	
	public byte[] getFile(String fileName) throws IOException;
	public ArrayList<File> getFileNames() throws RemoteException;
	public void uploadFile(String fileName, byte[] file) throws IOException;

}
