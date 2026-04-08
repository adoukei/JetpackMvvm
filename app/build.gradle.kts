plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "me.hgj.jetpackmvvm.demo"
    compileSdk = 36

    defaultConfig {
        applicationId = "me.hgj.jetpackmvvm.demo"
        minSdk = 23
        targetSdk = 36
        versionCode = 33
        versionName = "2.0.3"
        multiDexEnabled = true
        ndk {
            abiFilters.addAll(listOf("arm64-v8a"))
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    signingConfigs {
        create("release") {
            storeFile = file("cxk.jks")
            storePassword = "cxk666"
            keyAlias = "kunkun"
            keyPassword = "cxk666"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false //关闭混淆
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release") // 签名信息配置
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro") //混淆规则文件
        }
        named("release") {
            isMinifyEnabled = true //开启混淆
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release") // 签名信息配置
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro") //混淆规则文件
        }
    }



    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}

kotlin {
    jvmToolchain(17) // 自动设置 Java 17 兼容性
}

dependencies {
    //基础库
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)

    //项目核心框架
    implementation(project(":JetpackMvvm"))

    //================================网络框架 sart ===========================//
    // rxhttp示例引入((demo.app.core.net.rxhttp 包中))
    implementation(libs.okhttp) //必须
    implementation(libs.rxhttp)
    ksp(libs.rxhttp.compiler)

    //retrofit示例(demo.app.core.net.retrofit 包中)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //================================网络框架 end ===========================//

    //================================项目相关使用到的库 sart ===========================//
    implementation(libs.dialog.bootomsheets)
    //轮播图
    implementation(libs.banner)
    //lottie动画
    implementation(libs.lottie)
    //指示器库
    implementation(libs.magicindicator)
    //屏幕适配
    implementation(libs.autosize)
    //防崩溃
    implementation(libs.customactivityoncrash)
    //富文本工具
    implementation(libs.spannable)
    //================================项目相关使用到的库 end ===========================//
}
