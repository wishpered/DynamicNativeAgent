# DynamicNativeAgent
The DynamicNativeAgent can redefine java classes using native library (without classloaders or Instrumentation)

## Usage

```java
AgentFactory.loadNative(); //Load the native in the JVM

NativeAgent agent = AgentFactory.createNativeAgent(); //Create new dynamic agent

//Redefine some classes
agent.redefineClasses(
  new ClassDef(TestClass.class, bytesOfClass),
  new ClassDef(RandomClass.class, bytesOfClass)
);
```

## Builds
Linux : [download](https://github.com/wishpered/DynamicNativeAgent/releases/download/beta/DynamicNativeAgent-linux.jar)
Windows : soon
