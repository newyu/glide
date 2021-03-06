package com.bumptech.glide.request.animation;

import static com.bumptech.glide.request.animation.GlideAnimation.ViewAdapter;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.animation.Animation;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class ViewAnimationTest {
    private ViewAnimation<Object> viewAnimation;
    private ViewAdapter adapter;
    private ImageView view;
    private ViewAnimation.AnimationFactory animationFactory;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        animationFactory = mock(ViewAnimation.AnimationFactory.class);
        view = mock(ImageView.class);
        adapter = mock(ViewAdapter.class);
        when(adapter.getView()).thenReturn(view);
        viewAnimation = new ViewAnimation<Object>(animationFactory);
    }

    @Test
    public void testClearsAnimationOnAnimate() {
        viewAnimation.animate(null, adapter);

        verify(view).clearAnimation();
    }

    @Test
    public void testAlwaysReturnsFalse() {
        assertFalse(viewAnimation.animate(null, adapter));
    }

    @Test
    public void testStartsAnimationOnAnimate() {
        Animation animation = mock(Animation.class);
        when(animationFactory.build()).thenReturn(animation);
        viewAnimation.animate(null, adapter);
        verify(view).clearAnimation();
        verify(view).startAnimation(eq(animation));
    }
}
