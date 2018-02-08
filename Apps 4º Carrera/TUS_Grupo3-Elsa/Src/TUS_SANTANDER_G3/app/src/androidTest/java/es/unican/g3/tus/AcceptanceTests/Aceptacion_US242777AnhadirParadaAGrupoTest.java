package es.unican.g3.tus.views;


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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Database;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Aceptacion_US242777AnhadirParadaAGrupoTest {

    @Rule
    public ActivityTestRule<ActivityInicialLogo> mActivityTestRule = new ActivityTestRule<>(ActivityInicialLogo.class);

    @Before
    public void estadoInicial(){
        Database db = new Database(mActivityTestRule.getActivity().getApplicationContext());
        db.eliminaParadasDeGrupos();
    }

    @Test
    public void PA1US242777() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Ordenar alfabéticamente"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.RelativeLayout01),
                        childAtPosition(
                                withId(android.R.id.list),
                                0),
                        isDisplayed()));
        relativeLayout.perform(longClick());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Aguamarina"),
                        childAtPosition(
                                withId(R.id.list),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_grupos), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linearLayoutGrupoCabecera),
                        childAtPosition(
                                allOf(withId(android.R.id.list),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.groupTitle), withText("Aguamarina"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayoutGrupoCabecera),
                                        childAtPosition(
                                                withId(android.R.id.list),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Aguamarina")));

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.RelativeLayout01),
                        childAtPosition(
                                allOf(withId(android.R.id.list),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        relativeLayout2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewNumero), withText("296"),
                        childAtPosition(
                                allOf(withId(R.id.RelativeLayout01),
                                        childAtPosition(
                                                withId(android.R.id.list),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("296")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewName), withText("Abilio Garcia baron 1 ( hote4l expres)"),
                        childAtPosition(
                                allOf(withId(R.id.RelativeLayout01),
                                        childAtPosition(
                                                withId(android.R.id.list),
                                                1)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Abilio Garcia baron 1 ( hote4l expres)")));

    }

    @Test
    public void PA2US242777() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Ordenar alfabéticamente"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.RelativeLayout01),
                        childAtPosition(
                                withId(android.R.id.list),
                                0),
                        isDisplayed()));
        relativeLayout.perform(longClick());

        Database db = new Database(mActivityTestRule.getActivity().getApplicationContext());

        // Provocar que la BBDD dé problemas
        try {
            db.eliminarEstructura(null);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Intentar insertar un elemento en la BBDD
        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Aguamarina"),
                        childAtPosition(
                                withId(R.id.list),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        // Comprobar mensaje de error en interfaz y rescatar BBDD
        db.reiniciar(null);
        onView(withText(R.string.app_fallo_bbdd)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void PA3US242777() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_grupos), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.empty), withText("Aún no hay paradas en esta vista"),
                        isDisplayed()));
        textView.check(matches(withText("Aún no hay paradas en esta vista")));

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
