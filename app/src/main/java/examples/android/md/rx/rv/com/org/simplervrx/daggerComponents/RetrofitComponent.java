package examples.android.md.rx.rv.com.org.simplervrx.daggerComponents;

import dagger.Component;
import examples.android.md.rx.rv.com.org.simplervrx.daggerModules.RetrofitModule;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.ArtistSearchFragment;

/**
 * Created by User on 11/24/2016.
 */

@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    void inject(ArtistSearchFragment artistSearchFragment);

}
