# DynamicNativeAgent
The DynamicNativeAgent can redefine classes using native library (without classloaders or Instrumentation)

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
