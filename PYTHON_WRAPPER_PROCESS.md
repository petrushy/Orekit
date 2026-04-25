# Python Wrapper Class Process

Use this process whenever adding a new JCC-backed Java wrapper for an Orekit interface or abstract class.

## Rules

1. Find the original Java type to wrap.
   It must be the interface or abstract class that Python must subclass or implement.

2. Create the wrapper in the same package as the original type.
   Name it `PythonOriginalClassName`.

3. Add the JCC template verbatim.
   Every wrapper must contain this helper block, adjusted only for indentation and the `pythonObject` field visibility already used in nearby wrapper classes (`private` and `protected` both exist in this repository):

```java
    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();
```

4. For an interface wrapper, declare every interface method as `native`.
   This includes methods inherited from parent interfaces when they are abstract and not default methods.

5. For an abstract class wrapper, declare every abstract method as `native`.
   This includes abstract methods inherited through parent abstract classes or interfaces.
   Non-abstract inherited methods do not need wrapper declarations unless JNI access explicitly requires them.

6. For an abstract class wrapper, mirror the required superclass constructors.
   Existing wrappers keep thin pass-through constructors that only call `super(...)`, so the wrapped abstract base remains instantiable through JCC.

7. Preserve the original Java signature exactly.
   Keep method name, generics, parameter order, return type, checked exceptions, and varargs unchanged.

8. Add `@Override` on every implemented interface method or overridden abstract method.

9. Do not add Java-side behavior in the wrapper methods.
   The wrapper exists only to expose JNI entry points, so wrapper implementations should be `native`, not Java code.

## Minimal Template

```java
public class PythonOriginalClassName implements OriginalInterface {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    /** {@inheritDoc} */
    @Override
    public native ReturnType methodName(ParameterType parameter);
}
```

For an abstract base class, use the same pattern plus pass-through constructors:

```java
public class PythonOriginalAbstractClass extends OriginalAbstractClass {

    /** Part of JCC Python interface to object */
    private long pythonObject;

    /** Part of JCC Python interface to object */
    public void pythonExtension(long pythonObject) {
        this.pythonObject = pythonObject;
    }

    /** Part of JCC Python interface to object */
    public long pythonExtension() {
        return this.pythonObject;
    }

    /** Part of JCC Python interface to object */
    public void finalize() throws Throwable {
        pythonDecRef();
    }

    /** Part of JCC Python interface to object */
    public native void pythonDecRef();

    public PythonOriginalAbstractClass(Type1 arg1, Type2 arg2) {
        super(arg1, arg2);
    }

    /** {@inheritDoc} */
    @Override
    public native ReturnType abstractMethod(ParameterType parameter);
}
```

## Verification Checklist

- Wrapper class name is `PythonOriginalClassName`
- Package matches the wrapped type package
- JCC template block is present
- All interface methods or abstract methods are declared `native`
- No required abstract method is missing
- Abstract-class wrappers preserve required superclass constructors
- Method signatures match the wrapped type exactly
- `@Override` is present where applicable
