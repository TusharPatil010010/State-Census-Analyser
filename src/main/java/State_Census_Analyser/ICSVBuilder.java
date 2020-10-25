package State_Census_Analyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
	public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;
}

