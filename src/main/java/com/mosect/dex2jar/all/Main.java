package com.mosect.dex2jar.all;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<String, String> classNameMap;

    private static void printHelp() {
        System.out.println("BuildFor: dex-tools-2.2-SNAPSHOT");
        for (String cmd : getClassNameMap().keySet()) {
            System.out.println(cmd + ":");
            System.out.printf("java -jar <this_jar> %s <args>%n", cmd);
        }

        System.out.println();
        System.out.println("other: ");
        System.out.println("java -jar <this_jar> <class_name> <args>");
    }

    private static Map<String, String> getClassNameMap() {
        if (null == classNameMap) {
            classNameMap = new HashMap<>();
            classNameMap.put("d2j-dex2jar", "com.googlecode.dex2jar.tools.Dex2jarCmd");
            classNameMap.put("d2j-apk-sign", "com.googlecode.dex2jar.tools.ApkSign");
            classNameMap.put("d2j-asm-verify", "com.googlecode.dex2jar.tools.AsmVerify");
            classNameMap.put("d2j-baksmali", "com.googlecode.d2j.smali.BaksmaliCmd");
            classNameMap.put("d2j-class-version-switch", "com.googlecode.dex2jar.tools.ClassVersionSwitch");
            classNameMap.put("d2j-decrypt-string", "com.googlecode.dex2jar.tools.DecryptStringCmd");
            classNameMap.put("d2j-dex2smali", "com.googlecode.d2j.smali.BaksmaliCmd");
            classNameMap.put("d2j-dex-recompute-checksum", "com.googlecode.dex2jar.tools.DexRecomputeChecksum");
            classNameMap.put("d2j-dex-weaver", "com.googlecode.dex2jar.tools.DexWeaverCmd");
            classNameMap.put("d2j-jar2dex", "com.googlecode.dex2jar.tools.Jar2Dex");
            classNameMap.put("d2j-jar2jasmin", "com.googlecode.d2j.jasmin.Jar2JasminCmd");
            classNameMap.put("d2j-jar-access", "com.googlecode.dex2jar.tools.JarAccessCmd");
            classNameMap.put("d2j-jar-weaver", "com.googlecode.dex2jar.tools.JarWeaverCmd");
            classNameMap.put("d2j-jasmin2jar", "com.googlecode.d2j.jasmin.Jasmin2JarCmd");
            classNameMap.put("d2j-smali", "com.googlecode.d2j.smali.SmaliCmd");
            classNameMap.put("d2j-std-apk", "com.googlecode.dex2jar.tools.StdApkCmd");
        }
        return classNameMap;
    }

    private static String getSafeClassName(String className) {
        String scn = getClassNameMap().get(className);
        if (null != scn) return scn;
        return className;
    }

    public static void main(String[] args) throws Exception {
        if (null == args || args.length == 0) {
            printHelp();
        } else {
            String className = getSafeClassName(args[0]);
            Class<?> cls = Class.forName(className);
            Method method = cls.getDeclaredMethod("main", String[].class);
            String[] targetArgs = new String[args.length - 1];
            System.arraycopy(args, 1, targetArgs, 0, targetArgs.length);
            method.invoke(null, (Object) targetArgs);
        }
    }
}
