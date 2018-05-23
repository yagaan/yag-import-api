# yag-import-api
Java API to read/write Scan results report that can be processed by the [YAG Suite](http://www.yagaan.com/products.html|http://www.yagaan.com/products.html). Issues wil be analyzed to reduce false positives rate and provide a CVSS score based on machine learning and a hierarchical knowledge filled by some users feedbacks.

## Quick Start

### Install
```sh
	git clone https://github.com/alfoch/yag-import-api.git
	cd yag-import-api
	mvn clean install
```

### Export some scan results

```java
	//create a new scan for an application named 'test'
	Scan scan = new Scan("test");
	
	//add some checked rules into that scan, optional classification
	scan.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
	...
	
	//create some issues in function of the files relative paths and lines
	List<Issue> issues = new ArrayList<>();
	issues.add(new Issue("test.rule1",new Fragment("Test1.java", 12))); //issue detected by checker test.rule1 at line 12
	issues.add(new Issue("test.rule1",new Fragment("Test1.java", 18)));
	
	//store it in a JSON file
	OutputStream output = new FileOutputStream(new File("report.yson"));
	ScanIO.write(scan,issues output);
```	
	
### Manage large scan results

In order to reduce memory consumption you can use a ``Supplier<Issue>`` to write the JSON file without storing all informations in memory. This supplier need to returns `null` when no more issue is available.

```java	
	Scan scan = new Scan("test");
	...
	OutputStream output = new FileOutputStream(new File("report.yson"));

	//use a supplier
	Supplier<Issue> supplier = ... // your specific needs supplier
	ScanIO.write(results, supplier, output);
```


## License

See the [LICENSE](https://github.com/alfoch/yag-import-api/master/LICENSE) file.



