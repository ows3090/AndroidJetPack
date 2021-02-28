package ows.androidstudy.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ows.androidstudy.di.ActivityHash.ActivityHash;

@Module
@InstallIn(SingletonComponent.class)
public class ActivityModule {

    @ActivityHash
    @Provides
    public String provideHash(){
        return String.valueOf(hashCode());
    }
}
