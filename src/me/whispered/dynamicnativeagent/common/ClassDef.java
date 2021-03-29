package me.whispered.dynamicnativeagent.common;

public class ClassDef {
	private Class<?> clazz;
	private byte[] classBytes;

	public ClassDef(Class<?> clazz, byte[] classBytes) {
		this.clazz = clazz;
		this.classBytes = classBytes;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public byte[] getClassBytes() {
		return classBytes;
	}
}
