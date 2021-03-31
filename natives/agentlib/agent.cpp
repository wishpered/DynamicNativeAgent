#include <string.h>
#include "jni.h"
#include "jvmti.h"
#include "NativeAgent.h"

using namespace std;

static JavaVM * jvm;
static jvmtiEnv * jvmti;

JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM * vm, void * reserved) {
	jvm = vm;
	jvm -> GetEnv((void ** ) & jvmti, JVMTI_VERSION_1_0);
	jvmtiCapabilities capabilities;
	jvmti->GetCapabilities(&capabilities);
	capabilities.can_redefine_classes = 1;
	capabilities.can_redefine_any_class = 1;
	jvmtiError err = jvmti -> AddCapabilities( & capabilities);
	return JNI_VERSION_1_4;
}

void* allocate(jvmtiEnv * jvmtienv, size_t bytecount) {
	void * resultBuffer = NULL;
	jvmtiError error = JVMTI_ERROR_NONE;

	error = jvmtienv -> Allocate(
		bytecount,
		(unsigned char ** ) & resultBuffer);
	if (error != JVMTI_ERROR_NONE) {
		resultBuffer = NULL;
	}
	return resultBuffer;
}

void deallocate(jvmtiEnv * jvmtienv, void * buffer) {
	jvmtienv -> Deallocate((unsigned char * ) buffer);
	return;
}


JNIEXPORT jint JNICALL Java_me_whispered_dynamicnativeagent_NativeAgent_redefineClasses0
(JNIEnv * env, jobject instance, jobjectArray classDefinitions) {
	jint error = 0;
	jclass classDefClass = NULL;
	jmethodID getClazzMethodID = NULL;
	jmethodID getClassBytesMethodID = NULL;
	jsize numDefs = 0;
	numDefs = env -> GetArrayLength(classDefinitions);
	classDefClass = env -> FindClass("me/whispered/dynamicnativeagent/common/ClassDef");
	getClazzMethodID = env -> GetMethodID(classDefClass,"getClazz","()Ljava/lang/Class;");
	getClassBytesMethodID = env -> GetMethodID(classDefClass,"getClassBytes","()[B");
	jvmtiClassDefinition * definitions = (jvmtiClassDefinition * ) allocate(jvmti,numDefs * sizeof(jvmtiClassDefinition));
	jbyteArray * targetFiles = (jbyteArray * ) allocate(jvmti,numDefs * sizeof(jbyteArray));
	jint i, j;
	memset(definitions, 0, numDefs * sizeof(jvmtiClassDefinition));
	for (i = 0; i < numDefs; i++) {
		jclass classDef = NULL;
		classDef = (jclass) env -> GetObjectArrayElement(classDefinitions, i);
		definitions[i].klass = (jclass) env -> CallObjectMethod(classDef, getClazzMethodID);
		targetFiles[i] = (jbyteArray) env -> CallObjectMethod(classDef, getClassBytesMethodID);
		definitions[i].class_byte_count = env -> GetArrayLength(targetFiles[i]);
		definitions[i].class_bytes = (unsigned char * ) env -> GetByteArrayElements(targetFiles[i], NULL);
	}
	error = (jint) jvmti -> RedefineClasses(numDefs, definitions);
	for (j = 0; j < i; j++) {
		if ((jbyte * ) definitions[j].class_bytes != NULL) {
			env -> ReleaseByteArrayElements(targetFiles[j], (jbyte * ) definitions[j].class_bytes,0);
		}
	}
	deallocate(jvmti, (void * ) targetFiles);
	deallocate(jvmti, (void * ) definitions);

	classDefinitions = NULL;
	instance = NULL;
	env = NULL;
	
	return error;
}
