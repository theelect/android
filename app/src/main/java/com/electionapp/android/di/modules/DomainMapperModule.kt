package com.electionapp.android.di.modules

import com.electionapp.android.utils.mapper.PVCDataMapper
import com.electionapp.android.utils.mapper.PollingUnitMapper
import com.electionapp.android.utils.mapper.StateMapper
import com.electionapp.android.utils.mapper.UserMapper
import com.electionapp.domain.mapper.PVCDataModelMapper
import com.electionapp.domain.mapper.PollingUnitModelMapper
import com.electionapp.domain.mapper.StateModelMapper
import com.electionapp.domain.mapper.UserModelMapper
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

}