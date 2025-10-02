# split-issue

Gradle (Kotlin) project contains only single Java class - SlitIsue sampling identified issue. Demonstration focuses on Camel route definition containing split within which we also use validate. Once this split and validate combination appears in our definition, split must be ended with duplicated .end() call to properly define its end. The single end call will not have same effect as it would be expected.

No other Camel methods like validate have been identified to have same issue in combination with split.

Please inspect the code with comments and then log output of the program for further explanation.

Quarkus logging is reduced for better output clarity (see application.properties).

## How to run demonstration
To execute app in Quarkus dev mode by gradle command:

```
gradlew quarkusDev
```
Dev Mode will rerun the demonstration instantly on start and then on every change to SplitIssue.java.


### Output

2nd and 3rd showcase should produce same lines as 1st one. But output is unexpected.

Example output:

```bash
     __  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2025-10-02 10:54:14,034 INFO  [SplitIssue:29] (Camel (camel-86) thread #84 - timer://splitIssue) Demo Start

2025-10-02 10:54:14,034 INFO  [SplitIssue:41] (Camel (camel-86) thread #84 - timer://splitIssue) 1st Showcase - Start
2025-10-02 10:54:14,035 INFO  [SplitIssue:45] (Camel (camel-86) thread #84 - timer://splitIssue) Item 1
2025-10-02 10:54:14,035 INFO  [SplitIssue:45] (Camel (camel-86) thread #84 - timer://splitIssue) Item 2
2025-10-02 10:54:14,035 INFO  [SplitIssue:45] (Camel (camel-86) thread #84 - timer://splitIssue) Item 3
2025-10-02 10:54:14,035 INFO  [SplitIssue:48] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,035 INFO  [SplitIssue:49] (Camel (camel-86) thread #84 - timer://splitIssue) 1st Showcase - Complete

2025-10-02 10:54:14,035 INFO  [SplitIssue:54] (Camel (camel-86) thread #84 - timer://splitIssue) 2nd Showcase - Start
2025-10-02 10:54:14,035 INFO  [SplitIssue:58] (Camel (camel-86) thread #84 - timer://splitIssue) Item 1
2025-10-02 10:54:14,035 INFO  [SplitIssue:61] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,035 INFO  [SplitIssue:58] (Camel (camel-86) thread #84 - timer://splitIssue) Item 2
2025-10-02 10:54:14,035 INFO  [SplitIssue:61] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,035 INFO  [SplitIssue:58] (Camel (camel-86) thread #84 - timer://splitIssue) Item 3
2025-10-02 10:54:14,035 INFO  [SplitIssue:61] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,035 INFO  [SplitIssue:64] (Camel (camel-86) thread #84 - timer://splitIssue) 2nd Showcase - Complete

2025-10-02 10:54:14,035 INFO  [SplitIssue:69] (Camel (camel-86) thread #84 - timer://splitIssue) 3rd Showcase - Start
2025-10-02 10:54:14,036 INFO  [SplitIssue:75] (Camel (camel-86) thread #84 - timer://splitIssue) Item 1
2025-10-02 10:54:14,036 INFO  [SplitIssue:78] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,036 INFO  [SplitIssue:75] (Camel (camel-86) thread #84 - timer://splitIssue) Item 2
2025-10-02 10:54:14,036 INFO  [SplitIssue:78] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,036 INFO  [SplitIssue:75] (Camel (camel-86) thread #84 - timer://splitIssue) Item 3
2025-10-02 10:54:14,036 INFO  [SplitIssue:78] (Camel (camel-86) thread #84 - timer://splitIssue) This log entry should appear only once, after the split iteration
2025-10-02 10:54:14,036 INFO  [SplitIssue:81] (Camel (camel-86) thread #84 - timer://splitIssue) 3rd Showcase - Complete

2025-10-02 10:54:14,036 INFO  [SplitIssue:84] (Camel (camel-86) thread #84 - timer://splitIssue) Demo Complete
```