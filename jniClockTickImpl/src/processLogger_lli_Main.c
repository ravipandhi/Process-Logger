#include <jni.h>
#include "processLogger_lli_LowerLevelInfoFetcher.h"
#include <unistd.h>


JNIEXPORT jdouble JNICALL Java_processLogger_lli_LowerLevelInfoFetcher_nativePrint(JNIEnv *env, jobject obj){
	return sysconf(_SC_CLK_TCK);
}
