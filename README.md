# DynamicNativeAgent
DynamicNativeAgent is a jvmti wrapper for redefine java classes at runtime using natives (without Instrumentation or custom ClassLoaders)

## Usage

```java
AgentFactory.loadNative();

NativeAgent agent = AgentFactory.createNativeAgent();

//Redefine some classes
agent.redefineClasses(
  new ClassDef(TestClass.class, bytesOfClass),
  new ClassDef(RandomClass.class, bytesOfClass)
);
```
