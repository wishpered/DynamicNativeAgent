package me.whispered.dynamicnativeagent.utils;

public class OSInfo {
	public static OSType getOSType() throws SecurityException {
		String var0 = System.getProperty("os.name");
		if (var0 != null) {
			if (var0.contains("Windows")) {
				return OSType.WINDOWS;
			}

			if (var0.contains("Linux")) {
				return OSType.LINUX;
			}

			if (var0.contains("Solaris") || var0.contains("SunOS")) {
				return OSType.SOLARIS;
			}

			if (var0.contains("OS X")) {
				return OSType.MACOSX;
			}
		}

		return OSType.UNKNOWN;
	}
	public static enum OSType {
		WINDOWS,
		LINUX,
		SOLARIS,
		MACOSX,
		UNKNOWN;

		private OSType() {
		}
	}
}
