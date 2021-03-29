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
	native int redefineClasses0(ClassDef[] definitions);

}
