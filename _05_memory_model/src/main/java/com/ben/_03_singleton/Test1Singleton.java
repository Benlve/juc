package com.ben._03_singleton;

public class Test1Singleton {
    public static void main(String[] args) {
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        enumSingleton.setInfo(77);
        enumSingleton.setMsg("Michael");

        System.out.println(enumSingleton.hashCode());

        EnumSingleton single02 = EnumSingleton.INSTANCE;
        single02.setInfo(77);
        single02.setMsg("Michael");

        System.out.println(single02.hashCode());


        InnerHolderSingleton instance1 = InnerHolderSingleton.getInstance();
        InnerHolderSingleton instance2 = InnerHolderSingleton.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

    }
}
