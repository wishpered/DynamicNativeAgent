# DynamicNativeAgent
DynamicNativeAgent is a jvmti wrapper for redefine java classes at runtime using natives (without Instrumentation or custom ClassLoaders)

#Usage

```
AgentFactory.loadNative();

NativeAgent agent = AgentFactory.createNativeAgent();

agent.redefineClasses(
  new ClassDef(TestClass.class, bytesOfClass);
);

```

