package com.tonyecoleelection.android.di.modules

import com.tonyecoleelection.android.utils.mapper.*
import com.tonyecoleelection.domain.mapper.PVCDataModelMapper
import com.tonyecoleelection.domain.mapper.VoterDataModelMapper
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

    @Provides
    fun providePVCStatsMapper(): PVCStatsMapper {
        return PVCStatsMapper()
    }

    @Provides
    fun provideLGAMapper(): LGAMapper {
        return LGAMapper()
    }

    @Provides
    fun provideVoterDataMapper(pvcDataMapper: PVCDataMapper): VoterDataMapper {
        return VoterDataMapper(pvcDataMapper)
    }
}