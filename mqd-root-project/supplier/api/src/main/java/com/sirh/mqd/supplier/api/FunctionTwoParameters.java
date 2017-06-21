package com.sirh.mqd.supplier.api;

@FunctionalInterface
public interface FunctionTwoParameters<T, U, V> {

	public V apply(T t, U u);
}
