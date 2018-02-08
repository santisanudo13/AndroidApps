package es.unican.g3.tus;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.g3.tus.R;
import es.unican.g3.tus.views.ActivityInicialLogo;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Aceptacion_US243482ListarParadasTest {

    @Rule
    public ActivityTestRule<ActivityInicialLogo> mActivityTestRule = new ActivityTestRule<>(ActivityInicialLogo.class);

    @Test
    public void aceptacionListarParadasTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_portada), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        3),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        4),
                        isDisplayed()));
        linearLayout5.check(matches(isDisplayed()));

        ViewInteraction linearLayout6 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        5),
                        isDisplayed()));
        linearLayout6.check(matches(isDisplayed()));

        ViewInteraction linearLayout7 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        6),
                        isDisplayed()));
        linearLayout7.check(matches(isDisplayed()));

        ViewInteraction linearLayout8 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        7),
                        isDisplayed()));
        linearLayout8.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
