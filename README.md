### Running
```bash
./gradlew clean test allureReport 
```


### Resources 
* [Allure + Gradle + Selenide + Retrofit2](https://medium.com/@rosolko/simple-allure-2-configuration-for-gradle-8cd3810658dd) 
* [Junit 5 parallel execution](http://antkorwin.com/junit5/junit5_parallel_execution.html)
  ```text
  By default, JUnit Jupiter tests are run sequentially in a single thread. Running tests in parallel, e.g. to speed up execution, is available as an opt-in feature since version 5.3. To enable parallel execution, simply set the junit.jupiter.execution.parallel.enabled configuration parameter to true, e.g. in junit-platform.properties (see Configuration Parameters for other options).
  Once enabled, the JUnit Jupiter engine will execute tests on all levels of the test tree fully in parallel according to the provided configuration while observing the declarative synchronization mechanisms. Please note that the Capturing Standard Output/Error feature needs to enabled separately.
  Parallel test execution is currently an experimental feature. You’re invited to give it a try and provide feedback to the JUnit team so they can improve and eventually promote this feature.
  ```
* [Gradle`s Junit5 support](https://docs.gradle.org/4.6/release-notes.html#junit-5-support)
* [Passing System Properties from command line](https://stackoverflow.com/questions/42492742/how-to-pass-command-line-arguments-to-tests-with-gradle-test):
```text
When you run gradle test -Darg1=smth, you pass system parameter arg1 to gradle jvm, not test jvm where tests are run. It is designed this way to protect tests from side effects.
If you need to propagete param to tests, use smth like this
test {
    systemProperty 'arg1', System.getProperty('arg1')
}
```