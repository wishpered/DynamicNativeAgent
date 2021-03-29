package me.whispered.dynamicnativeagent;

import me.whispered.dynamicnativeagent.utils.OSInfo;
import me.whispered.dynamicnativeagent.utils.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;

public class AgentFactory {
	private AgentFactory() {};

	/**
	 * Load the native library
	 */
	public static void loadNative() {
		try {
			byte[] nativeBytes = new byte[0];
			OSInfo.OSType osType = OSInfo.getOSType();
			if (osType.equals(OSInfo.OSType.WINDOWS)) {
				nativeBytes = StreamUtils.isToByteArray(AgentFactory.class.getResourceAsStream("/libAgent.dll"));
			} else if (osType.equals(OSInfo.OSType.LINUX)) {
				nativeBytes = StreamUtils.isToByteArray(AgentFactory.class.getResourceAsStream("/libAgent.so"));
			} else {
				throw new RuntimeException("The JVM is not compatible with DynamicAgents");
			}
			File nativeFile = File.createTempFile("libAgent_",".lib");
			nativeFile.deleteOnExit();
			FileOutputStream outputFile = new FileOutputStream(nativeFile);
			outputFile.write(nativeBytes);
			outputFile.close();
			System.load(nativeFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create an instance of NativeAgent
	 */
	public static NativeAgent createNativeAgent() {
		return new NativeAgent();
	}

}
