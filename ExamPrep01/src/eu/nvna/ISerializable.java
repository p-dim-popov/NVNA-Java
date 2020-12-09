package eu.nvna;

import java.io.Serializable;

public interface ISerializable<T> extends Serializable {
    String serialize();
}
