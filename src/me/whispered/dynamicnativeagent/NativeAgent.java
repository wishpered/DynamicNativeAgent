package me.whispered.dynamicnativeagent;

import me.whispered.dynamicnativeagent.common.ClassDef;
import me.whispered.dynamicnativeagent.common.JVMTIError;

public class NativeAgent {
	public NativeAgent() {
	}

	/**
	 * Redefine classes using definitions
	 * @param definitions
	 * @return JVMTIError
	 */
	public JVMTIError redefineClasses(ClassDef... definitions) {
		return JVMTIError.parse(this.redefineClasses0(definitions));
	}

	private native int redefineClasses0(ClassDef[] definitions);

	public void appendToBootstrapClassLoaderSearch(String path) {
		appendToBootstrapClassLoaderSearch0(path.toCharArray());
	}

	private native int appendToBootstrapClassLoaderSearch0(char[] segment);

}
