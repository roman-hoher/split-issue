package dev.issue.split;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

/**
 * Route is defined to demonstrate the issue when validate is used within the split block. Then, to
 * exit this split it is necessary to end the split block with second 'end' call. It does not matter
 * if validate is used once or multiple times, split simply needs extra 'end' to ensure that
 * following method call definitions will not be executed within each split iteration.
 *
 * <p>Demonstration is dividied to 3 showcases of different application of validate within the
 * split.
 *
 * <p>To see the outcome of each showcase please observe logged output. Logged lines starting 'This
 * log entry should appear only once...' should indeed appear only once within each, 2nd and 3rd
 * showcase, but they do repeat instead.
 */
@ApplicationScoped
public class SplitIssue extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    // Main demonstration route, fires off immediately after Quarkus app started
    from("timer:splitIssue?delay=-1&repeatCount=1")

        // Route starts
        .log("Demo Start\n")

        // 1st Showcase - Split works as expected, it entails simple condition and log entry. No
        // validate included.
        //
        // Output:
        //    1st Showcase - Start
        //    Item 1
        //    Item 2
        //    Item 3
        //    This log entry should appear only once, after the split iteration
        //    1st Showcase - Complete
        .log("1st Showcase - Start")
        .split(constant("123").tokenize(""))
        // only to demonstrate some condition-based logic we log every split item which is not null
        .filter(body().isNotNull())
        .log("Item ${body}")
        .end() // filter end
        .end() // split end
        .log("This log entry should appear only once, after the split iteration")
        .log("1st Showcase - Complete\n")

        // 2nd Showcase - Here we extend the 1st Showcase and involve a validate within the split.
        // The last log entry is expected to appear only once, but it happens to be reiterated with
        // every split run
        .log("2nd Showcase - Start")
        .split(constant("123").tokenize(""))
        .filter(body().isNotNull())
        .validate(body().isNotNull()) // please, ignore the fact that logic is futile
        .log("Item ${body}")
        .end() // filter end
        .end() // split end
        .log("This log entry should appear only once, after the split iteration")
        .end() // split end for second time, if not present everything following would be executed
        // multiple times as well
        .log("2nd Showcase - Complete\n")

        // 3rd Showcase - Altering 2nd showcase with having multiple validates.
        // Here we see that no matter how many validates are involved, no more .end() calls are
        // needed to solve the situation. Only one extra 'end' is still needed as in 2nd showcase.
        .log("3rd Showcase - Start")
        .split(constant("123").tokenize(""))
        .filter(body().isNotNull())
        .validate(body().isNotNull())
        .validate(body().isInstanceOf(String.class))
        .validate(body().isGreaterThan("0")) // string based comparison
        .log("Item ${body}")
        .end() // filter end
        .end() // split end
        .log("This log entry should appear only once, after the split iteration")
        .end() // split end for second time, if not present everything following would be executed
        // multiple times as well
        .log("3rd Showcase - Complete\n")

        // Route ends
        .log("Demo Complete\n");
  }
}
