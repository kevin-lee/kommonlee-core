kommonlee-core
==============

[![Build Status](https://travis-ci.org/Kevin-Lee/kommonlee-core.svg)](https://travis-ci.org/Kevin-Lee/kommonlee-core)

KommonLee Core

[Project Homepage](http://projects.elixirian.org/kommonlee)

#Welcome to KommonLee

[KommonLee](https://github.com/Kevin-Lee/kommonlee-core) (pronounced the same as 'commonly') is a set of commonly used Java libraries written by [Kevin Lee](http://lckymn.com) and is currently used in the [software projects](http://lckymn.com/kevin-projects.html) owned by Kevin Lee and Agin3 Pty Ltd. It is an open source project. The main goal of this project is to create better APIs for Java to make Java programming fun and enjoyable with fewer chances to introduce bugs.

So Traditional vs KommonLee
* Traditional

```java
public class FileElement {
  // ...

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((folder == null) ? 0 : folder.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object fileElement) {
    if (this == fileElement) {
      return true;
    }
    if (!(fileElement instanceof FileElement)) {
      return false;
    }
    final FileElement that = (FileElement) fileElement;
    return (this.folder == that.folder ||
               (null != this.folder &&
                this.folder
                    .equals(that
                    .getFolder()))) &&
           (this.name == that.name ||
               (null != this.name &&
                this.name
                    .equals(that
                    .getName())));
  }
}

```

* KommonLee

```java
import static org.elixirian.kommonlee.util.Objects.*;

public class FileElement {
  // ...

  @Override
  public int hashCode() {
    return hash(folder, name);
  }

  @Override
  public boolean equals(final Object fileElement) {
    if (this == fileElement) {
      return true;
    }
    final FileElement that = castIfInstanceOf(FileElement.class, fileElement);
    return null != that &&
        (equal(this.folder, that.getFolder()) &&
         equal(this.name, that.getName()));
  }
}

```

# Why KommonLee?
1. Why should I use it? Why not [Objects](http://docs.oracle.com/javase/7/docs/api/java/util/Objects.html) in JDK 7?
   * The Objects utility class is just a small part of KommonLee, and besides you can benefit by using KommonLee's Objects as with JDK's java.lang.Objects, you can't really have convenient static import due to [Shadowing Declarations?](http://blog.lckymn.com/2010/05/01/catch-up-2010-05-01/#3.%20Shadowing%20Declarations?)
   * ... more will be added.

1. What about using [Lombok](http://projectlombok.org)?
   * Lombok is good unless you use some frameworks or libraries that do not work well with Lombok. For instance, you can't use Lombok, if you use [AspectJ](http://www.eclipse.org/aspectj) (just like me :( ).

