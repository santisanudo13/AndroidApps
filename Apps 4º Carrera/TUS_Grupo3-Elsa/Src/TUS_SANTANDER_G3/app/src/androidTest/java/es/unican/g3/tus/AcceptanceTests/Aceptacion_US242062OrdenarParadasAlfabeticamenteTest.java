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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.g3.tus.R;
import es.unican.g3.tus.views.ActivityInicialLogo;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Aceptacion_US242062OrdenarParadasAlfabeticamenteTest {

    @Rule
    public ActivityTestRule<ActivityInicialLogo> mActivityTestRule = new ActivityTestRule<>(ActivityInicialLogo.class);

    @Test
    public void PA1US242062O() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Ordenar alfabéticamente"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("296"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("296")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("Abilio Garcia baron 1 ( hote4l expres)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Abilio Garcia baron 1 ( hote4l expres)")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewNumero), withText("455"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("455")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewName), withText("Adarzo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Adarzo")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewNumero), withText("235"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("235")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewName), withText("Adarzo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Adarzo")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textViewNumero), withText("489"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("489")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewName), withText("Albert Einstein.14"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        3),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Albert Einstein.14")));

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
