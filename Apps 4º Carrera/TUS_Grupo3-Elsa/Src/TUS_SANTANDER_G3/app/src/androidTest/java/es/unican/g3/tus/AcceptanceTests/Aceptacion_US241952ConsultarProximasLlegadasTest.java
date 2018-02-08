package es.unican.g3.tus;


import android.support.test.espresso.DataInteraction;
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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Aceptacion_US241952ConsultarProximasLlegadasTest {

    @Rule
    public ActivityTestRule<ActivityInicialLogo> mActivityTestRule = new ActivityTestRule<>(ActivityInicialLogo.class);

    @Test
    public void PA1US241952() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Ordenar alfabéticamente"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(356);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewLineaa), withText("6C2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("6C2")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewTitle), withText("ESTIMACIONES"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayoutElements),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("ESTIMACIONES")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewLineaa), withText("18"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("18")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewLineaa), withText("17"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("17")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewLineaa), withText("17"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("17")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewLineaa), withText("14"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        3),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("14")));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        3),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

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