# DynamicProxyAndersen

@Cached - has 2 parameters: 
  savePolicy - use FILE, if you want cache methods result into file in your system (default),
            - use HEAP, if you want cache just for one running in your JVM
  saveName   - by default file name in your system or key in JVM cache will have method name.
  If you want to change it use this parameter.

CacheHandler processes Service implementation and checks its methods for cached annotations.
CacheProcy has just one static method cache() where it uses a CacheHandler for returning Service proxy with cached methods.
cache() the method can have two parameters:
  Service parameter required;
  String parameter indicating the path to the file to be cached. a typical path is the root folder of the project.

CgLibCacheProxy does the same job as a CacheProxy and CacheHandler, but uses CGLib.
