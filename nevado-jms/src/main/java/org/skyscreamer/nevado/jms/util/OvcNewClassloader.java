package org.skyscreamer.nevado.jms.util;


import com.amazonaws.handlers.HandlerChainFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Our Custom Class Loader to load the classes. Any class in the com.amazonaws
 * package will be loaded using this ClassLoader. For other classes, it will
 * delegate the request to its Parent ClassLoader.
 *
 */
public class OvcNewClassloader extends ClassLoader{
	/**
	 * This constructor is used to set the parent ClassLoader
	 */
	public OvcNewClassloader(ClassLoader parent) {
		super(parent);
	}

	/**
	 * Every request for a class passes through this method. If the class is in
	 * com.amazonaws package, we will use this classloader or else delegate the
	 * request to parent classloader.
	 *
	 *
	 * @param name
	 *            Full class name
	 */
	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		System.out.println("Loading Class '" + name + "'");
		if ( name.startsWith("com.amazonaws.") ) {
			System.out.println("Loading Class using OvcNewClassLoader");
			return getClass(name);
		}
		if (name.startsWith("com.oneviewcommerce.shande.nevado.")) {
			System.out.println("Classloaders are confusing to compliers....");
		}
//		return super.loadClass(name);
		return getClass(name);
	}

	/**
	 * Loads the class from the file system. The class file should be located in
	 * the file system. The name should be relative to get the file location
	 *
	 * @param name
	 *            Fully Classified name of class, for example com.oneviewcommerce.AClass
	 */
	private Class getClass(String name) throws ClassNotFoundException {
		if (name.startsWith("com.oneviewcommerce.shande.nevado.")) {
			name = name.substring(32);
		}
		//String file = "com/oneviewcommerce/shade/nevado/" + name.replace('.', File.separatorChar) + ".class";
		String file = "com/oneviewcommerece/shade/nevado/com/amazonaws/services/sqs/QueueUrlHandler.class";
		byte[] b = null;
		try {
			// This loads the byte code data from the file
			b = loadClassFileData(file);
			Class c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Reads the file (.class) into a byte array. The file should be
	 * accessible as a resource and make sure that its not in Classpath to avoid
	 * any confusion.
	 *
	 * @param name
	 *            File name
	 * @return Byte array read from the file
	 * @throws IOException
	 *             if any exception comes in reading the file
	 */
	private byte[] loadClassFileData(String name) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				"com/oneviewcommerece/shade/nevado/com/amazonaws/services/sqs/QueueUrlHandler.class");
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}

	public void invokeOvcShadedClass(String awsClassName) {
		try {
			// Create New class loader
			HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
			handlerChainFactory.newRequestHandler2Chain("com.amazonaws.services.sqs.QueueUrlHandler");
			//Class aClass = ClassLoaderHelper.loadClass("com.amazonaws.services.sqs.QueueUrlHandler", true);

			// Load the amazonaws class using but append the shade
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			if (awsClassName.startsWith("com.amazonaws.")) {
//				Class newClass = classLoader.loadClass(awsClassName);
//				System.out.println("The Following Class has been LOADED " + newClass.getName());
//			}
			//System.out.println("The following class has been loaded" + aClass.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}