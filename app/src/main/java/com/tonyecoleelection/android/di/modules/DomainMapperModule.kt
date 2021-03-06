package com.tonyecoleelection.android.di.modules

import com.tonyecoleelection.domain.mapper.*
import dagger.Module
import dagger.Provides

@Module
class DomainMapperModule {
    @Provides
    fun provideUserMapper(): UserModelMapper {
        return UserModelMapper()
    }

    @Provides
    fun providePollingUnitMapper(): PollingUnitModelMapper {
        return PollingUnitModelMapper()
    }

    @Provides
    fun provideStateMapper(): StateModelMapper {
        return StateModelMapper()
    }


    @Provides
    fun providePVCDataMapper(pollingUnitMapper: PollingUnitModelMapper,
                             stateMapper: StateModelMapper): PVCDataModelMapper {
        return PVCDataModelMapper(pollingUnitMapper, stateMapper)
    }

    @Provides
    fun providePVCStatsModelMapper(): PVCStatsModelMapper {
        return PVCStatsModelMapper()
    }

    @Provides
    fun provideLGAModelMapper(): LGAModelMapper {
        return LGAModelMapper()
    }

    @Provides
    fun provideVoterDataModelMapper(pvcDataModelMapper: PVCDataModelMapper): VoterDataModelMapper {
        return VoterDataModelMapper(pvcDataModelMapper)
    }
}