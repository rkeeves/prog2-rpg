package hu.unideb.inf.ioc.factory;

/**
 * A Factory is able to create an object of a given type.
 * @param <T> type of the creatable object
 */
public interface Factory<T> {

    /**
     * Returns the creatable objects type of this Factory.
     * @return type of the creatable object
     */
    Class<T> getReturnType();

    /**
     * Creates and returns a new instance of the object.
     * @param args arguments used in the construction or underlying method call
     * @return new instance of type
     */
    T newInstance(Object... args);

    /**
     * Return the Parameter types used by the newInstance method
     * @return an array holding the parameter types
     */
    Class<?>[] getParameterTypes();
}
