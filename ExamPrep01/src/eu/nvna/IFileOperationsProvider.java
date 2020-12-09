package eu.nvna;

import java.nio.file.Path;

public interface IFileOperationsProvider<T extends ISerializable<T> > {
    Iterable<T> readAll(String filename);
    Path writeTo(String filename);
    Path edit(String filename, int row, T element);
}
