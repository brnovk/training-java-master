package by.training.ifaces;

import by.training.model.beans.File;

public interface IFileDAO {

	File setFile(String fileName);
	void removeFile(int idFile);
}
