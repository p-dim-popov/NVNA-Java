package eu.nvna;

public interface IFileOperationsProvider {
    void readAll(String filename);
    void write(String filename);
    void edit(String filename, int row, TV tv);
}
