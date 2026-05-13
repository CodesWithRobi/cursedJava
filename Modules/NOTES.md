# Java Modules Explained

This document provides a basic understanding of the Java Platform Module System (JPMS), which was introduced in Java 9.

## What are Java Modules?

The Java Platform Module System is a major change introduced in Java 9 to organize Java applications and the JRE itself into modules. A module is a collection of related packages and resources, along with a module descriptor file (`module-info.java`).

### Key Benefits:

*   **Strong Encapsulation:** Modules allow you to explicitly control which packages are accessible from outside the module. This prevents accidental or unauthorized use of internal APIs.
*   **Reliable Configuration:** The module system checks for missing or conflicting modules at startup, which makes applications more reliable.
*   **Improved Security:** By encapsulating internal APIs, modules reduce the attack surface of an application.
*   **Scalable Applications:** Modules help in building and maintaining large applications by providing a clear structure and separation of concerns.

## Core Concepts

### `module-info.java`

This is the heart of a module. It's a file located in the root of the module's source directory and it defines the module's properties, such as its name, its dependencies, and the packages it makes available to other modules.

### Module Declaration

You declare a module using the `module` keyword in `module-info.java`.

```java
module com.mycompany.mymodule {
    // module directives go here
}
```

### `requires` Directive

The `requires` directive specifies that a module depends on another module.

```java
module com.mycompany.app {
    requires com.mycompany.api;
}
```
This means that `com.mycompany.app` needs `com.mycompany.api` to compile and run.

### `exports` Directive

The `exports` directive specifies which packages in a module are accessible to other modules. By default, all packages in a module are private to that module.

```java
module com.mycompany.api {
    exports com.mycompany.api.services;
}
```
In this example, only the `com.mycompany.api.services` package is accessible to other modules that require `com.mycompany.api`.

### `requires static` Directive

The `requires static` directive declares a compile-time-only dependency that is optional at runtime. This is useful for modules that can integrate with other modules but don't strictly require them to function.

```java
module com.mycompany.app {
    requires static com.mycompany.optional.feature;
}
```

### `requires transitive` Directive

The `requires transitive` directive makes a module's dependencies available to other modules that depend on it. If module A `requires transitive` module B, and module C `requires` module A, then module C can access the public types of module B without explicitly requiring it.

```java
// In module B's descriptor
module com.mycompany.moduleB {
    exports com.mycompany.moduleB.api;
}

// In module A's descriptor
module com.mycompany.moduleA {
    requires transitive com.mycompany.moduleB;
}

// In module C's descriptor
module com.mycompany.moduleC {
    requires com.mycompany.moduleA;
    // Now moduleC can access com.mycompany.moduleB.api
}
```

### `opens` and `open` Directives for Reflection

The `opens` and `open` keywords are used to grant other modules access to a module's internal types via reflection, which is otherwise disallowed by default.

*   **`opens <package> to <module>`**: This opens a specific package to a specific module, allowing deep reflection.

    ```java
    module com.mycompany.data {
        opens com.mycompany.data.models to com.mycompany.serializer;
    }
    ```

*   **`open module <module-name>`**: This opens the *entire* module, making all its packages accessible for reflection by any other module. This should be used with caution as it significantly breaks encapsulation.

    ```java
    open module com.mycompany.legacy {
        // All packages are open to reflection
    }
    ```

### Module Directive Cheatsheet

| Keyword                 | Description                                                                                             |
| ----------------------- | ------------------------------------------------------------------------------------------------------- |
| `module <name>`         | Defines a new module.                                                                                   |
| `requires <module>`     | Specifies a mandatory dependency on another module.                                                     |
| `requires transitive`   | Specifies a dependency that is also made available to any module that requires this one.                |
| `requires static`       | Specifies an optional, compile-time-only dependency.                                                    |
| `exports <package>`     | Makes a package's public types accessible to all modules that require this module.                      |
| `exports <pkg> to <mod>`| Makes a package's public types accessible only to the specified module(s).                              |
| `opens <package>`       | Allows all other modules to use reflection to access the private types of a package.                    |
| `opens <pkg> to <mod>`  | Allows a specific module to use reflection to access the private types of a package.                    |
| `open module <name>`    | Allows all other modules to use reflection to access all packages within the entire module.             |
| `uses <service>`        | Specifies that this module consumes a service interface.                                                |
| `provides <svc> with <impl>` | Specifies that this module provides an implementation for a service interface.                      |


### Module Path vs. Class Path

*   **Class Path:** The old way of loading classes. It's a linear list of JARs and directories, and it can lead to issues like "classpath hell" where different versions of the same library conflict.
*   **Module Path:** The new way of loading modules. It's a path where the Java runtime looks for modules. The module system ensures that each module is present and that its dependencies are met.

## Directory Structure

A common convention for a module-based project is to have a source directory (`src`) that contains a directory for each module. Inside each module directory, you have the `module-info.java` file and the package structure.

For our project, the structure is:

```
.
└── src
    └── greetings
        ├── com
        │   └── modular
        │       └── greetings
        │           └── Modular.java
        └── module-info.java
```

*   `src`: The module source path.
*   `greetings`: The name of our module.
*   `module-info.java`: The module descriptor for the `greetings` module.
*   `com/modular/greetings`: The package structure inside the module.

## Compilation and Execution

### Compiling a Module

You use `javac` with the `--module-source-path` and `-d` flags.

*   `--module-source-path`: Specifies the directory where the module source files are located (in our case, `src`).
*   `-d`: Specifies the output directory for the compiled modules (in our case, `mods`).

```bash
javac --module-source-path src -d mods $(find src -name "*.java")
```

### Running a Module

You use `java` with the `--module-path` and `-m` flags.

*   `--module-path`: Specifies the directory where the compiled modules are located (in our case, `mods`).
*   `-m` (or `--module`): Specifies the main class to run in the format `<module-name>/<fully-qualified-main-class>`.

```bash
java --module-path mods -m greetings/com.modular.greetings.Modular
```
