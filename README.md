# DynamicNativeAgent
DynamicNativeAgent is a jvmti wrapper for redefine java classes at runtime using natives (without Instrumentation or custom ClassLoaders)

## Usage

```java
AgentFactory.loadNative(); //Load the .dll or .so in the JVM

NativeAgent agent = AgentFactory.createNativeAgent(); //Create new agent

//Redefine some classes
agent.redefineClasses(
  new ClassDef(TestClass.class, bytesOfClass),
  new ClassDef(RandomClass.class, bytesOfClass)
);
```
