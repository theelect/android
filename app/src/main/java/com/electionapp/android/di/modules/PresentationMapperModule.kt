package com.electionapp.android.di.modules

import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.android.utils.mapper.PollingUnitMapper
import com.electionapp.android.utils.mapper.StateMapper
import com.electionapp.android.utils.mapper.UserMapper
import dagger.Module
import dagger.Provides

@Module
class PresentationMapperModule {
    @Provides
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

    @Provides
    fun providePollingUnitMapper(): PollingUnitMapper {
        return PollingUnitMapper()
    }

    @Provides
    fun provideStateMapper(): StateMapper {
        return StateMapper()
    }


    @Provides
    fun providePVCDataMapper(pollingUnitMapper: PollingUnitMapper,
                             stateMapper: StateMapper): PVCDataMapper {
        return PVCDataMapper(pollingUnitMapper, stateMapper)
    }

}