//package com.github.edgar615.util.reflect;
//
//import static com.github.edgar615.util.interceptor.Interceptor.intercepting;
//import static com.github.edgar615.util.interceptor.MethodInterpreter.caching;
//import static com.github.edgar615.util.interceptor.MethodInterpreter.handlingDefaultMethods;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Proxy;
//import java.util.Map;
//import java.util.stream.Stream;
//
//public final class Proxies {
//
//    @SuppressWarnings("unchecked")
//    public static <T> T simpleProxy(Class<? extends T> iface, InvocationHandler handler, Class<?>...otherIfaces) {
//        Class<?>[] allInterfaces = Stream.concat(
//                Stream.of(iface),
//                Stream.of(otherIfaces))
//                .distinct()
//                .toArray(Class<?>[]::new);
//
//        return (T) Proxy.newProxyInstance(iface.getClassLoader(),
//                allInterfaces,
//                handler);
//    }
//
//    public static <T> T interceptingProxy(T target, Class<T> iface, Interceptor interceptor) {
//        return simpleProxy(iface,
//                caching(intercepting(
//                        handlingDefaultMethods(MethodInterpreters.binding(target)),
//                        interceptor)));
//    }
//
//    public static <T> T propertyMapping(Class<? extends T> iface, Map<String, Object> propertyValues) {
//        PropertyValueStore store = new PropertyValueStore(iface, propertyValues);
//        return simpleProxy(iface, store.createMethodInterpreter(), EqualisableByState.class);
//    }
//
//}