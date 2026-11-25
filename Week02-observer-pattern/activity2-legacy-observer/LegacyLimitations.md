# Legacy Observer Limitations

`java.util.Observable` and `java.util.Observer` were deprecated in Java 9.

- `Observable` is a class. A model that extends it cannot also extend a domain-specific base class.
- `setChanged()` is protected and notification behavior is awkward to compose.
- `notifyObservers(Object)` uses `Object`, so property details are not type-safe.
- Modern alternatives use interfaces, listener APIs, JavaBeans property listeners, or UI properties.
