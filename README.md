# 可直接录制mp3
## 感谢：[AndroidMp3Recorder](https://github.com/telescreen/AndroidMp3Recorder)
### [AndroidMp3Recorder](https://github.com/telescreen/AndroidMp3Recorder)有中文文档，好像更好用



### so包相关

* 如果包名更改了，```main/jni/libmp3lame/Android.mk```中的倒数第二行的**\.c\**的文件需要更改。

* src/main/jni/libmp3lame中的.c和.h的文件需要重新生成然后**修改.c中的方法名，那个是跟路径相关的，必须改

* 重新生成.h文件在Android studio中Terminal中使用命令
javah -d jni -jni -classpath /Users/wanglei/Downloads/AndroidMp3Recorder-master2/recordingmp3library/build/intermediates/classes/debug com.myUtil.recordingmp3library.SimpleLame

* 生成so包点击Bulid->Make Project
* 需要在Aplication.mk中的APP_ABI := armeabi armeabi-v7a mips x86配置上，v8a和x86_64不能添加也不能使用all，会报错(具体原因不明)

* 在当前Module中的build.gradle中android节点中添加
 sourceSets {
        main {
            jni.srcDirs = []//禁用as自动生成mk
        }
    }
    task ndkBuild(type: Exec, description: 'Compile JNI source via NDK') {
        commandLine "/Users/wanglei/Documents/ndk/ndk10/ndk-build",//配置ndk的路径
                'NDK_PROJECT_PATH=build/intermediates/ndk',//ndk默认的生成so的文件
                'NDK_LIBS_OUT=src/main/jniLibs',//配置的我们想要生成的so文件所在的位置
                'APP_BUILD_SCRIPT=src/main/jni/Android.mk',//指定项目以这个mk的方式
                'NDK_APPLICATION_MK=src/main/jni/Application.mk'//指定项目以这个mk的方式
    }
    tasks.withType(JavaCompile) {
            //使用ndkBuild
        compileTask -> compileTask.dependsOn ndkBuild
    }

Module作为依赖使用时删除jni文件夹，要不报错，因为没配置CMake的原因



