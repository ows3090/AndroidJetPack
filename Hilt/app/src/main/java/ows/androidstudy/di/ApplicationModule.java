package ows.androidstudy.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ows.androidstudy.di.qualifier.AppHash;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule{

    @AppHash
    @Provides
    public String provideHash(){
        return String.valueOf(hashCode());
    }
}
