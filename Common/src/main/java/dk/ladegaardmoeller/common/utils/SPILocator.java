package dk.ladegaardmoeller.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class SPILocator {
	public static <T> List<T> locateAll(Class<T> service) {
		ServiceLoader<T> loader = ServiceLoader.load(service);
		
		List<T> instances = new ArrayList<>();
		for (T instance : loader) {
			instances.add(instance);
		}
		
		System.out.println("Found " + instances.size() + " implementations of type: " + service.getName());
		return instances;
	}
}
